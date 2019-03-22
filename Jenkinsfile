#!/usr/bin/env groovy

node('master') {
    try {
        stage('build') {
            checkout scm

            sh "mvn clean package"
            sh "docker build -t jframework ."
            sh "docker tag jframework:latest registry.cn-hangzhou.aliyuncs.com/suxiaolin/jframework:latest"
            sh "docker push registry.cn-hangzhou.aliyuncs.com/suxiaolin/jframework:latest"
        }

        stage('deploy') {
            sh "rancher kubectl apply -f k8s.yml"
        }
    } catch(error) {
        throw error
    } finally {

    }
}