package com.tcl.test.tvinfo;

import android.util.Log;
import com.tcl.deviceinfo.TDeviceInfo;
import android.test.AndroidTestCase;;


public class TestTvinfo extends AndroidTestCase {
    private static final String TAG = "TvinfoTest";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
    }
    
    public void testProjectId(){
        TDeviceInfo tdevice = TDeviceInfo.getInstance();
        int projectid = tdevice.getProjectID();
        assertTrue(projectid > 0);
    }
    public void testDeviceId(){
        TDeviceInfo tdevice = TDeviceInfo.getInstance();
        String deviceid = tdevice.getDeviceID();
        assertNull(deviceid);
    }
}
