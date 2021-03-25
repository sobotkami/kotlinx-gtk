plugins {
	kotlin("multiplatform")
	id("maven-publish")
}

repositories {
	mavenCentral()
}

group = "org.gnome.kotlin.nativex"
version = "0.0.0"

kotlin {

	kotlin {
		linuxX64("native") {
			val main by compilations.getting
			val gtk by main.cinterops.creating
			binaries {
				sharedLib()
			}
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")
			}
		}
	}
}