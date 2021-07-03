package nativex.gtk.widgets.container.menu

import gtk.*
import gtk.GtkPackDirection.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.MenuModel

/**
 * kotlinx-gtk
 *
 * 20 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html">GtkMenuBar</a>
 */
class MenuBar(
	 val menuBarPointer: CPointer<GtkMenuBar>
) : MenuShell(menuBarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-get-pack-direction">
	 *     gtk_menu_bar_get_pack_direction</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-set-pack-direction">
	 *     gtk_menu_bar_set_pack_direction</a>
	 */
	var packDirection: PackDirection
		get() = PackDirection.valueOf(
			gtk_menu_bar_get_pack_direction(
				menuBarPointer
			)
		)!!
		set(value) = gtk_menu_bar_set_pack_direction(menuBarPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-get-child-pack-direction">
	 *     gtk_menu_bar_get_child_pack_direction</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-set-child-pack-direction">
	 *     gtk_menu_bar_set_child_pack_direction</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-new">gtk_menu_bar_new</a>
	 */
	constructor() : this(gtk_menu_bar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#gtk-menu-bar-new-from-model">gtk_menu_bar_new_from_model</a>
	 */
	constructor(menuModel: MenuModel) : this(
		gtk_menu_bar_new_from_model(
			menuModel.menuModelPointer
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuBar.html#GtkPackDirection">GtkPackDirection</a>
	 */
	enum class PackDirection(val key: Int,  val gtk: GtkPackDirection) {
		/** Widgets are packed left-to-right */
		LTR(0, GTK_PACK_DIRECTION_LTR),

		/** Widgets are packed right-to-left */
		RTL(1, GTK_PACK_DIRECTION_RTL),

		/** Widgets are packed top-to-bottom */
		TTB(2, GTK_PACK_DIRECTION_TTB),

		/** Widgets are packed bottom-to-top */
		BTT(3, GTK_PACK_DIRECTION_BTT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			 fun valueOf(gtk: GtkPackDirection) =
				values().find { it.gtk == gtk }
		}

	}
}