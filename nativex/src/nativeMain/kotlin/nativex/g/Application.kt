package nativex.g

import gtk.GApplication
import gtk.g_application_run
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val gApplicationPointer: CPointer<GApplication>
) : KotlinGObject(gApplicationPointer.reinterpret()) {
	fun run(argc: Int = 0): Int = memScoped {
		g_application_run(gApplicationPointer, argc, null)
	}
}