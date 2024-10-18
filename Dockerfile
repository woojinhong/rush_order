# OpenJDK 21 이미지 사용
FROM openjdk:21-slim AS builder

# Gradle 설치
RUN apt-get update && apt-get install -y curl unzip && \
    curl -Lo gradle.zip https://services.gradle.org/distributions/gradle-8.4-bin.zip && \
    unzip gradle.zip -d /opt && \
    ln -s /opt/gradle-8.4/bin/gradle /usr/bin/gradle

# 작업 디렉터리 설정
WORKDIR /home/gradle/app

# 의존성 파일 복사 및 빌드 캐시 활용
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon || return 0

# 소스 코드 복사 및 빌드 실행
COPY src ./src
RUN gradle build --no-daemon


# 6. 최종 이미지 생성: 경량 OpenJDK 사용
FROM openjdk:21-slim
WORKDIR /app

# 7. 빌드된 JAR 파일을 복사
COPY --from=builder /home/gradle/app/build/libs/*SNAPSHOT.jar /app/app.jar

# 8. set up mysql first before test begins: https://github.com/vishnubob/wait-for-it
#COPY wait-for-it.sh /wait-for-it.sh
#RUN chmod +x /wait-for-it.sh
#ENTRYPOINT ["/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/app.jar"]

# 8. JAR 파일 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
