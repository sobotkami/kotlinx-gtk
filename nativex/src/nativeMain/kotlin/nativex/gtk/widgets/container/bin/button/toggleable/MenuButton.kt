package nativex.gtk.widgets.container.bin.button.toggleable

import gtk.*
import gtk.GtkArrowType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.MenuModel
import nativex.gio.MenuModel.Impl.Companion.wrap
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.popover.Popover
import nativex.gtk.widgets.container.bin.popover.Popover.Companion.wrap
import nativex.gtk.widgets.container.menu.Menu
import nativex.gtk.widgets.container.menu.Menu.Companion.wrap

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-popup">
	 *     gtk_menu_button_get_popup</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-popup">
	 *     gtk_menu_button_set_popup</a>
	 */
	var popup: Menu?
		get() = gtk_menu_button_get_popup(menuButtonPointer).wrap()
		set(value) = gtk_menu_button_set_popup(menuButtonPointer, value?.widgetPointer)

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-use-popover">
	 *     gtk_menu_button_get_use_popover</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-use-popover">
	 *     gtk_menu_button_set_use_popover</a>
	 */
	var usePopover: Boolean
		get() = gtk_menu_button_get_use_popover(menuButtonPointer).bool
		set(value) = gtk_menu_button_set_use_popover(menuButtonPointer, value.gtk)

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-get-align-widget">
	 *     gtk_menu_button_get_align_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkMenuButton.html#gtk-menu-button-set-align-widget">
	 *     gtk_menu_button_set_align_widget</a>
	 */
	var alignWidget: Widget?
		get() = gtk_menu_button_get_align_widget(menuButtonPointer).wrap()
		set(value) = gtk_menu_button_set_align_widget(menuButtonPointer, value?.widgetPointer)


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