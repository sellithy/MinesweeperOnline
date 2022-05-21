import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.sikulix:sikulixapi:2.0.5")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:4.1.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}