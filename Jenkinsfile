pipeline {
    agent any
    environment {
        BACKEND = 'backend'
        FRONTEND = 'frontend'
        TAG = 'test'
        RENAME_JAR = 'backend'
        BUILD_CMD = 'bootJar'
        WORKSPACE_DIR = ''
        TEST_BUILD_CMD = 'bootJar'

    }
   
    stages {
         stage('프론트 변수 세팅') { // application.yml이 있는 경우 삭제
            when {
                expression { env.GIT_BRANCH == 'origin/frontend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {								
                sh 'cp ../.env frontend'
            }
            post {
		           success {
                        echo "프론트 환경변수 세팅 성공"
                    }
                    failure{
                        echo "프론트 환경변수 실패 성공"
                    }
            }
        }
        stage('프론트 빌드') {
            when {
                expression { env.GIT_BRANCH == 'origin/frontend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                dir('frontend'){
                    sh "docker build -t frontend ."
                    script {
                        try {
                            sh "docker rm -f \$(docker ps -aqf \"name=^/${env.FRONTEND}\$\")"
                        } catch (Exception e) {
                            echo "컨테이너 제거 중 에러가 발생했습니다: ${e.getMessage()}"
                        }
                    }
                    sh "docker run --name frontend frontend"
                    sh "docker cp frontend:/app/dist /data/frontend"
                    sh "docker exec openvidu_nginx_1 nginx -s reload"
                }
            }
            post {
                success {
                    updateGitlabCommitStatus name: 'build', state: 'success'
                    echo "build 성공"
                }
                failure {
                    updateGitlabCommitStatus name: 'build', state: 'failed'
                    echo "build 실패"
                }
            }
        }
        stage('application.yml 삭제') { // application.yml이 있는 경우 삭제
            when {
                expression { env.GIT_BRANCH == 'origin/backend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {								
                sh 'rm -rf ./backend/src/main/resources'
                sh 'mkdir ./backend/src/main/resources'
                sh 'cp ../application.yml backend/src/main/resources'
                sh 'cd ./backend/src/main/resources && ls -al'
            }
            post {
		           success {
                        echo "application.yml 복사 성공"
                    }
                    failure{
                        echo "application.yml 복사 실패"

                    }
            }
        }
        stage('백앤드 도커 빌드') {
            when {
                expression { env.GIT_BRANCH == 'origin/backend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {								
                dir('backend'){
                    sh "docker build -t ${env.BACKEND} ."
                }
            }
            post {
                success {
                    echo "백앤드 도커 빌드 성공"
                }
                failure{
                    echo "백앤드 도커 빌드 실패"
                }
            }
        }
        
        stage('백앤드 도커 띄우기') { // 테스트 빌드하기
            when {
                expression { env.GIT_BRANCH == 'origin/backend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                 script {
                        try {
                            
                            sh "docker rm -f \$(docker ps -aqf \"name=^/${env.BACKEND}\$\")"
                        } catch (Exception e) {
                            echo "컨테이너 제거 중 에러가 발생했습니다: ${e.getMessage()}"
                        }
                    }
                sh "docker run -itd -p 8080:8080 -v /data/image:/data/image --name ${env.BACKEND} ${env.BACKEND}"
            }
            post {
                success {
                    updateGitlabCommitStatus name: 'build', state: 'success'
                    echo "시작 성공"
                }
                failure {
                    updateGitlabCommitStatus name: 'build', state: 'failed'
                    echo "시작 실패"
                }
            }
        }
    }
}
