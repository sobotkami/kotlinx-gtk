package org.gtk.gdk

import gtk.GdkClipboard
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Clipboards.html#GtkClipboard">
 *     GdkClipboard</a>
 */
class Clipboard(
	val clipboardPointer: CPointer<GdkClipboard>
) : KGObject(clipboardPointer.reinterpret()) {
	companion object {
		inline fun CPointer<GdkClipboard>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkClipboard>.wrap() =
			Clipboard(this)
	}
}