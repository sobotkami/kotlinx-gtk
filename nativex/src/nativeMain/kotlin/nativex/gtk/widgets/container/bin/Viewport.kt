package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Scrollable
import nativex.gtk.common.enums.ShadowType

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Viewport(
	internal val viewPortPointer: CPointer<GtkViewport>
) : Bin(viewPortPointer.reinterpret()), Scrollable {
	override val scrollablePointer: nativex.PointerHolder<GtkScrollable>
		get() = nativex.PointerHolder(viewPortPointer.reinterpret())

	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_viewport_get_shadow_type(
				viewPortPointer
			)
		)!!
		set(value) = gtk_viewport_set_shadow_type(viewPortPointer, value.gtk)

	val binWindow: nativex.gdk.Window
		get() = nativex.gdk.Window(gtk_viewport_get_bin_window(viewPortPointer)!!)

	val viewWindow: nativex.gdk.Window
		get() = nativex.gdk.Window(gtk_viewport_get_view_window(viewPortPointer)!!)

}