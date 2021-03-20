package nativex.gtk

import gtk.*
import gtk.GtkSizeGroupMode.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class SizeGroup internal constructor(
	internal val sizeGroupPointer: CPointer<GtkSizeGroup>
) {
	constructor(mode: Mode) : this(gtk_size_group_new(mode.gtk)!!.reinterpret())

	var mode: Mode
		get() = Mode.valueOf(
			gtk_size_group_get_mode(sizeGroupPointer)
		)!!
		set(value) =
			gtk_size_group_set_mode(sizeGroupPointer, value.gtk)

	fun addWidget(widget: Widget) {
		gtk_size_group_add_widget(sizeGroupPointer, widget.widgetPointer)
	}

	fun removeWidget(widget: Widget) {
		gtk_size_group_remove_widget(sizeGroupPointer, widget.widgetPointer)
	}

	val widgets: Sequence<Widget>
		get() = gtk_size_group_get_widgets(sizeGroupPointer).asKSequence<GtkWidget, Widget> {
			Widget(
				it
			)
		}

	enum class Mode(val key: Int, internal val gtk: GtkSizeGroupMode) {
		NONE(0, GTK_SIZE_GROUP_NONE),
		HORIZONTAL(1, GTK_SIZE_GROUP_HORIZONTAL),
		VERTICAL(2, GTK_SIZE_GROUP_VERTICAL),
		BOTH(3, GTK_SIZE_GROUP_BOTH);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkSizeGroupMode) =
				values().find { it.gtk == gtk }
		}
	}
}