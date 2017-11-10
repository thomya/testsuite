package com.tcl.test;

import android.util.Log;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.provider.Settings;
import android.test.AndroidTestCase;

public class TestScreenOff extends AndroidTestCase{
    private static final String TAG = "TestScreenOffConfig";
    private static final int DEFAULT_SLEEP_TIMEOUT = -1;
    private static final int DEFAULT_SCREEN_OFF_TIMEOUT = 15 * 1000;
    private static final int DAY = 24 * 60 * 60 * 1000;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testScreenOffTime(){
        assertTrue(getScreenOffTimeout() > DAY);
    }

    public void testSleepTime(){
        assertTrue(getSleepTimeout() > DAY);
    }

    private int getScreenOffTimeout(){
        ContentResolver resolver = getContext().getContentResolver();
        Resources resources = getContext().getResources();
        int timeout = Settings.System.getInt(resolver,Settings.System.SCREEN_OFF_TIMEOUT, DEFAULT_SLEEP_TIMEOUT);
        Log.d(TAG,"screen off timeout : " + timeout);
        if (timeout <= 0) {
            return -1;
        }
        int id = Resources.getSystem().getIdentifier("config_minimumScreenOffTimeout","integer","android");
        int minimumScreenOffTimeoutConfig = resources.getInteger(id);
        Log.d(TAG,"minimum screen off timeout : " + minimumScreenOffTimeoutConfig);

        return Math.max(timeout, minimumScreenOffTimeoutConfig);

    }

    private int getSleepTimeout(){
        ContentResolver resolver = getContext().getContentResolver();
        Resources resources = getContext().getResources();
        int timeout = Settings.Secure.getInt(resolver,"sleep_timeout",-1);
        Log.d(TAG,"sleep timeout : " + timeout);
        if (timeout <= 0) {
            return -1;
        }
        int id = Resources.getSystem().getIdentifier("config_minimumScreenOffTimeout","integer","android");
        int minimumScreenOffTimeoutConfig = resources.getInteger(id);
        Log.d(TAG,"minimum screen off timeout : " + minimumScreenOffTimeoutConfig);

        return Math.max(timeout, minimumScreenOffTimeoutConfig);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    
    }
}
