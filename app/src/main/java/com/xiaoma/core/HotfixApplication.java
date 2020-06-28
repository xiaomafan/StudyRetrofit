package com.xiaoma.core;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

public class HotfixApplication extends Application {

    File apk;
    private static final String TAG = "xiaoma";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        apk = new File(getCacheDir() + "/hotfix_mine.dex");
        if (apk.exists()) {
            try {
                ClassLoader classLoader = getClassLoader();
                Log.e(TAG, "attachBaseContext: ="+classLoader.getClass().getSimpleName());
                Class loaderClass = BaseDexClassLoader.class;
                Field pathListField = loaderClass.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                Object pathListObject = pathListField.get(classLoader); // getClassLoader().pathList
                Class pathListClass = pathListObject.getClass();
                Log.e(TAG, "attachBaseContext: ="+pathListClass.getSimpleName());
                Field dexElementsField = pathListClass.getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);
                Object dexElementsObject = dexElementsField.get(pathListObject); // getClassLoader().pathList.dexElements

                // classLoader.pathList.dexElements = ???;
                PathClassLoader newClassLoader = new PathClassLoader(apk.getPath(), null);
                Object newPathListObject = pathListField.get(newClassLoader); // newClassLoader.pathList
                Object newDexElementsObject = dexElementsField.get(newPathListObject); // newClassLoader.pathList.dexElements


                dexElementsField.set(pathListObject,newDexElementsObject);

               /* int oldLength = Array.getLength(dexElementsObject);
                int newLength = Array.getLength(newDexElementsObject);
                Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
                for (int i = 0; i < newLength; i++) {
                    Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
                }
                for (int i = 0; i < oldLength; i++) {
                    Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
                }

                dexElementsField.set(pathListObject, concatDexElementsObject);*/
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
