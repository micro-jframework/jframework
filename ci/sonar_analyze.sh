#!/bin/bash

mvn clean install -Dmaven.test.skip=true

mvn --batch-mode sonar:sonar \
    -Dsonar.host.url=http://172.16.67.160:9000 \
    -Dsonar.login=e6ece14822a5d124966dff427f5f13b1ae614101 \
    -Dsonar.issuesReport.html.enable=true \
    -Dsonar.java.binaries=./target/classes/ \


if [ $? -eq 0 ]; then
    echo "sonarqube code-analyze over."
fi
