package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Adjustment
import nativex.gtk.Scrollable
import nativex.gtk.common.enums.ShadowType

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html">GtkViewport</a>
 */
class Viewport(
	 val viewPortPointer: CPointer<GtkViewport>
) : Bin(viewPortPointer.reinterpret()), Scrollable {
	override val scrollablePointer: nativex.PointerHolder<GtkScrollable>
		get() = nativex.PointerHolder(viewPortPointer.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-new">gtk_viewport_new</a>
	 */
	constructor(horizontalAdjustment: Adjustment, verticalAdjustment: Adjustment) : this(
		gtk_viewport_new(
			horizontalAdjustment.adjustmentPointer,
			verticalAdjustment.adjustmentPointer
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-get-shadow-type">
	 *     gtk_viewport_get_shadow_type</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-set-shadow-type">
	 *     gtk_viewport_set_shadow_type</a>
	 */
	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_viewport_get_shadow_type(
				viewPortPointer
			)
		)!!
		set(value) = gtk_viewport_set_shadow_type(viewPortPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-get-bin-window">
	 *     gtk_viewport_get_bin_window</a>
	 */
	val binWindow: nativex.gdk.Window
		get() = nativex.gdk.Window(gtk_viewport_get_bin_window(viewPortPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkViewport.html#gtk-viewport-get-view-window">
	 *     gtk_viewport_get_view_window</a>
	 */
	val viewWindow: nativex.gdk.Window
		get() = nativex.gdk.Window(gtk_viewport_get_view_window(viewPortPointer)!!)

}