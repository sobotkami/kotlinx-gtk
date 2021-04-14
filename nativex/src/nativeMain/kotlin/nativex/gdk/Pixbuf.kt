package nativex.gdk

import gtk.GdkPixbuf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.AppInfo
import nativex.gio.KotlinGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Pixbuf internal constructor(
	internal val pixbufPointer: CPointer<GdkPixbuf>
) : KotlinGObject(pixbufPointer.reinterpret()) {

	companion object {
		internal inline fun CPointer<GdkPixbuf>?.wrap() =
			this?.let { Pixbuf(it) }

		internal inline fun CPointer<GdkPixbuf>.wrap() =
			Pixbuf(this)
	}
}