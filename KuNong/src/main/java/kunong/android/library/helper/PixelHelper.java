package kunong.android.library.helper;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class PixelHelper {

	public static int dpToPx(Context context, float value) {
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());

		return (int) (px + 0.5);
	}

}
