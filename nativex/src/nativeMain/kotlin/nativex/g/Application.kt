package nativex.g

import gtk.GApplication
import gtk.g_application_run
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val cPointer: CPointer<GApplication>
) {
	fun run(argc: Int = 0): Int = memScoped {
		g_application_run(cPointer, argc, null)
	}
}