rootProject.name = "DevRush-Multiplatform"
include(":composeApp")
//include(":signalrkore")
//include(":buildSrc")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jogamp.org/deployment/maven")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

//configurations.all {
//    resolutionStrategy.eachDependency {
//        if (requested.group == "androidx.lifecycle") {
//            useVersion("2.6.1")
//        }
//    }
//}