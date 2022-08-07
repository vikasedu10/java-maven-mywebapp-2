def gv

pipeline {
    agent any

    tools {
        maven "maven-3.8"
    }

    environment {
        IMAGE_NAME = "vikas1412/mywebapp:1.0"
        EC2_USERNAME = "ubuntu"
        IP_ADDRESS = "13.233.156.218"
    }
    stages {
        stage("Initialize groovy") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("Building app") {
            steps {
                script {
                    gv.buildApp()
                    gv.buildImage()
                }
            }
        }

        stage("Testing app") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }

        stage("Deploy to docker-hub") {
            steps {
                script {
                    gv.deployImage()
                }
            }
        }
    }
}