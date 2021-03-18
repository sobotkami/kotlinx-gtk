plugins {
	kotlin("multiplatform")
}

repositories {
	mavenCentral()
}

kotlin {
	linuxX64("native") {
		val main by compilations.getting
		binaries {
			staticLib()
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":nativex"))
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")
			}
		}
	}
}