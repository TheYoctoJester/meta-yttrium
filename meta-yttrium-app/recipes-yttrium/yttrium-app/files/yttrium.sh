#!/bin/bash

cd /sys/class/gpio

echo @YLED1@ > export
echo @YLED2@ > export
echo @YLED3@ > export

echo out > gpio@YLED1@/direction
echo out > gpio@YLED2@/direction
echo out > gpio@YLED3@/direction

echo "Yttrium development version"

while true; do
	echo 1 > gpio@YLED1@/value
	sleep 1
	echo 1 > gpio@YLED2@/value
	sleep 1
	echo 1 > gpio@YLED3@/value
	sleep 1
	echo 0 > gpio@YLED1@/value
	sleep 1
	echo 0 > gpio@YLED2@/value
	sleep 1 
	echo 0 > gpio@YLED3@/value
	sleep 1
done
