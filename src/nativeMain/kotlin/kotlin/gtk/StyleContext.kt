package kotlin.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class StyleContext internal constructor(
	internal val styleContextPointer: CPointer<GtkStyleContext>
) {

	class Border internal constructor(
		internal val borderPointer: CPointer<GtkBorder>?
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