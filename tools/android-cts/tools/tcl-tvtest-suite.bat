@echo    
ECHO " **********  TCL TV Test Suite  ***********" 
ECHO " please assure adbd of your TV is on State(or USB Debug mode) . " 

set /p tv_ip="please input your tv ip address : "
adb connect %tv_ip%
adb root
choice /t 5 /d y /n >nul

adb connect %tv_ip%
adb shell -n setprop persist.tcl.debug.installapk 1
adb push D:\projects\2017\²âÊÔÌ×¼ş\android-cts\tools\fillData.sh /data/fillData.sh
adb push D:\projects\2017\²âÊÔÌ×¼ş\android-cts\tools\teardown.sh /data/teardown.sh
adb shell chown system:system /data/fillData.sh /data/teardown.sh
adb shell chmod 755 /data/fillData.sh /data/teardown.sh

ECHO " ******  start cts console   **********  "
CALL D:\projects\2017\²âÊÔÌ×¼ş\android-cts\tools\cts-tradefed.bat
pause