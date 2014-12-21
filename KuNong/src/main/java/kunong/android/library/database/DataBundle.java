package kunong.android.library.database;

import java.io.Serializable;
import java.util.HashMap;

public class DataBundle implements Serializable {

    private static final long serialVersionUID = 8317468620973537103L;
    private HashMap<String, String> data = new HashMap<>();

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    public Boolean getBoolean(String key) {
        try {
            return getInt(key) > 0;
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Byte getByte(String key) {
        try {
            return Byte.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Short getShort(String key) {
        try {
            return Short.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getInt(String key) {
        try {
            return Integer.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Long getLong(String key) {
        try {
            return Long.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Float getFloat(String key) {
        try {
            return Float.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double getDouble(String key) {
        try {
            return Double.valueOf(data.get(key));
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String remove(String key) {
        return data.remove(key);
    }

    public HashMap<String, String> getData() {
        return data;
    }
}
