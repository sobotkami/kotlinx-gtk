package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class StyleContext(
	 val styleContextPointer: CPointer<GtkStyleContext>
) {
	companion object{
		 inline fun CPointer<GtkStyleContext>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkStyleContext>.wrap() =
			StyleContext(this)

	}

	class Border(
		 val borderPointer: CPointer<GtkBorder>?
	) {
		val left: Short?
			get() = borderPointer?.pointed?.left

		val right: Short?
			get() = borderPointer?.pointed?.right

		val top: Short?
			get() = borderPointer?.pointed?.top

		val bottom: Short?
			get() = borderPointer?.pointed?.bottom

		constructor() : this(gtk_border_new()?.reinterpret())

		fun copy(): Border? = gtk_border_copy(borderPointer)?.let { Border(it) }

		fun free() {
			gtk_border_free(borderPointer)
		}
	}
}