@echo off
REM Gradle wrapper for Windows - configured for Gradle 8.4
set DIRNAME=%~dp0
set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar
java -jar "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
