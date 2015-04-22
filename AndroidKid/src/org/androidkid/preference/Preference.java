package org.androidkid.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference<T> {

    public static final String PREFERENCE_NAME = "Letsrun";
    // Please initialize context with ApplicationContext before you use it
    public static Context context;

    private final String name;
    private final Type valueType;
    private T value;

    private boolean isInitialized = false;

    public Preference(String name, T defValue) {
        super();
        this.name = name;
        try {
            this.valueType = Type.valueOf(defValue.getClass().getSimpleName());
        } catch (Exception e) {
            throw new IllegalArgumentException("Unsupported value type:" + value.getClass().getName());
        }
        this.value = defValue;
    }

    public T getValue() {
        if (!isInitialized) {
            value = getFromSharedReference(name, value);
            isInitialized = true;
        }
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        saveToSharedReference(name, value);
    }

    private T getFromSharedReference(String name, T defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        T result = null;
        switch (valueType) {
            case String:
                result = (T) settings.getString(name, (String) defaultValue);
                break;
            case Boolean:
                result = (T) Boolean.valueOf(settings.getBoolean(name, (Boolean) defaultValue));
                break;
            case Integer:
                result = (T) Integer.valueOf(settings.getInt(name, (Integer) defaultValue));
                break;
            case Long:
                result = (T) Long.valueOf(settings.getLong(name, (Long) defaultValue));
                break;
            case Float:
                result = (T) Float.valueOf(settings.getFloat(name, (Float) defaultValue));
                break;
        }
        return result;
    }

    private boolean saveToSharedReference(String name, T value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        switch (valueType) {
            case String:
                editor.putString(name, (String) value);
                break;
            case Boolean:
                editor.putBoolean(name, (Boolean) value);
                break;
            case Integer:
                editor.putInt(name, (Integer) value);
                break;
            case Long:
                editor.putLong(name, (Long) value);
                break;
            case Float:
                editor.putFloat(name, (Float) value);
                break;
        }

        return editor.commit();
    }

    enum Type {
        String, Boolean, Integer, Long, Float
    }

}
