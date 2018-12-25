#!/usr/bin/env bash

mvn clean install package

docker build -t course-edge-service:test1 .