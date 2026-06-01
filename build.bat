@echo off
setlocal

set SRC_DIR=src
set BIN_DIR=bin
set SOURCES_FILE=build_sources.txt
set MAIN_CLASS=main.Main

if "%1"=="clean" goto clean
if "%1"=="run" goto run

:build
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"
echo Compiling to bin/...
javac -d "%BIN_DIR%" "@%SOURCES_FILE%"
if %errorlevel% equ 0 (
    echo Build successful! Binaries in bin/
)
goto end

:run
if not exist "%BIN_DIR%\main\Main.class" call %0 build
echo Running...
java -cp "%BIN_DIR%" %MAIN_CLASS%
goto end

:clean
if exist "%BIN_DIR%" rmdir /s /q "%BIN_DIR%"
echo Cleaned bin/
goto end

:end
endlocal
