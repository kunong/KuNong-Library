package kunong.android.library.concurrent;

import android.util.SparseArray;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kunong on 12/14/14 AD.
 */
public final class EventLocker {

    private HashMap<String, Boolean> mKeys = new HashMap<>();
    private SparseArray<Runnable> mRunnables = new SparseArray<>();
    private List<Runnable> mRunnableQueue = new ArrayList<>();
    private boolean mIsChecking = false;

    public EventLocker() {
    }

    public EventLocker(String... keys) {
        Stream.of(keys).forEach(this::lock);
    }

    public synchronized void lock(String key) {
        mKeys.put(key, true);
    }

    public synchronized void unlock(String key) {
        mKeys.remove(key);

        if (isKeyEmpty()) {
            check();
        }
    }

    public void run(Runnable runnable) {
        run(runnable.hashCode(), runnable);
    }

    public void run(String runKey, Runnable runnable) {
        run(getRunCode(runKey), runnable);
    }

    private synchronized void run(int runCode, Runnable runnable) {
        remove(runCode);

        if (isKeyEmpty()) {
            runnable.run();
        } else {
            mRunnables.put(runCode, runnable);
            mRunnableQueue.add(runnable);
        }
    }

    private int getRunCode(String runKey) {
        return runKey.hashCode();
    }

    public boolean containRun(String runKey) {
        return getRunnable(getRunCode(runKey)) != null;
    }

    public boolean containKey(String key) {
        return mKeys.containsKey(key);
    }

    public void remove(String runKey) {
        remove(getRunCode(runKey));
    }

    private synchronized void remove(int runCode) {
        AtomicInteger index = new AtomicInteger();
        Runnable runnable = getRunnable(runCode, index);

        if (runnable != null) {
            mRunnables.removeAt(index.get());
            mRunnableQueue.remove(runnable);
        }
    }

    private synchronized Runnable getRunnable(int runCode) {
        return mRunnables.get(runCode);
    }

    private synchronized Runnable getRunnable(int runCode, AtomicInteger index) {
        index.set(mRunnables.indexOfKey(runCode));

        return index.get() >= 0 ? mRunnables.valueAt(index.get()) : null;
    }

    private synchronized boolean check() {
        if (mIsChecking)
            return true;

        mIsChecking = true;

        Async.main(() -> {
            synchronized (this) {
                mIsChecking = false;

                if (!isKeyEmpty())
                    return;

                dispatch();
            }
        });

        return false;
    }

    private synchronized void dispatch() {
        for (Runnable runnable : mRunnableQueue) {
            runnable.run();
        }

        mRunnableQueue.clear();
        mRunnables.clear();
    }

    public boolean isKeyEmpty() {
        return mKeys.size() == 0;
    }

}
