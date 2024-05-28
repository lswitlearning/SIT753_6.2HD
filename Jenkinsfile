pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                docker.image('nginx:latest').inside {
                    sh 'cp C:/Users/cinna/OneDrive/Desktop/SIT753_Professional Practice in Information Technology/753OnTrack/6.2HD Create your DevOps Pipeline/task_6.2HD/index.html /usr/share/nginx/html/'
                }
            }
        }
    }
}

