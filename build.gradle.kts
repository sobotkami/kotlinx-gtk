plugins {
	kotlin("multiplatform") version "1.4.30"
}

group = "com.github.doomsdayrs.lib"
version = "0.0.0"

kotlin {

	val hostOs = System.getProperty("os.name")
	val isMingwX64 = hostOs.startsWith("Windows")
	val nativeTarget = when {
		hostOs == "Mac OS X" -> macosX64("native")
		hostOs == "Linux" -> linuxX64("native")
		isMingwX64 -> mingwX64("native")
		else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
	}
}
