#!/usr/bin/env bash

mvn clean install package

docker build -t course-service:test1 .