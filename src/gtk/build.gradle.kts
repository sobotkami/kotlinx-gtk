plugins {
	kotlin("multiplatform")
	`maven-publish`
}

repositories {
	mavenCentral()
}

group = "org.gnome.gtk"
version = "4.5.0-alpha"

kotlin {

	kotlin {
		linuxX64("native") {
			val main by compilations.getting
			val gtk by main.cinterops.creating
			//val gtkunixprint by main.cinterops.creating

			binaries {
				sharedLib()
			}
		}
	}

	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(project(":src:gio"))
				implementation(project(":src:cairo"))
				implementation(project(":src:pango"))
			}
		}
	}
}