package kotlin.gtk.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class ButtonBox internal constructor(
	internal val buttonBoxPointer: CPointer<GtkButtonBox>
) : Box(
	buttonBoxPointer.reinterpret()
) {
	constructor(orientation: Orientation) : this(gtk_button_box_new(orientation.gtk)!!.reinterpret())

	var buttonBoxStyle: ButtonBoxStyle
		get() = ButtonBoxStyle.valueOf(
			gtk_button_box_get_layout(
				buttonBoxPointer
			)
		)!!
		set(value) = gtk_button_box_set_layout(buttonBoxPointer, value.gtk)


	fun getChildSecondary(widget: Widget): Boolean =
		Boolean.from(
			gtk_button_box_get_child_secondary(
				buttonBoxPointer,
				widget.widgetPointer
			)
		)

	fun setChildSecondary(widget: Widget, isSecondary: Boolean) {
		gtk_button_box_set_child_secondary(
			buttonBoxPointer,
			widget.widgetPointer,
			isSecondary.gtkValue
		)
	}

	fun getChildNonHomogeneous(widget: Widget): Boolean =
		Boolean.from(
			gtk_button_box_get_child_non_homogeneous(
				buttonBoxPointer,
				widget.widgetPointer
			)
		)

	fun setChildNonHomogeneous(widget: Widget, isNonHomogeneous: Boolean) {
		gtk_button_box_set_child_non_homogeneous(
			buttonBoxPointer,
			widget.widgetPointer,
			isNonHomogeneous.gtkValue
		)
	}


	enum class ButtonBoxStyle(
		val key: Int,
		internal val gtk: GtkButtonBoxStyle
	) {
		SPREAD(0, GTK_BUTTONBOX_SPREAD),
		EDGE(1, GTK_BUTTONBOX_EDGE),
		START(2, GTK_BUTTONBOX_START),
		END(3, GTK_BUTTONBOX_END),
		CENTER(4, GTK_BUTTONBOX_CENTER),
		EXPAND(5, GTK_BUTTONBOX_EXPAND);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GtkButtonBoxStyle) =
				values().find { it.gtk == gtk }
		}
	}

	class VerticalButtonBox() : ButtonBox(Orientation.VERTICAL)

	class HorizontalButtonBox() : ButtonBox(Orientation.HORIZONTAL)
}