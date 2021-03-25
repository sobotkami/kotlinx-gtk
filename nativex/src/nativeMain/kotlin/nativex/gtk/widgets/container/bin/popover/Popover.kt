package nativex.gtk.widgets.container.bin.popover

import gtk.*
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_NONE
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_WINDOW
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Rectangle
import nativex.gio.MenuModel
import nativex.gtk.asWidget
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.common.enums.PositionType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class Popover(
	internal val popoverPointer: CPointer<GtkPopover>
) : Bin(popoverPointer.reinterpret()) {
	constructor(relativeTo: Widget) : this(
		gtk_popover_new(relativeTo.widgetPointer)!!.reinterpret()
	)

	constructor(relativeTo: Widget, model: MenuModel) : this(
		gtk_popover_new_from_model(
			relativeTo.widgetPointer,
			model.cPointer
		)!!.reinterpret()
	)

	fun popup() {
		gtk_popover_popup(popoverPointer)
	}

	fun popdown() {
		gtk_popover_popdown(popoverPointer)
	}

	var relativeTo: Widget
		get() = gtk_popover_get_relative_to(popoverPointer).asWidget()
		set(value) {
			gtk_popover_set_relative_to(popoverPointer, value.widgetPointer)
		}

	fun setPointingTo(rectangle: Rectangle) {
		gtk_popover_set_pointing_to(popoverPointer, rectangle.rectanglePointer)
	}

	fun getPointingTo(rectangle: Rectangle): Boolean =
		gtk_popover_get_pointing_to(
			popoverPointer,
			rectangle.rectanglePointer
		).bool

	var positionType: PositionType
		set(value) = gtk_popover_set_position(popoverPointer, value.gtk)
		get() = PositionType.valueOf(
			gtk_popover_get_position(
				popoverPointer
			)
		)!!

	var constrainTo: Constraint
		get() = Constraint.valueOf(
			gtk_popover_get_constrain_to(popoverPointer)
		)!!
		set(value) = gtk_popover_set_constrain_to(popoverPointer, value.gtk)

	var isModal: Boolean
		get() = gtk_popover_get_modal(popoverPointer).bool
		set(value) = gtk_popover_set_modal(popoverPointer, value.gtk)

	var transitionsEnabled: Boolean
		get() = gtk_popover_get_transitions_enabled(popoverPointer).bool
		set(value) = gtk_popover_set_transitions_enabled(
			popoverPointer,
			value.gtk
		)

	var defaultWidget: Widget?
		get() = gtk_popover_get_default_widget(popoverPointer).asWidgetOrNull()
		set(value) = gtk_popover_set_default_widget(
			popoverPointer,
			value?.widgetPointer
		)

	class Menu internal constructor(
		internal val popoverMenuPointer: CPointer<GtkPopoverMenu>
	) : Popover(popoverMenuPointer.reinterpret()) {
		constructor() : this(gtk_popover_menu_new()!!.reinterpret())

		fun openSubmenu(name: String) {
			gtk_popover_menu_open_submenu(popoverMenuPointer, name)
		}
	}

	enum class Constraint(
		val key: Int,
		internal val gtk: GtkPopoverConstraint
	) {
		NONE(0, GTK_POPOVER_CONSTRAINT_NONE),
		WINDOW(1, GTK_POPOVER_CONSTRAINT_WINDOW);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkPopoverConstraint) =
				values().find { it.gtk == gtk }

		}
	}
}