@echo   
setlocal enabledelayedexpansion  
setlocal ENABLEEXTENSIONS  
  
  
set CTS_ROOT=D:\projects\2017\²âÊÔÌ×¼þ
 
set JAR_DIR=%CTS_ROOT%\android-cts\tools
set JARS=cts-tradefed.jar hosttestlib.jar tradefed-prebuilt.jar
::cts-tradefed.jar hosttestlib.jar 
  
set JAR_PATH= 
::tradefed-prebuilt.jar 
for %%i in (%JARS%) do (  
    set JAR_PATH=!JAR_PATH!%JAR_DIR%\%%i  
)  
java %RDBG_FLAG% -cp %JAR_DIR%\* -DCTS_ROOT=%CTS_ROOT% com.android.cts.tradefed.command.CtsConsole %*  
  
pause  