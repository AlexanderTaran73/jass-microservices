plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "jass-microservices"
include("auth-service")
include("email-service")
include("discovery-server")
include("api-gateway")
include("user-service")
include("profile-service")
