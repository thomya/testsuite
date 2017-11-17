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
    private static final String BOOTFROMUI = "persist.sys.bootfromui";
    private static final String BOOTPACKAGE = "persist.sys.bootpackage";
    private ActivityManager mAm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAm = (ActivityManager)(getContext().getSystemService(Context.ACTIVITY_SERVICE));
        injectKey(KeyEvent.KEYCODE_HOME);
    }

    public void testAmNotNull(){
        assertTrue(mAm != null);
    }

    public void testSourceRecord(){
        String bootfromui = "";
        String bootTo = "";
        String TopActivity = "";

        bootfromui = getProperty(BOOTFROMUI);
        TopActivity = getTopActivity();
        assertTrue(!"".equals(TopActivity));
        bootTo = TopActivity.equals("com.tcl.tv") ? "tv" : "ui";

        assertTrue(!"".equals(bootfromui));
        assertTrue(!"".equals(bootTo));
        assertTrue(bootfromui.equals(bootTo));
        
        injectKey(KeyEvent.KEYCODE_HOME);
        
        try{
            Thread.sleep(500);
        }catch(Exception e){
            e.printStackTrace();
        }

        bootfromui = getProperty(BOOTFROMUI);
        TopActivity = getTopActivity();
        assertTrue(!"".equals(TopActivity));
        bootTo = TopActivity.equals("com.tcl.tv") ? "tv" : "ui";

        assertTrue(!"".equals(bootfromui));
        assertTrue(!"".equals(bootTo));
        assertTrue(bootfromui.equals(bootTo));
    
    }

    public void testCommonSourceRecord(){
        String bootfromui = "";
        String bootPackage = "";
        String bootTo = "";
        String TopActivity = "";

        bootfromui = getProperty(BOOTFROMUI);
        bootPackage = getProperty(BOOTPACKAGE);
        TopActivity = getTopActivity();
        assertTrue(!"".equals(TopActivity));
        bootTo = TopActivity.equals("com.tcl.tv") ? "tv" : "ui";

        assertTrue(!"".equals(bootfromui));
        assertTrue(!"".equals(bootPackage));
        assertTrue(!"".equals(bootTo));
        assertTrue(bootfromui.equals(bootTo));
        assertTrue(bootPackage.equals(TopActivity));
        
        injectKey(KeyEvent.KEYCODE_HOME);
        
        try{
            Thread.sleep(500);
        }catch(Exception e){
            e.printStackTrace();
        }

        bootfromui = getProperty(BOOTFROMUI);
        bootPackage = getProperty(BOOTPACKAGE);
        TopActivity = getTopActivity();
        assertTrue(!"".equals(TopActivity));
        bootTo = TopActivity.equals("com.tcl.tv") ? "tv" : "ui";

        assertTrue(!"".equals(bootfromui));
        assertTrue(!"".equals(bootPackage));
        assertTrue(!"".equals(bootTo));
        assertTrue(bootfromui.equals(bootTo));
        assertTrue(bootPackage.equals(TopActivity));
    }

    private String getProperty(String key){
        String value = "";
        try{
            Class cls = Class.forName("android.os.SystemProperties");
            Method getprop = cls.getMethod("get",String.class,String.class);
            value = (String)getprop.invoke(null,key,"");
        }catch(Exception e ){
            e.printStackTrace();
        }
        Log.d(TAG,"getproperty : [ " + key + " , " + value + " ];");
        return value;
    }

    private void injectKey(int keyCode){
        long now = SystemClock.uptimeMillis();
        KeyEvent down = new KeyEvent(now, now, KeyEvent.ACTION_DOWN,keyCode , 0,0,-1,65,0,0);
        Class cls;
        Method instance;
        Object inputManager;
        Method injectEvent;
        try{
            cls = InputManager.class;
            instance = cls.getMethod("getInstance",null);
            inputManager = instance.invoke(null);
            injectEvent = cls.getMethod("injectInputEvent",InputEvent.class,int.class);
            injectEvent.invoke(inputManager,down,2);
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }

        now = SystemClock.uptimeMillis();
        KeyEvent up = new KeyEvent(now, now, KeyEvent.ACTION_UP, keyCode, 0,0,-1,65,0,0);
        try{
            cls = InputManager.class;
            instance = cls.getMethod("getInstance",null);
            inputManager = instance.invoke(null);
            injectEvent = cls.getMethod("injectInputEvent",InputEvent.class,int.class);
            injectEvent.invoke(inputManager,up,2);
        }catch(Exception e){
            e.printStackTrace();
        }
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
        return packageName;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
