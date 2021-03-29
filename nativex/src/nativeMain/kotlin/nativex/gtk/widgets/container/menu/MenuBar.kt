package nativex.gtk.widgets.container.menu

import gtk.*
import gtk.GtkPackDirection.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.MenuModel

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class MenuBar internal constructor(
	internal val menuBarPointer: CPointer<GtkMenuBar>
) : MenuShell(menuBarPointer.reinterpret()) {

	var packDirection: PackDirection
		get() = PackDirection.valueOf(
			gtk_menu_bar_get_pack_direction(
				menuBarPointer
			)
		)!!
		set(value) = gtk_menu_bar_set_pack_direction(menuBarPointer, value.gtk)

	var childPackDirection: PackDirection
		get() = PackDirection.valueOf(
			gtk_menu_bar_get_child_pack_direction(
				menuBarPointer
			)
		)!!
		set(value) = gtk_menu_bar_set_child_pack_direction(
			menuBarPointer,
			value.gtk
		)

	constructor() : this(gtk_menu_bar_new()!!.reinterpret())
	constructor(menuModel: MenuModel) : this(
		gtk_menu_bar_new_from_model(
			menuModel.menuModelPointer
		)!!.reinterpret()
	)

	enum class PackDirection(val key: Int, internal val gtk: GtkPackDirection) {
		LTR(0, GTK_PACK_DIRECTION_LTR),
		RTL(1, GTK_PACK_DIRECTION_RTL),
		TTB(2, GTK_PACK_DIRECTION_TTB),
		BTT(3, GTK_PACK_DIRECTION_BTT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkPackDirection) =
				values().find { it.gtk == gtk }
		}

	}
}