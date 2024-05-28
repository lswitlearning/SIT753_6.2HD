pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    // 使用 Docker 映像運行建置流程
                    docker.image('nginx:latest').inside {
                        // 將工作目錄設置為絕對路徑
                        dir('C:\\Users\\cinna\\OneDrive\\Desktop\\SIT753_Professional Practice in Information Technology\\753OnTrack\\6.2HD Create your DevOps Pipeline\\task_6.2HD') {
                            // 在 Docker 容器內執行建置命令
                            sh 'cp -r ./* /usr/share/nginx/html/'
                        }
                    }
                }
            }
        }
    }
}
