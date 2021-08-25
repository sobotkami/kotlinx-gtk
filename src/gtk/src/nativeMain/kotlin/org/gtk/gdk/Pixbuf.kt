package org.gtk.gdk

import gtk.GdkPixbuf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Pixbuf (val pixbufPointer: CPointer<GdkPixbuf>) : KGObject(pixbufPointer.reinterpret()) {

	companion object {
		fun CPointer<GdkPixbuf>?.wrap() =
			this?.wrap()

		fun CPointer<GdkPixbuf>.wrap() =
			Pixbuf(this)
	}
}