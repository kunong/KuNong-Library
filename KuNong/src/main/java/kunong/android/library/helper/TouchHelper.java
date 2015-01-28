package kunong.android.library.helper;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Macmini on 1/28/15 AD.
 */
public class TouchHelper {

    public static void disableParentTouchEventWhenTouch(View sourceView, ViewGroup targetView) {
        View view = sourceView;

        view.setOnTouchListener((v, event) -> {
            int action = event.getAction() & MotionEventCompat.ACTION_MASK;

            switch (action) {

                case MotionEvent.ACTION_DOWN:
                    targetView.requestDisallowInterceptTouchEvent(true);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    targetView.requestDisallowInterceptTouchEvent(false);
                    break;

            }

            return false;
        });
    }

}
