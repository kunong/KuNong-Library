package kunong.android.library.helper;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;

/**
 * Created by Macmini on 10/3/14 AD.
 */
public class ViewHelper {

    public static void addOnPreDrawListener(View view, OnLayoutUpdateListener listener) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);

                listener.onLayoutUpdate(view);

                return true;
            }
        });
    }

    public static void addOnLayoutUpdateListener(View view, OnLayoutUpdateListener listener) {
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                view.removeOnLayoutChangeListener(this);

                listener.onLayoutUpdate(v);
            }
        });
    }

    public static void setTopMargin(View view, int topMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.topMargin = topMargin;

        view.requestLayout();
    }

    public static Rect getViewRect(View view) {
        int[] location = new int[2];

        view.getLocationOnScreen(location);

        return new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
    }

    public static View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            final int childIndex = position - firstListItemPosition;

            return listView.getChildAt(childIndex);
        }
    }

    public static interface OnLayoutUpdateListener {
        public void onLayoutUpdate(View v);
    }
}
