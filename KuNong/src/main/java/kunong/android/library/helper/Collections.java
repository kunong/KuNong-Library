package kunong.android.library.helper;

import com.android.internal.util.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunong on 12/13/14 AD.
 */
public class Collections {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();

        for (T obj : list) {
            if (predicate.apply(obj))
                filteredList.add(obj);
        }

        return filteredList;
    }

}
