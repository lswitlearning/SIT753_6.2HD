pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    // 使用 Docker 映像運行建置流程
                    docker.image('your-build-image:tag').inside {
                        // 在 Docker 容器內執行建置命令
                        sh 'npm install' // 這裡假設您的專案是一個 Node.js 專案
                        sh 'npm run build'
                    }
                }
            }
        }
    }
}
