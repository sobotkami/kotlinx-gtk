package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.Scrollable
import kotlin.gtk.common.enums.ShadowType

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Viewport(
	internal val viewPortPointer: CPointer<GtkViewport>
) : Bin(viewPortPointer.reinterpret()), Scrollable {
	override val scrollablePointer: PointerHolder<GtkScrollable>
		get() = PointerHolder(viewPortPointer.reinterpret())

	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_viewport_get_shadow_type(
				viewPortPointer
			)
		)!!
		set(value) = gtk_viewport_set_shadow_type(viewPortPointer, value.gtk)

	val binWindow: kotlin.gdk.Window
		get() = kotlin.gdk.Window(gtk_viewport_get_bin_window(viewPortPointer)!!)

	val viewWindow: kotlin.gdk.Window
		get() = kotlin.gdk.Window(gtk_viewport_get_view_window(viewPortPointer)!!)

}