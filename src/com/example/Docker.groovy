#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin() {
        script.echo "Login to DockerHub"
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }

    def dockerLoginInToHost(String user, String password, String host) {
        script.sh "echo $password | docker login -u $user --password-stdin ${host}"
    }

    def dockerImagePush(String imageName) {
        script.echo "Pushing the docker image"
        script.sh "docker push $imageName"
    }
}


