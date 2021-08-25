package org.gtk.gtk.widgets.button.toggleable

import gtk.*
import gtk.GtkArrowType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gio.MenuModel
import org.gtk.gio.MenuModel.Impl.Companion.wrap
import org.gtk.gtk.widgets.popover.Popover
import org.gtk.gtk.widgets.popover.Popover.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html">GtkMenuButton</a>
 */
class MenuButton(val menuButtonPointer: CPointer<GtkMenuButton>) : ToggleButton(menuButtonPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-new">
	 *     gtk_menu_button_new</a>
	 */
	constructor() : this(gtk_menu_button_new()!!.reinterpret())


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-popover">
	 *     gtk_menu_button_get_popover</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-popover">
	 *     gtk_menu_button_set_popover</a>
	 */
	var popover: Popover?
		get() = gtk_menu_button_get_popover(menuButtonPointer).wrap()
		set(value) = gtk_menu_button_set_popover(menuButtonPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-menu-model">
	 *     gtk_menu_button_get_menu_model</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-menu-model">
	 *     gtk_menu_button_set_menu_model</a>
	 */
	var menuModel: MenuModel?
		get() = gtk_menu_button_get_menu_model(menuButtonPointer).wrap()
		set(value) = gtk_menu_button_set_menu_model(menuButtonPointer, value?.menuModelPointer)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-direction">
	 *     gtk_menu_button_get_direction</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-direction">
	 *     gtk_menu_button_set_direction</a>
	 */
	var popupDirection: ArrowType
		get() = ArrowType.valueOf(gtk_menu_button_get_direction(menuButtonPointer))!!
		set(value) = gtk_menu_button_set_direction(menuButtonPointer, value.gtk)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#GtkArrowType">GtkArrowType</a>
	 */
	enum class ArrowType(val gtk: GtkArrowType) {
		/**
		 * Represents an upward pointing arrow.
		 */
		UP(GTK_ARROW_UP),

		/**
		 * Represents a downward pointing arrow.
		 */
		DOWN(GTK_ARROW_DOWN),

		/**
		 * Represents a left pointing arrow.
		 */
		LEFT(GTK_ARROW_LEFT),

		/**
		 * Represents a right pointing arrow.
		 */
		RIGHT(GTK_ARROW_RIGHT),

		/**
		 * No arrow.
		 *
		 * @since 2.10.
		 */
		NONE(GTK_ARROW_NONE);

		companion object {
			fun valueOf(gtk: GtkArrowType) = values().find { it.gtk == gtk }
		}
	}
}