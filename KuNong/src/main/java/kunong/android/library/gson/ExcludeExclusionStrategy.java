package kunong.android.library.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import kunong.android.library.gson.annotations.Exclude;

/**
 * Created by kunong on 12/22/14 AD.
 */
public class ExcludeExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Exclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(Exclude.class) != null;
    }
}
