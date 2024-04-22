plugins {
    kotlin("jvm")
}

group = "com.jass"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}