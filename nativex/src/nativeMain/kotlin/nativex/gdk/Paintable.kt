package nativex.gdk

import gtk.GdkPaintable
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Paintable(val paintablePointer: CPointer<GdkPaintable>) : KGObject(paintablePointer.reinterpret()) {
	companion object {
		inline fun CPointer<GdkPaintable>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkPaintable>.wrap() =
			Paintable(this)
	}
}