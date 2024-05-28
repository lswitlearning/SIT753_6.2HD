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
            // 使用 PowerShell 命令获取要停止的容器 ID
            def containerIds = powershell(script: 'docker ps -q --filter "ancestor=webimage:latest"', returnStdout: true).trim().split("\\r?\\n")

            // 停止容器
            for (containerId in containerIds) {
                bat "docker stop ${containerId}"
            }
        }
    }
}



 
    }
}

