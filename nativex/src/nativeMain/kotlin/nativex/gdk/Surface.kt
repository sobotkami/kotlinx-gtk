package nativex.gdk

import gtk.GdkSurface
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 25 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gdk4/stable/GdkSurface.html">GdkSurface</a>
 */
class Surface(val surfacePointer: CPointer<GdkSurface>) : KGObject(surfacePointer.reinterpret()) {
	companion object {
		inline fun CPointer<GdkSurface>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkSurface>.wrap() =
			Surface(this)
	}
}