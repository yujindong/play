#!/usr/bin/env bash

mvn clean install package

docker build -t user-service:test1 .