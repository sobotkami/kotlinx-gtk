plugins {
	kotlin("multiplatform")
}

repositories {
	mavenCentral()
}

group = "org.gnome.kotlin.dsl"
version = "0.1.0"

kotlin {
	linuxX64("native") {
		val main by compilations.getting
		binaries {
			sharedLib()
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":nativex"))
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
			}
		}
	}
}