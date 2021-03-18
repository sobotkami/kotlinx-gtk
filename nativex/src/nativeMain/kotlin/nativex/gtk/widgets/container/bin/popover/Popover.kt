package nativex.gtk.widgets.container.bin.popover

import gtk.*
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_NONE
import gtk.GtkPopoverConstraint.GTK_POPOVER_CONSTRAINT_WINDOW
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.g.MenuModel
import nativex.gtk.asWidget
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Popover(
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

	//fun setPointingTo()
	//fun getPointingTo()

//	var positionType: PositionType
	//var constainTo: Constraint

	//fun setModal()
	//fun getModal()

//	var transitionsEnabled: Boolean

//	var defaultWidget: Widget

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