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
                /* Run SonarQube analysis */
                script {
                    def scannerHome = tool name: 'SonarQube-Scan', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    withSonarQubeEnv('SonarQube-Scan') {
                        sh "${scannerHome}/bin/sonar-scanner \
                            -D sonar.projectKey=my:task \
                            -D sonar.projectName='My task' \
                            -D sonar.projectVersion=1.0 \
                            -D sonar.sources=. \
                            -D sonar.language=html \
                            -D sonar.sourceEncoding=UTF-8 \
                            -D sonar.exclusions='**/*.java,**/*.js,**/*.css,**/*.ts,**/*.jsx,**/*.tsx'"
                            -D sonar.login=sqp_797d4fef5fe5244be962f4c6e2c1eaedf2927a79
                    }
                }
            }
        }
    }
}