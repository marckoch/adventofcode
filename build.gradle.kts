plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "me.marckoch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:0.27.0")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass.set("MainKt")
}