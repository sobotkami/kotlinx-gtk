package org.gtk.gtk

import gtk.GtkBorder
import gtk.gtk_border_copy
import gtk.gtk_border_free
import gtk.gtk_border_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * @see <a href="https://docs.gtk.org/gtk4/struct.Border.html">GtkBorder</a>
 */
class Border(
	val borderPointer: CPointer<GtkBorder>
) {
	var left: Short
		get() = borderPointer.pointed.left
		set(value) {
			borderPointer.pointed.left = value
		}

	var right: Short
		get() = borderPointer.pointed.right
		set(value) {
			borderPointer.pointed.right = value
		}

	var top: Short
		get() = borderPointer.pointed.top
		set(value) {
			borderPointer.pointed.top = value
		}

	var bottom: Short
		get() = borderPointer.pointed.bottom
		set(value) {
			borderPointer.pointed.bottom = value
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.Border.new.html">gtk_border_new</a>
	 */
	constructor() : this(gtk_border_new()!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Border.copy.html">gtk_border_copy</a>
	 */
	fun copy(): Border = gtk_border_copy(borderPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Border.free.html">gtk_border_free</a>
	 */
	fun free() {
		gtk_border_free(borderPointer)
	}

	companion object {
		inline fun CPointer<GtkBorder>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkBorder>.wrap() =
			Border(this)
	}
}