


plugins {
    kotlin("jvm") version "1.9.23"
    id("java")

    //shadow插件
    id("com.github.johnrengelman.shadow") version ("7.1.2")

}

group = "top.rainydrm"
version="1.0.0"

repositories {
    mavenLocal()

    // 添加SpigotMC的快照仓库，用于获取Spigot插件开发相关的依赖
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    // Sonatype的公共仓库群组，提供广泛的开源库依赖
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    // Sonatype的快照版本仓库，用于获取依赖的快照版本
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    // 中央Maven仓库，包含大部分公有依赖
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}
// 或者在build.gradle中

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
}
tasks.shadowJar {
    archiveBaseName.set("bukkit-RainyRecipe")

    archiveClassifier.set("")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}

tasks.named<ProcessResources>("processResources") {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}
