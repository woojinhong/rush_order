# 1. Eclipse Temurin JDK 21 기반의 Alpine Linux 이미지 사용 (빌드 스테이지)
FROM eclipse-temurin:21-jdk-alpine AS build

# 2. 작업 디렉터리 설정
WORKDIR /app

COPY . .

# 6. 테스트를 제외하고 애플리케이션 빌드
RUN ./gradlew build -x test

# 7. JRE 21 기반의 실행 스테이지 준비
FROM eclipse-temurin:21-jre-alpine


# 8. 절대 경로에서 애플리케이션 실행
WORKDIR /app

# 9. 빌드된 JAR 파일을 실행 환경으로 복사
COPY --from=build build/libs/*.jar app.jar

# 10. 애플리케이션 실행 명령어 설정
ENTRYPOINT ["java", "-jar", "app.jar"]
