#!/usr/bin/env bash

mvn clean install package

docker build -t user-edge-service:test1 .