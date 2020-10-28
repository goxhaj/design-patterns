import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.agoxhaj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.test {
    useJUnitPlatform()
}