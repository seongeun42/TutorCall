# Git Convention
## 커밋메시지 템플릿 적용법

* 레포지토리에 있는 .gitmessage.txt를 아래와 같이 적용
```
git config commit.template .gitmessage.txt
```
test
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
# Part 1
stagedFiles=$(git diff --staged --name-only)

# Part 2
echo "Running google-java-format. Formatting code..."
# Google Java 형식을 사용해 auto lint
java -jar "google-java-format-1.19.2-all-deps.jar" -i $(git diff --cached --name-only --diff-filter=ACM -- '*.java' | tr '\n' ' ')

# Part 3
for file in $stagedFiles; do
  if test -f "$file"; then
    git add $file
  fi
done
```


* pre-commit 실행 권한 부여
```
chmod -x .git/hooks/pre-commit
```
