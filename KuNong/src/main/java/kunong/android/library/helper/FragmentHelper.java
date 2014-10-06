package kunong.android.library.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

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

    public static boolean returnBackStackImmediate(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 0) {
            List<Fragment> fragments = fm.getFragments();

            if (fragments != null && fragments.size() > 0) {
                for (Fragment fragment : fragments) {
                    boolean hasBackStackEntry = fragment != null && returnBackStackImmediate(fragment.getChildFragmentManager());

                    if (hasBackStackEntry) {
                        return true;
                    }
                }
            }

            fm.popBackStackImmediate();

            return true;
        }

        return false;
    }

}
