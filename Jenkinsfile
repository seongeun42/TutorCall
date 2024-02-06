pipeline {
    agent any
    environment {
        BACKEND = 'backend'
        TAG = 'test'
        RENAME_JAR = 'backend'
        BUILD_CMD = 'bootJar'
        WORKSPACE_DIR = ''
        TEST_BUILD_CMD = 'bootJar'
    }
   
    stages {
        stage('프론트 테스트 빌드') {
            when {
                expression { env.GIT_BRANCH == 'origin/frontend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {								
                dir('frontend'){
                    sh 'npm i'
                    sh 'npm run build'
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
        
        stage('테스트 빌드하기') { // 테스트 빌드하기
            when {
                expression { env.GIT_BRANCH == 'origin/backend' || env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                dir('backend') {
                    updateGitlabCommitStatus name: 'build', state: 'running'

                    sh 'chmod +x ./gradlew'
                    sh "./gradlew ${env.BUILD_CMD}"
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
        
        stage('컨테이너 삭제') {
            when {
                expression { env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                script {
                    try {
                        sh 'docker image prune -a -f'
												sh "docker rm -f \$(docker ps -aqf \"name=^/${env.BACKEND}\$\")"
												sh "docker rmi ${env.BACKEND}"
                    } catch(e) {
                        echo 'docker clear fail!'
                    }
                }
            }
        }
        
        stage('배포') {
            when {
                expression { env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                sh 'docker build -t ${env.TAG}:v1.0 .'
                sh "docker run -d -p 8088:8080 --name ${env.BACKEND} ${env.TAG}:v1.0"
            }
            post {
                success {
                    echo "성공적으로 배포되었습니다."
                }
                failure {
                    echo "배포 실패"
                }
            }
        }
    }
}
