package kunong.android.library.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotificationCenter {

    private static HashMap<String, List<OnNotifyListener>> notifyMap = new HashMap<>();

    public static void addListener(String event, OnNotifyListener listener) {
        List<OnNotifyListener> notify = notifyMap.get(event);

        if (notify == null) {
            notify = new ArrayList<>();
            notifyMap.put(event, notify);
        }

        notify.add(listener);
    }

    public static void dispatchEvent(String event, HashMap<String, Object> objects) {
        List<OnNotifyListener> notify = notifyMap.get(event);

        if (notify != null) {
            for (OnNotifyListener listner : notify) {
                listner.onNotify(objects);
            }
        }
    }

    public static void removeListener(String event, OnNotifyListener listener) {
        List<OnNotifyListener> notify = notifyMap.get(event);

        if (notify != null) {
            notify.remove(listener);
        }
    }

    public static void removeEvent(String event) {
        notifyMap.remove(event);
    }

    public static interface OnNotifyListener {
        public void onNotify(HashMap<String, Object> objects);
    }
}
