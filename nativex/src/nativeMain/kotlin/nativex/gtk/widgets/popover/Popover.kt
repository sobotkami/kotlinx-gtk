package nativex.gtk.widgets.popover

import gtk.*
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_NONE
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_WINDOW
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.gdk.Rectangle
import nativex.gio.MenuModel
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.Signals
import nativex.gtk.asWidget
import nativex.gtk.asWidgetOrNull
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html">GtkPopover</a>
 */
open class Popover(
	 val popoverPointer: CPointer<GtkPopover>
) : Widget(popoverPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-new">gtk_popover_new</a>
	 */
	constructor(relativeTo: Widget) : this(
		gtk_popover_new(relativeTo.widgetPointer)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-new-from-model">
	 *     gtk_popover_new_from_model</a>
	 */
	constructor(relativeTo: Widget, model: MenuModel) : this(
		gtk_popover_new_from_model(
			relativeTo.widgetPointer,
			model.menuModelPointer
		)!!.reinterpret()
	)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-bind-model">
	 *     gtk_popover_bind_model</a>
	 */
	fun bind(model: MenuModel?, actionName: String) {
		gtk_popover_bind_model(popoverPointer, model?.menuModelPointer, actionName)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-popup">gtk_popover_popup</a>
	 */
	fun popup() {
		gtk_popover_popup(popoverPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-popdown">
	 *     gtk_popover_popdown</a>
	 */
	fun popdown() {
		gtk_popover_popdown(popoverPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-relative-to">
	 *     gtk_popover_get_relative_to</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-relative-to">
	 *     gtk_popover_set_relative_to</a>
	 */
	var relativeTo: Widget
		get() = gtk_popover_get_relative_to(popoverPointer).asWidget()
		set(value) = gtk_popover_set_relative_to(popoverPointer, value.widgetPointer)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-pointing-to">
	 *     gtk_popover_set_pointing_to</a>
	 */
	fun setPointingTo(rectangle: Rectangle) {
		gtk_popover_set_pointing_to(popoverPointer, rectangle.rectanglePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-pointing-to">
	 *     gtk_popover_get_pointing_to</a>
	 */
	fun getPointingTo(rectangle: Rectangle): Boolean =
		gtk_popover_get_pointing_to(
			popoverPointer,
			rectangle.rectanglePointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-position">
	 *     gtk_popover_get_position</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-position">
	 *     gtk_popover_set_position</a>
	 */
	var positionType: PositionType
		set(value) = gtk_popover_set_position(popoverPointer, value.gtk)
		get() = PositionType.valueOf(
			gtk_popover_get_position(
				popoverPointer
			)
		)!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-constrain-to">
	 *     gtk_popover_get_constrain_to</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-constrain-to">
	 *     gtk_popover_set_constrain_to</a>
	 */
	var constrainTo: Constraint
		get() = Constraint.valueOf(
			gtk_popover_get_constrain_to(popoverPointer)
		)!!
		set(value) = gtk_popover_set_constrain_to(popoverPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-modal">
	 *     gtk_popover_get_modal</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-modal">
	 *     gtk_popover_set_modal</a>
	 */
	var isModal: Boolean
		get() = gtk_popover_get_modal(popoverPointer).bool
		set(value) = gtk_popover_set_modal(popoverPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-get-default-widget">
	 *     gtk_popover_get_default_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#gtk-popover-set-default-widget">
	 *     gtk_popover_set_default_widget</a>
	 */
	var defaultWidget: Widget?
		get() = gtk_popover_get_default_widget(popoverPointer).asWidgetOrNull()
		set(value) = gtk_popover_set_default_widget(
			popoverPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopoverMenu.html">GtkPopoverMenu</a>
	 */
	class Menu(
		 val popoverMenuPointer: CPointer<GtkPopoverMenu>
	) : Popover(popoverMenuPointer.reinterpret()) {

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopoverMenu.html#gtk-popover-menu-new">
		 *     gtk_popover_menu_new</a>
		 */
		constructor() : this(gtk_popover_menu_new()!!.reinterpret())

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopoverMenu.html#gtk-popover-menu-open-submenu">
		 *     gtk_popover_menu_open_submenu</a>
		 */
		fun openSubmenu(name: String) {
			gtk_popover_menu_open_submenu(popoverMenuPointer, name)
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#GtkPopoverConstraint">
	 *     GtkPopoverConstraint</a>
	 */
	enum class Constraint(
		val key: Int,
		 val gtk: GtkPopoverConstraint
	) {
		/** Don't constrain the popover position beyond what is imposed by the implementation */
		NONE(0, GTK_POPOVER_CONSTRAINT_NONE),

		/** Constrain the popover to the boundaries of the window that it is attached to */
		WINDOW(1, GTK_POPOVER_CONSTRAINT_WINDOW);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkPopoverConstraint) =
				values().find { it.gtk == gtk }

		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPopover.html#GtkPopover-closed">closed</a>
	 */
	@ExperimentalCoroutinesApi
	val closed: Flow<Unit> by signalFlow(Signals.CLOSED)

	companion object {
		inline fun CPointer<GtkPopover>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkPopover>.wrap() =
			Popover(this)
	}
}