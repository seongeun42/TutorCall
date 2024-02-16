
# 자바 버전 및 정보
 - openjdk 17.0.10 2024-01-16 LTS
 - OpenJDK Runtime Environment Corretto-17.0.10.7.1 (build 17.0.10+7-LTS)
 - OpenJDK 64-Bit Server VM Corretto-17.0.10.7.1 (build 17.0.10+7-LTS, mixed mode, sharing)

<br>

# 엔진엑스
Openvidu의 2.29.0 버전에 설치 된 엔진엑스 사용
nginx version: nginx/1.23.3

<br>

# Spring boot
Spring boot 3.2.1 version
IDE 버전 - IntelliJ IDEA 2023.3.2 (Ultimate Edition)

<br>

# 특이점
젠킨스의 파이프라인 gitlab SCM을 사용한다.
빌드 시 파일 위치에 대해 신경써야한다.

## 젠킨스
젠킨스는 origin/frontend origin/backend에 머지 오픈이 되면 각각 해당 파이프 라인 실행한다.

백앤드의 application.yml 파일과 프론트앤드의 .env파일은 젠킨스 스크립트가 돌아가는 폴더 루트에 저장되어 있다.
 - #### /var/jenkins_home/workspace에 위치


## 도커와 폴더 매핑

/data/frontend/dist 젠킨스에서 도커파일로 빌드한 산출물을 저장하는 폴더

/data/ssl 엔진엑스의 ssl의 설정파일이 담겨있는 폴더

/data/nginx 엔진엑스 설정 파일

<br>

# 배포된 상황
![image](https://wockss3.s3.ap-northeast-2.amazonaws.com/cicd.png)

## 프론트 앤드 배포

/data/frontend/dist는 젠킨스와 엔진엑스가 같이 공유하는 폴더이다. 

젠킨스의 빌드 스크립트가 돌아가는 위치
nginx는 https://주소/ 로 들어오면 항상 /data/frontend/dist의 index.html을 보내준다.

프론트엔드 빌드 시 결과물인 dist폴더가 생기고, 아래 index.html과 assets 폴더가 생긴다.
젠킨스에선 frontend 브랜치의 merge 시 도커파일로 빌드 후  내부 결과물을 /data/frontend/dist로 복사해준다.
그럼 nginx에서는 프론트엔드 브랜치가 성공적으로 빌드가 된다면 자동 배포가 된다.

## 백앤드 배포
젠킨스와 ec2도커를 연결시켜 젠킨스에서 도커 실행 가능하다.

도커를 이용하여 빌드
젠킨스에서 바로 도커의 8080포트로 띄워준다.

엔진엑스는 https://wocks.me/api 로 요청이 들어오면 도커로 띄워져 있는 백앤드로 요청을 proxy_pass한다. 
https://wocks.me/api -> 서버의 localhost:8080

