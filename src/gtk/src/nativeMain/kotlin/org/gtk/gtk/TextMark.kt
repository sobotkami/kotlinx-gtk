package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gtk.TextBuffer.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 17 / 07 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextMark.html>GtkTextMark</a>
 */
class TextMark(val markPointer: CPointer<GtkTextMark>) : KGObject(markPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TextMark.new.html">
	 *     gtk_text_mark_new</a>
	 */
	constructor(leftGravity: Boolean, name: String? = null) : this(gtk_text_mark_new(name, leftGravity.gtk)!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.get_visible.html">
	 *     gtk_text_mark_get_visible</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.set_visible.html">
	 *     gtk_text_mark_set_visible</a>
	 */
	var isVisible: Boolean
		get() = gtk_text_mark_get_visible(markPointer).bool
		set(value) = gtk_text_mark_set_visible(markPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.get_deleted.html">
	 *     gtk_text_mark_get_deleted</a>
	 */
	val isDeleted: Boolean
		get() = gtk_text_mark_get_deleted(markPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.get_name.html">
	 *     gtk_text_mark_get_name</a>
	 */
	val name: String?
		get() = gtk_text_mark_get_name(markPointer)?.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.get_buffer.html">
	 *     gtk_text_mark_get_buffer</a>
	 */
	val buffer: TextBuffer?
		get() = gtk_text_mark_get_buffer(markPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextMark.get_left_gravity.html">
	 *     gtk_text_mark_get_left_gravity</a>
	 */
	val leftGravity: Boolean
		get() = gtk_text_mark_get_left_gravity(markPointer).bool


	companion object{
		inline fun CPointer<GtkTextMark>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextMark>.wrap() =
			TextMark(this)
	}
}