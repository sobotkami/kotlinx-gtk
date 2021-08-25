package org.gtk.gtk.widgets

import gtk.GtkScrollable
import gtk.GtkViewport
import gtk.gtk_viewport_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.Adjustment
import org.gtk.gtk.Scrollable

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html">GtkViewport</a>
 */
class Viewport(
	val viewPortPointer: CPointer<GtkViewport>
) : Widget(viewPortPointer.reinterpret()), Scrollable {
	override val scrollablePointer: CPointer<GtkScrollable>
		get() = viewPortPointer.reinterpret()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-new">gtk_viewport_new</a>
	 */
	constructor(horizontalAdjustment: Adjustment, verticalAdjustment: Adjustment) : this(
		gtk_viewport_new(
			horizontalAdjustment.adjustmentPointer,
			verticalAdjustment.adjustmentPointer
		)!!.reinterpret()
	)


}