pipeline {
    agent any

    environment{
        DOCKERHUB_CREDENTIALS = credentials('lswitlearning-dockerhub')
    }
    stages {
        stage('Clone repository') {
            steps {
                /* Let's make sure we have the repository cloned to our workspace */
                checkout scm
            }
        }

        stage('Build image') {
            steps {
                /* This builds the actual image; synonymous to
                 * docker build on the command line */
                script {
                    app = docker.build("beatalam/webimage:latest")
                }
            }
        }
        stage('Run container') {
            steps {
                /* This runs the built image with port mapping; synonymous to
                 * docker run -p 80:80 webimage:latest on the command line */
                script {
                    app.run("-p 80:80")
                }
            }
        }
        stage('Test') {
            steps {
                bat "mvn -D clean test"
            }
        }
        stage('AWS test') {
            steps {
                bat "aws s3 ls"
            }
        }
    }
}