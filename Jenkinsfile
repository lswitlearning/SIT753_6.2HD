pipeline {
    agent any

    environment{
        DOCKERHUB_CREDENTIALS = credentials('lswitlearning-dockerhub')
    }
    stages {
        stage('Clone repository') {
            steps {
                checkout scm
            }
        }

        stage('Build image') {
            steps {
                script {
                    app = docker.build("beatalam/webimage:latest")
                }
            }
        }
        stage('Run container') {
            steps {
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
                            bat """
                                ${scannerHome}\\bin\\sonar-scanner.bat ^
                                -Dsonar.projectKey=my:task ^
                                -Dsonar.projectName='My_task' ^
                                -Dsonar.projectVersion=1.0 ^
                                -Dsonar.sources=. ^
                                -Dsonar.language=html ^
                                -Dsonar.sourceEncoding=UTF-8 ^
                                -Dsonar.exclusions=**/*.java,**/*.js,**/*.css,**/*.ts,**/*.jsx,**/*.tsx ^
                                -Dsonar.login=%SONAR_TOKEN% ^
                            """
                    }
                }
            }
        }
        stage('Push image') {
            steps {
                bat 'docker push beatalam/webimage:latest'

            }
        }
        //test
        stage('Push to ECS staging') {
            steps {
                withAWS(credentials: 'jenkins_aws', region:'ap-southeast-2'){
                    bat "aws ecs update-service --cluster ecs_cluster --service webserver_stage --force-new-deployment"
                }
            }
        }
        stage('Push to ECS production') {
            steps {
                withAWS(credentials: 'jenkins_aws', region:'ap-southeast-2'){
                    bat "aws ecs update-service --cluster ecs_cluster --service webserver_prod_new --force-new-deployment"
                }
            }
        }
    }
}
