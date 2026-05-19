# WinVMJ Generated Dockerfile
# Product: Notifiedeo
# Context: src-gen/Notifiedeo/ (same level as docker-compose.yml)

# Stage 1: Download Gradle dependencies (needs full project context)
FROM gradle:8.5-jdk17 AS builder

WORKDIR /app
COPY . .

# Compile sources and download dependencies
RUN gradle compileJava copyDependencies --no-daemon

# Stage 2: Runtime
# Use Java 21 runtime to match classes compiled with class file version 65.0
FROM eclipse-temurin:21-jre

WORKDIR /app

# Create non-root user (Debian-compatible)
# Use groupadd/useradd; tolerate existing user/group with `|| true`
RUN groupadd -g 1001 winvmj || true && \
    useradd -u 1001 -g winvmj -s /bin/sh -m -d /home/winvmj winvmj || true

# Copy everything from project folder
COPY . .

# Copy Gradle-downloaded dependencies into jars folder
COPY --from=builder /app/deps/ ./jars/

# Move product JARs and lib JARs (includes monitoring module) to jars folder
RUN mv eventmanager.product.notifiedeo/*.jar ./jars/ 2>/dev/null || true && \
    mv lib/*.jar ./jars/ 2>/dev/null || true

# Set permissions
RUN chown -R winvmj:winvmj /app

USER winvmj

# Expose internal ports
EXPOSE 7776

# Run the application
CMD ["java", "-cp", "jars/*:.", "eventmanager.product.notifiedeo.Notifiedeo"]
