package kunong.android.library.helper;

import android.support.v4.app.FragmentManager;

/**
 * Created by kunong on 10/3/14 AD.
 */
public class FragmentHelper {

    public static void clearChildFragmentStack(FragmentManager fragmentManager) {
        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; --i) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
            fragmentManager.popBackStackImmediate(backStackEntry.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
