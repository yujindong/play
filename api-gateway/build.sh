#!/usr/bin/env bash

mvn clean install package

docker build -t api-gateway:test1 .