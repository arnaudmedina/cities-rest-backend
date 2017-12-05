#!/bin/sh
dir=$(dirname "$0")
java -cp "$dir/h2-1.4.196.jar" org.h2.tools.Server
