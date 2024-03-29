import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")

    kotlin("jvm") version "1.9.0"
}

application {
    mainClass.set("com.example.HelloKt")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":lib")))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}