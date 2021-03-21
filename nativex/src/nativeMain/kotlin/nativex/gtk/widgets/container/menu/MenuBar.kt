package nativex.gtk.widgets.container.menu

import gtk.GtkMenu
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class MenuBar internal constructor(
	internal val menuBarPointer: CPointer<GtkMenu>
) : MenuShell(menuBarPointer.reinterpret()) {

}