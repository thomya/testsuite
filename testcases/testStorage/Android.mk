LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

# don't include this package in any target
LOCAL_MODULE_TAGS := test

# Include both the 32 and 64 bit versions
#LOCAL_MULTILIB := both

#LOCAL_JAVA_LIBRARIES := 
LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := CtsStorageTest
LOCAL_CERTIFICATE := platform

#LOCAL_SDK_VERSION := 21

include $(BUILD_CTS_PACKAGE)

