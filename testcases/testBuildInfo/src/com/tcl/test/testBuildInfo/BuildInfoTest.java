package com.tcl.test.testBuildInfo;

import android.os.Build;
import android.util.Log;
import android.test.AndroidTestCase;


public class BuildInfoTest extends AndroidTestCase {

    private static final String TAG = "BuildInfoTest";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    /**
     * Asserts that a String is non-null and non-empty.  If it is not,
     * an AssertionFailedError is thrown with the given message.
     */
    private static void assertNotEmpty(String message, String string) {
        Log.d(TAG, "" + message + ": " + string);
        assertNotNull(message, string);
        assertFalse(message, string.equals(""));
    }

    /**
     * Asserts that a String is non-null and non-empty.  If it is not,
     * an AssertionFailedError is thrown.
     */
    private static void assertNotEmpty(String string) {
        assertNotEmpty(null, string);
    }

    /**
     * Asserts that all android.os.Build fields are non-empty and/or in a valid range.
     */
    public void testBuildFields() throws Exception {
        assertNotEmpty("ID", Build.ID);
        assertNotEmpty("DISPLAY", Build.DISPLAY);
        assertNotEmpty("PRODUCT", Build.PRODUCT);
        assertNotEmpty("DEVICE", Build.DEVICE);
        assertNotEmpty("BOARD", Build.BOARD);
        assertNotEmpty("BRAND", Build.BRAND);
        assertNotEmpty("MODEL", Build.MODEL);
        assertNotEmpty("VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL);
        assertNotEmpty("VERSION.RELEASE", Build.VERSION.RELEASE);
        assertNotEmpty("TYPE", Build.TYPE);
        assertNotNull("TAGS", Build.TAGS); // TAGS is allowed to be empty.
        assertNotEmpty("FINGERPRINT", Build.FINGERPRINT);
        assertTrue("TIME", Build.TIME > 0);
        assertNotEmpty("USER", Build.USER);
        assertNotEmpty("HOST", Build.HOST);

    }
}
