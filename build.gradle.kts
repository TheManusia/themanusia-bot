import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.themanusia"
version = "1.2.1.0" // <Major change>.<Core change>.<Feature improvement>.<Bug fix>

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.1")
    implementation("com.github.minndevelopment:jda-ktx:9fc90f6")
    implementation("ch.qos.logback:logback-classic:1.4.6")

    implementation("com.github.ygimenez:Pagination-Utils:4.0.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("app.jar")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "themanusiabot.Main"
    }
}