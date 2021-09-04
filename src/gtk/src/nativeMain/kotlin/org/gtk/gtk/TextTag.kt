package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject

/**
 * gtk-kt
 *
 * 03 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextTag.html">
 *     GtkTextTag</a>
 */
class TextTag(val textTagPointer: CPointer<GtkTextTag>) : KGObject(textTagPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TextTag.new.html">
	 *     gtk_text_tag_new</a>
	 */
	constructor(name: String) : this(gtk_text_tag_new(name)!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTag.changed.html">
	 *     gtk_text_tag_changed</a>
	 */
	fun changed(sizeChanged: Boolean) {
		gtk_text_tag_changed(textTagPointer, sizeChanged.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTag.get_priority.html">
	 *     gtk_text_tag_get_priority</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTag.set_priority.html">
	 *     gtk_text_tag_set_priority</a>
	 */
	var priority: Int
		get() = gtk_text_tag_get_priority(textTagPointer)
		set(value) = gtk_text_tag_set_priority(textTagPointer, value)

	companion object {
		inline fun CPointer<GtkTextTag>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextTag>.wrap() =
			TextTag(this)
	}
}