package nativex.gdk

import gtk.GdkWindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Window internal constructor(
	internal val windowPointer: CPointer<GdkWindow>
) : KObject(windowPointer.reinterpret()){

	companion object{
		internal inline fun CPointer<GdkWindow>?.wrap() =
			this?.let { Window(it) }

		internal inline fun CPointer<GdkWindow>.wrap() =
			Window(this)
	}
}