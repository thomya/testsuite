/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tcl.test;

import com.tcl.EmptyActivity;
import android.content.res.Configuration;
import android.util.Log;
import android.test.ActivityInstrumentationTestCase2;

/**
 *
 * This test uses {@link android.test.ActivityInstrumentationTestCase2} to instrument the
 * {@link com.tcl.EmptyActivity}.
 */
public class testSoftkeyboard extends ActivityInstrumentationTestCase2<EmptyActivity> {

    private EmptyActivity mActivity;

    public testSoftkeyboard() {
        super(EmptyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Start the activity and get a reference to it.
        mActivity = getActivity();
        // Wait for the UI Thread to become idle.
        getInstrumentation().waitForIdleSync();
    }

    @Override
    protected void tearDown() throws Exception {
        // Scrub the activity so it can be freed. The next time the setUp will create a new activity
        // rather than reusing the old one.
        mActivity = null;
        super.tearDown();
    }

    public void testSoftkeyboard() throws Exception {
        Configuration config = mActivity.getResources().getConfiguration();
        Log.d("testSoftkeyboard","config : " + config.toString());
        assertTrue(config.keyboard == config.KEYBOARD_NOKEYS || config.hardKeyboardHidden == config.HARDKEYBOARDHIDDEN_YES);
    }
}
