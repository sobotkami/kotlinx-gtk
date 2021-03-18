package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.common.enums.Orientation
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

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
		gtk_button_box_get_child_secondary(
			buttonBoxPointer,
			widget.widgetPointer
		)
			.bool

	fun setChildSecondary(widget: Widget, isSecondary: Boolean) {
		gtk_button_box_set_child_secondary(
			buttonBoxPointer,
			widget.widgetPointer,
			isSecondary.gtk
		)
	}

	fun getChildNonHomogeneous(widget: Widget): Boolean =
		gtk_button_box_get_child_non_homogeneous(
			buttonBoxPointer,
			widget.widgetPointer
		)
			.bool

	fun setChildNonHomogeneous(widget: Widget, isNonHomogeneous: Boolean) {
		gtk_button_box_set_child_non_homogeneous(
			buttonBoxPointer,
			widget.widgetPointer,
			isNonHomogeneous.gtk
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

	class VerticalButtonBox : ButtonBox(Orientation.VERTICAL)

	class HorizontalButtonBox : ButtonBox(Orientation.HORIZONTAL)
}