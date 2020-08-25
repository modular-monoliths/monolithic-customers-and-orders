#!/bin/bash -e

./gradlew testClasses

./gradlew mysqlComposeUp

./gradlew -x :end-to-end-tests:test build

./gradlew applicationComposeUp

./gradlew :end-to-end-tests:cleanTest  :end-to-end-tests:test

./gradlew applicationComposeDown


