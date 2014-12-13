package kunong.android.library.concurrent;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;

/**
 * Created by Macmini on 12/2/14 AD.
 */
public class Async {

    public static Task background(Runnable runnable) {
        return new Task().background(runnable);
    }

    public static Task main(Runnable runnable) {
        return main(runnable, 0);
    }

    public static Task main(Runnable runnable, long delay) {
        return new Task().main(runnable, delay);
    }

    public enum QueueType {
        MAIN, BACKGROUND
    }

    public static class Task {
        LinkedList<TaskQueue> queues = new LinkedList<>();

        private Handler mHandler = new Handler(Looper.getMainLooper());
        private boolean hasRunningTask;

        public Task background(Runnable runnable) {
            this.queues.add(new TaskQueue(QueueType.BACKGROUND, runnable));

            onTaskComplete();

            return this;
        }

        public Task main(Runnable runnable) {
            return main(runnable, 0);
        }

        public Task main(Runnable runnable, long delay) {
            this.queues.add(new TaskQueue(QueueType.MAIN, runnable, delay));

            onTaskComplete();

            return this;
        }

        private void onTaskComplete() {
            if (!hasRunningTask && queues.size() > 0) {
                TaskQueue taskQueue = queues.poll();

                runTask(taskQueue);
            }
        }

        private void runTask(TaskQueue taskQueue) {
            hasRunningTask = true;

            if (taskQueue.type == QueueType.BACKGROUND) {
                runBackground(taskQueue.runnable);
            } else {
                runMain(taskQueue.runnable, taskQueue.delay);
            }
        }

        private void runBackground(Runnable runnable) {
            ThreadQueue threadQueue = new ThreadQueue();

            threadQueue.add(new QThread<Object, Object>() {
                @Override
                public Object run(Object... objects) {
                    runnable.run();

                    return null;
                }

                @Override
                public void onCompleted(Object o) {
                    hasRunningTask = false;

                    onTaskComplete();
                }
            });

            threadQueue.start();
        }

        private void runMain(Runnable runnable, long delay) {
            mHandler.postDelayed(() -> {
                runnable.run();

                hasRunningTask = false;

                onTaskComplete();
            }, delay);
        }
    }

    public static class TaskQueue {
        QueueType type;
        Runnable runnable;
        long delay;

        public TaskQueue(QueueType type, Runnable runnable) {
            this(type, runnable, 0);
        }

        public TaskQueue(QueueType type, Runnable runnable, long delay) {
            this.type = type;
            this.runnable = runnable;
            this.delay = delay;
        }
    }
}
