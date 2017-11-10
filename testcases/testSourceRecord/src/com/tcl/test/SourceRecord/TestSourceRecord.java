package com.tcl.test.SourceRecord;

import android.util.Log;
import java.lang.reflect.Method;
import android.test.AndroidTestCase;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.ComponentName;

import android.hardware.input.InputManager;
import android.view.KeyEvent;
import android.view.InputEvent;
import android.os.SystemClock;

public class TestSourceRecord extends AndroidTestCase{
    private static final String TAG = "TestSourceRecord";
    private ActivityManager mAm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAm = (ActivityManager)(getContext().getSystemService(Context.ACTIVITY_SERVICE));
        long now = SystemClock.uptimeMillis();
        KeyEvent down = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HOME, 0,0,-1,65,0,0);
        KeyEvent up = new KeyEvent(now, now, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HOME, 0,0,-1,65,0,0);
        try{
            Class cls = InputManager.class;
            Method instance = cls.getMethod("getInstance",null);
            Object inputManager = instance.invoke(null);
            Method injectEvent = cls.getMethod("injectInputEvent",InputEvent.class,int.class);
            injectEvent.invoke(inputManager,down,2);
            injectEvent.invoke(inputManager,up,2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void testAmNotNull(){
        assertTrue(mAm != null);
    }

    public void testSourceRecord(){
        assertTrue(getRecordSource().equals(getTopActivity()));
        long now = SystemClock.uptimeMillis();
        KeyEvent down = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HOME, 0,0,-1,65,0,0);
        KeyEvent up = new KeyEvent(now, now, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HOME, 0,0,-1,65,0,0);
        try{
            Class cls = InputManager.class;
            Method instance = cls.getMethod("getInstance",null);
            Object inputManager = instance.invoke(null);
            Method injectEvent = cls.getMethod("injectInputEvent",InputEvent.class,int.class);
            injectEvent.invoke(inputManager,down,2);
            injectEvent.invoke(inputManager,up,2);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(500);
        }catch(Exception e){
            e.printStackTrace();
        }

        assertTrue(getRecordSource().equals(getTopActivity()));
    }

    private String getRecordSource(){
        String value = "";
        try{
            Class cls = Class.forName("android.os.SystemProperties");
            Method getprop = cls.getMethod("get",String.class,String.class);
            value = (String)getprop.invoke(null,"persist.sys.bootfromui","");
        }catch(Exception e ){
            e.printStackTrace();
        }
        Log.d(TAG,"getRecordSource() : " + value);
        return value;
    }

    private String getTopActivity(){
        String packageName = "";
        ComponentName cn = mAm.getRunningTasks(1).get(0).topActivity;
        if(cn != null){
            packageName = cn.getPackageName();
            Log.d(TAG,"getTopActivity : " + packageName);
        }else{
            Log.d(TAG,"getTopActivity failed .");
        }
        return packageName.equals("com.tcl.tv") ? "tv" : "ui";
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
