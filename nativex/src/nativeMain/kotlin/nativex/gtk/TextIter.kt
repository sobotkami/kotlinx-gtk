package nativex.gtk

import gtk.GtkTextIter
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class TextIter internal constructor(
	val pointer: CPointer<GtkTextIter>
) {

}