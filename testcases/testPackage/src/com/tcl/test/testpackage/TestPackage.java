package com.tcl.test.testpackage;

import android.util.Log;
import java.lang.reflect.Method;
import android.test.AndroidTestCase;

public class TestPackage extends AndroidTestCase{
    private static final String TAG="TestPackage";

    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }

    public void testDisableThirdpartAppInstall(){
        String value = "";
        try{
            Class systemProperty = Class.forName("android.os.SystemProperties");
            Method get = systemProperty.getMethod("get",String.class,String.class);
            value = (String)get.invoke(null,"persist.tcl.installapk.enable","0");
            Log.d(TAG,"persist.tcl.installapk.enable : " + value);
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean ret = "0".equals(value);
        assertTrue(ret);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
