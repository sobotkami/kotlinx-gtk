plugins {
	kotlin("multiplatform")
	`maven-publish`
}

repositories {
	mavenCentral()
}

group = "org.gnome.gtk"
version = "2.68.0-alpha"

kotlin {

	kotlin {
		linuxX64("native") {
			val main by compilations.getting
			val gobject by main.cinterops.creating

			binaries {
				sharedLib()
			}
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":src:glib"))
			}
		}
	}
}