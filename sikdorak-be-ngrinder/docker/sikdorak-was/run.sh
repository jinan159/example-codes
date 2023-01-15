#!/usr/bin/env bash
CONTROLLER_HOST_NAME=$1
shift

if [ -z "$CONTROLLER_HOST_NAME" ];
then
  echo "Controller:port should be provided as an argument to download an agent"
  CONTROLLER_HOST_NAME="controller:80"
  echo "Use controller:80 as a default"
fi

MONITOR_DOWNLOAD_URL="http://$CONTROLLER_HOST_NAME/monitor/download"

APP_BASE_DIR=/app

cd $APP_BASE_DIR

for i in {1..30};
do
  wget -O $APP_BASE_DIR/ngrinder-monitor.tar -T 60 $MONITOR_DOWNLOAD_URL && break || sleep 10
done

if [ ! -f "$APP_BASE_DIR/ngrinder-monitor.tar" ];
then
  echo "Fail to download an monitor file from "$MONITOR_DOWNLOAD_URL
  exit 1
else
  tar -xvf $APP_BASE_DIR/ngrinder-monitor.tar
  MONITOR_DIR=$APP_BASE_DIR/ngrinder-monitor
  echo "Run $MONITOR_DIR/run_monitor.sh on background"
  nohup $MONITOR_DIR/run_monitor.sh > monitor.log 2>&1 &
fi

if [ ! -f "$APP_BASE_DIR/sikdorak.jar" ];
then
  echo "Application start faild: fild not exists"
  exit 1
else
  echo "Application $APP_BASE_DIR/sikdorak.jar starting..."
  java -jar $APP_BASE_DIR/sikdorak.jar -Xms512m -Xmx1024m
fi
