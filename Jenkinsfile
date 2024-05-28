pipeline {
    agent any

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
                    app = docker.build("webimage:latest")
                }
            }
        }
        stage('Run container') {
            steps {
                script {
                    // 停止并删除之前运行的容器，避免端口冲突
                    bat 'for /F "tokens=*" %i IN (\'docker ps -q --filter ancestor=webimage:latest\') DO docker stop %i'
                    bat 'for /F "tokens=*" %i IN (\'docker ps -aq --filter ancestor=webimage:latest\') DO docker rm %i'
                    
                    // 运行新的容器
                    app.run("-p 80:80")
                }
            }
        }
    }
}

