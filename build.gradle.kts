plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "me.marckoch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
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