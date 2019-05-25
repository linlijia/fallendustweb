#!/usr/bin/env bash
SOURCE_PATH=$(cd "$(dirname "${BASH_SOURCE-$0}")"; pwd)
APP_NAME=dust.jar
CMD="java -Xms512m -Xmx1024m -jar $SOURCE_PATH/$APP_NAME"
usage(){
    echo "Usage: sh 脚本名.sh [start|stop|restart|status]"
    exit 1
}

is_exist(){
    pid=`ps -ef|grep "$CMD"|grep -v grep|awk '{print $2}' `
    if [ -z "${pid}" ]; then
        return 1
    else
        return 0
    fi
}

start(){
    is_exist
    if [ $? -eq "0" ]; then
        echo "${APP_NAME} is already running. pid=${pid} ."
    else
        nohup $CMD  > app.log 2>&1 &
        echo "${APP_NAME} start success"
    fi
}

stop(){
    is_exist
    if [ $? -eq "0" ]; then
        kill -9 $pid
        echo "stop sucess"
    else
        echo "${APP_NAME} is not running"
    fi
}

status(){
    is_exist
    if [ $? -eq "0" ]; then
        echo "${APP_NAME} is running. Pid is ${pid}"
    else
        echo "${APP_NAME} is NOT running."
    fi
}

restart(){
    stop
    start
}

case "$1" in
    "build")
    rm -rf dist/
    mkdir dist
    mvn clean install
    cp control.sh target/$APP_NAME dist/
    ;;
    "start")
    start
    ;;
    "stop")
    stop
    ;;
    "status")
    status
    ;;
    "restart")
    restart
    ;;
    *)
    usage
    ;;
esac

