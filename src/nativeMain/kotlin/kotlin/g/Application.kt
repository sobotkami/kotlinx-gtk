package kotlin.g

import gtk.g_application_run
import gtk.g_object_unref
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.memScoped

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val cPointer: CPointer<gtk.GApplication>
) {
	fun run(argc: Int = 0): Int = memScoped {
		g_application_run(cPointer, argc, null)
	}.also {
		//g_object_unref(cPointer)
	}
}