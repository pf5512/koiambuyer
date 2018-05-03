package com.hc360.koiambuyer.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by huang on 2017/8/28.
 */

public class ThreadUtil {
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public static void toToOnSubThread(Runnable runnable){
        EXECUTOR.execute(runnable);
    }
    public static void toToOnMainThread(Runnable runnable){
        HANDLER.post(runnable);
    }

}
