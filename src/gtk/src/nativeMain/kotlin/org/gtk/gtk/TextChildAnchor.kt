package org.gtk.gtk

import gtk.GtkTextChildAnchor
import gtk.gtk_text_child_anchor_get_deleted
import gtk.gtk_text_child_anchor_get_widgets
import gtk.gtk_text_child_anchor_new
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.gobject.KGObject
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.Widget.Companion.wrap

/**
 * gtk-kt
 *
 * 04 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextChildAnchor.html">GtkTextChildAnchor</a>
 */
class TextChildAnchor(val textChildAnchorPointer: CPointer<GtkTextChildAnchor>) :
	KGObject(textChildAnchorPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TextChildAnchor.new.html">
	 *     gtk_text_child_anchor_new</a>
	 */
	constructor() : this(gtk_text_child_anchor_new()!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextChildAnchor.get_deleted.html">
	 *     gtk_text_child_anchor_get_deleted</a>
	 */
	val deleted: Boolean
		get() = gtk_text_child_anchor_get_deleted(textChildAnchorPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextChildAnchor.get_widgets.html">
	 *     gtk_text_child_anchor_get_widgets</a>
	 */
	val widgets: Array<Widget>
		get() = memScoped {
			val len = cValue<UIntVar>()
			val array = gtk_text_child_anchor_get_widgets(textChildAnchorPointer, len)!!
			Array(len.ptr.pointed.value.toInt()) { index ->
				array[index]!!.wrap()
			}
		}

	companion object{
		inline fun CPointer<GtkTextChildAnchor>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextChildAnchor>.wrap() =
			TextChildAnchor(this)
	}
}