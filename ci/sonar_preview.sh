#!/bin/bash

mvn clean install -Dmaven.test.skip=true

mvn --batch-mode verify sonar:sonar \
    -Dsonar.host.url=http://172.16.67.160:9000 \
    -Dsonar.login=e6ece14822a5d124966dff427f5f13b1ae614101 \
    -Dsonar.analysis.mode=preview \
    -Dsonar.gitlab.project_id=$CI_PROJECT_ID \
    -Dsonar.gitlab.commit_sha=$CI_COMMIT_SHA \
    -Dsonar.gitlab.ref_name=$CI_COMMIT_REF_NAME
    -Dsonar.java.binaries=./target/classes/ \


if [ $? -eq 0 ]; then
    echo "sonarqube code-analyze-preview over."
fi
