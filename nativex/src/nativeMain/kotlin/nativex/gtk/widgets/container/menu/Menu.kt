package nativex.gtk.widgets.container.menu

import gtk.GtkMenu
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class Menu internal constructor(
	internal val menuPointer: CPointer<GtkMenu>
) : MenuShell(menuPointer.reinterpret()) {

}