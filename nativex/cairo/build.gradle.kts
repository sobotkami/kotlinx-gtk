plugins {
	kotlin("multiplatform")
	`maven-publish`
}

repositories {
	mavenCentral()
}

group = "org.gnome.kotlinx-gtk"
version = "0.1.0-alpha"

kotlin {

	kotlin {
		linuxX64("native") {
			val main by compilations.getting
			val cairo by main.cinterops.creating

			binaries {
				sharedLib()
			}
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":nativex:glib"))
			}
		}
	}
}