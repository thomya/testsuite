LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

# don't include this package in any target
LOCAL_MODULE_TAGS := tests

# Include both the 32 and 64 bit versions
LOCAL_MULTILIB := both

#LOCAL_STATIC_JAVA_LIBRARIES := ctstestrunner ctsdeviceutil
LOCAL_JAVA_LIBRARIES := com.tcl.deviceinfo
LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := CtsTvinfoTest

LOCAL_SDK_VERSION := 16

include $(BUILD_CTS_PACKAGE)

