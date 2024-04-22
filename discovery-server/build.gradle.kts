plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.spring")
}

group = "com.jass"
version = "0.0.1-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    testImplementation(kotlin("test"))
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}