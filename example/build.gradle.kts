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
			executable()
		}
	}

	sourceSets {

		val nativeMain by getting {
			dependencies {
				implementation(project(":src:gtk"))
				implementation(project(":dsl"))
				implementation(project(":coroutines"))
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
			}
		}
	}

}