package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.KObject
import nativex.gtk.TextBuffer.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 17 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html>GtkTextMark</a>
 */
class TextMark(val markPointer: CPointer<GtkTextMark>) : KObject(markPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-new">
	 *     gtk_text_mark_new</a>
	 */
	constructor(leftGravity: Boolean, name: String? = null) : this(gtk_text_mark_new(name, leftGravity.gtk)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-get-visible">
	 *     gtk_text_mark_get_visible</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-set-visible">
	 *     gtk_text_mark_set_visible</a>
	 */
	var isVisible: Boolean
		get() = gtk_text_mark_get_visible(markPointer).bool
		set(value) = gtk_text_mark_set_visible(markPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-get-deleted">
	 *     gtk_text_mark_get_deleted</a>
	 */
	val isDeleted: Boolean
		get() = gtk_text_mark_get_deleted(markPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-get-name">
	 *     gtk_text_mark_get_name</a>
	 */
	val name: String?
		get() = gtk_text_mark_get_name(markPointer)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-get-buffer">
	 *     gtk_text_mark_get_buffer</a>
	 */
	val buffer: TextBuffer?
		get() = gtk_text_mark_get_buffer(markPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkTextMark.html#gtk-text-mark-get-left-gravity">
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