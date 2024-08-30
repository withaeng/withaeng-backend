val queryDslVersion: String by project.extra

dependencies {
    implementation(project(":withaeng-common"))
    implementation(project(":withaeng-domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Query DSL
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion")
}