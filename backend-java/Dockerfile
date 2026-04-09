FROM eclipse-temurin:26-jdk-alpine-3.23 AS builder

WORKDIR /src
COPY src/Main.java ./Main.java
RUN javac Main.java

FROM eclipse-temurin:26-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app
COPY --from=builder /src/*.class /app/

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget -qO- http://127.0.0.1:8080/health >/dev/null || exit 1

USER appuser:appgroup
ENTRYPOINT ["java", "Main"]
