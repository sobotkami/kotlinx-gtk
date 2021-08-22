package nativex.gdk

import gtk.GdkCursor
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 28 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class Cursor(val cursorPointer: CPointer<GdkCursor>) {
	companion object {
		inline fun CPointer<GdkCursor>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkCursor>.wrap() =
			Cursor(this)
	}
}