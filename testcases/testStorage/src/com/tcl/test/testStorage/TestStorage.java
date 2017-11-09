package com.tcl.test.testStorage;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import android.util.Log;
import android.content.Context;
import android.os.storage.StorageManager;
import android.os.StatFs;
import android.os.SystemProperties;
import android.os.Environment;
import android.test.AndroidTestCase;;


public class TestStorage extends AndroidTestCase {
    private static final String TAG = "TestStorage";

    private static final long _500MB = 500*1024*2014;
    private static final long _16MB = 16*1024*2014;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        long fullThreshold = getStorageManager().getStorageLowBytes(Environment.getDataDirectory()) / 4;
        SystemProperties.set("debug.freemem",String.valueOf(fullThreshold - 1024));
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Log.d(TAG,"tearDown begin .");
       /* try{
            Process proc = Runtime.getRuntime().exec("/data/teardown.sh");
            proc.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }*/
        SystemProperties.set("debug.freemem","");
        Log.d(TAG,"teardown end .");
    }
    
    private StorageManager getStorageManager(){
        return (StorageManager)getContext().getSystemService(StorageManager.class);
    }

    public void testDataLowThresholdValue(){
        
        long threshold = getStorageManager().getStorageLowBytes(Environment.getDataDirectory());
        Log.i(TAG,"StorageManager low threshold value : " + threshold );
        StatFs dataFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long dataSize10_1 = dataFs.getBlockSizeLong() * dataFs.getBlockCountLong() / 10;
        threshold = dataSize10_1 > _500MB ? _500MB : threshold;
        Log.i(TAG,"StorageManager threshold = " + threshold + " ; data size 1/10 : " + dataSize10_1 );
        assertTrue(threshold > _16MB);
        assertTrue(threshold <= _500MB);
    }
    public void testDataFullThresholdValue(){
        long threshold = getStorageManager().getStorageFullBytes(Environment.getDataDirectory());
        Log.i(TAG,"StorageManager full threshold value : " + threshold );

        assertTrue(threshold > _16MB);
    }

    private void writeToDataFullThreshold(long blocks){
        Log.d(TAG,"writeToDataFullThreshold blocks : " + blocks);
        try{
            Process proc = Runtime.getRuntime().exec("/data/fillData.sh " + blocks + " 1 > /data/log.txt");
           // Process proc = Runtime.getRuntime().exec("dd if=/dev/zero of=/data/tmp1.txt bs=4096 count=" + blocks/12 );
            proc.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.d(TAG,"writeToDataFullThreshold end .");
    }

    private boolean writeSomeBytes(){
        boolean ret = false;
        File f = new File(Environment.getDataDirectory() + "/testStorage.txt");
        try{
            FileOutputStream fout = new FileOutputStream(f);
            fout.write(new byte[]{'t','e','s','t',' ','s','t','o','r','a','g','e','\n'});
            fout.flush();
            fout.close();
            ret = true;
        }catch(Exception e){
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

/*    public void testDataFullDisableWrite(){
        long fullThreshold = getStorageManager().getStorageLowBytes(Environment.getDataDirectory()) / 4;
        Log.i(TAG,"testDataFullDisableWrite fullThreshold value : " + fullThreshold );
        StatFs dataFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long dataBlocks = dataFs.getBlockCountLong();
        writeToDataFullThreshold(dataBlocks - fullThreshold/dataFs.getBlockSizeLong() + 1);
        assertFalse(writeSomeBytes());    

    }*/

    public void testDebugFullDisableWrite(){
        Log.i(TAG,"testDebugFullDisableWrite .");
        try{
            Thread.sleep(1*60*1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        assertFalse(writeSomeBytes());
    }
}
