plugins {
    kotlin("jvm") version "1.9.23"
}

group = "top.rainydrm"
version = "1.0.0"

repositories {
    mavenLocal()

    // 添加SpigotMC的快照仓库，用于获取Spigot插件开发相关的依赖
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}