plugins {
	kotlin("multiplatform")
	`maven-publish`
}

repositories {
	mavenCentral()
}

group = "org.gnome.kotlin.gtk"
version = "0.1.0-alpha"

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
				implementation(project(":nativex:gio"))
				implementation(project(":nativex:cairo"))
				implementation(project(":nativex:pango"))
			}
		}
	}
}