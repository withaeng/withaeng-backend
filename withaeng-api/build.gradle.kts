tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

val jjwtVersion: String by project.extra
dependencies {
    implementation(project(":withaeng-domain"))
    implementation(project(":withaeng-common"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
}