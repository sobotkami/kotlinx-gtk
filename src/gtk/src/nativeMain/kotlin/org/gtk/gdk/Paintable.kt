package org.gtk.gdk

import gtk.GdkPaintable
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
open class Paintable(val paintablePointer: CPointer<GdkPaintable>) : KGObject(paintablePointer.reinterpret()) {
	companion object {
		inline fun CPointer<GdkPaintable>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkPaintable>.wrap() =
			Paintable(this)
	}
}