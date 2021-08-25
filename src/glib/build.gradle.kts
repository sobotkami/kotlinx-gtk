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
			val glib by main.cinterops.creating

			binaries {
				sharedLib()
			}
		}
	}
}