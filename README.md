# Tutor Call

## Git Convention
### 커밋메시지 템플릿 적용법

* 레포지토리에 있는 .gitmessage.txt를 아래와 같이 적용
```
git config commit.template .gitmessage.txt
```

## 자바 auto lint

* 프로젝트의 .git/hooks/ 폴더 안에 **pre-commit** 파일 생성
```
// bash에서
cd .git/hooks/

touch pre-commit

vim pre-commmit
```

* pre-commit 파일에 아래의 스크립트를 입력
```
#!/bin/sh
stagedFiles=$(git diff --staged --name-only)

# Backend auto formatting
echo "Formatting Backend code..."

echo "Running Google Java Format..."

# Google Java 형식을 사용하여 사용하지 않는 import제거
java -jar "google-java-format-1.19.2-all-deps.jar" -i $(git diff --cached --name-only --diff-filter=ACM -- '*.java' | tr '\n' ' ')

# FrontEnd auto formatting
echo "Formatting Frontend code..."

cd frontend/

# Prettier 실행
echo "Runnig Prettier..."
npm run format

# ESLint 실행
echo "Running ESLint..."
npm run lint

# 수정된 파일들 git add
cd ..

# for file in $stagedFiles; do
#  if test -f "$file"; then
#    git add $file
#  fi
# done

git add .
```
edit


* pre-commit 실행 권한 부여
```
chmod -x .git/hooks/pre-commit
```
