package nativex.gtk.widgets.container.menu

import gtk.GtkMenu
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
open class Menu(
	val menuPointer: CPointer<GtkMenu>
) : MenuShell(menuPointer.reinterpret()) {

	companion object {
		inline fun CPointer<GtkMenu>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkMenu>.wrap() =
			Menu(this)
	}
}