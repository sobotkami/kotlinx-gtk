plugins {
	kotlin("multiplatform")
}

description = "Custom drawing example"

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
				implementation(project(":dsl"))
			}
		}
	}

}