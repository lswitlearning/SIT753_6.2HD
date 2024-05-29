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
        stage('SonarQube Code Analysis') {
            steps {
                script {
                    def scannerHome = tool name: 'SonarQube-Scan', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    withSonarQubeEnv('SonarServer') {
                        withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                            bat """
                                ${scannerHome}\\bin\\sonar-scanner.bat ^
                                -Dsonar.projectKey=my\\:task ^
                                -Dsonar.projectName='My\\ task' ^
                                -Dsonar.projectVersion=1.0 ^
                                -Dsonar.sources=. ^
                                -Dsonar.language=html ^
                                -Dsonar.sourceEncoding=UTF-8 ^
                                -Dsonar.exclusions=**/*.java,**/*.js,**/*.css,**/*.ts,**/*.jsx,**/*.tsx ^
                                -Dsonar.login=%SONAR_TOKEN%
                            """
                        }
                    }
                }
            }
        }
    }
}