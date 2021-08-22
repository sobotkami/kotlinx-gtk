plugins {
	kotlin("multiplatform")
	`maven-publish`
}

repositories {
	mavenCentral()
}

group = "org.gnome.kotlinx-gtk"
version = "2.68"

kotlin {

	kotlin {
		linuxX64("native") {
			val main by compilations.getting
			val gio by main.cinterops.creating

			binaries {
				sharedLib()
			}
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":nativex:glib"))
				implementation(project(":nativex:gobject"))
			}
		}
	}
}