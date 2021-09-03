package org.gtk.gtk

import gtk.GtkTextTag
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * gtk-kt
 *
 * 03 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextTag.html"></a>
 */
class TextTag(val textTagPointer: CPointer<GtkTextTag>) : KGObject(textTagPointer.reinterpret()) {

	companion object {
		inline fun CPointer<GtkTextTag>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextTag>.wrap() =
			TextTag(this)
	}
}