package nativex.gdk

import gtk.GdkPixbuf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KotlinGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Pixbuf internal constructor(
	internal val pixbufPointer: CPointer<GdkPixbuf>
) : KotlinGObject(pixbufPointer.reinterpret()) {

}