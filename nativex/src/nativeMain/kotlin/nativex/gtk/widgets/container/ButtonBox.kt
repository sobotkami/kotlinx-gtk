package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.common.enums.Orientation
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.box.Box

/**
 * kotlinx-gtk
 *
 * 07 / 03 / 2021
 *
 * @see <a href=""></a>
 */
open class ButtonBox internal constructor(
	internal val buttonBoxPointer: CPointer<GtkButtonBox>
) : Box(
	buttonBoxPointer.reinterpret()
) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-new">
	 *     gtk_button_box_new</a>
	 */
	constructor(orientation: Orientation) : this(gtk_button_box_new(orientation.gtk)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-get-layout">
	 *     gtk_button_box_get_layout</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-set-layout">
	 *     gtk_button_box_set_layout</a>
	 */
	var layout: Style
		get() = Style.valueOf(
			gtk_button_box_get_layout(
				buttonBoxPointer
			)
		)!!
		set(value) = gtk_button_box_set_layout(buttonBoxPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-get-child-secondary">
	 *     gtk_button_box_get_child_secondary</a>
	 */
	fun getChildSecondary(widget: Widget): Boolean =
		gtk_button_box_get_child_secondary(
			buttonBoxPointer,
			widget.widgetPointer
		)
			.bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-set-child-secondary">
	 *     gtk_button_box_set_child_secondary</a>
	 */
	fun setChildSecondary(widget: Widget, isSecondary: Boolean) {
		gtk_button_box_set_child_secondary(
			buttonBoxPointer,
			widget.widgetPointer,
			isSecondary.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-get-child-non-homogeneous">
	 *     gtk_button_box_get_child_non_homogeneous</a>
	 */
	fun getChildNonHomogeneous(widget: Widget): Boolean =
		gtk_button_box_get_child_non_homogeneous(
			buttonBoxPointer,
			widget.widgetPointer
		)
			.bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#gtk-button-box-set-child-non-homogeneous">
	 *     gtk_button_box_set_child_non_homogeneous</a>
	 */
	fun setChildNonHomogeneous(widget: Widget, isNonHomogeneous: Boolean) {
		gtk_button_box_set_child_non_homogeneous(
			buttonBoxPointer,
			widget.widgetPointer,
			isNonHomogeneous.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButtonBox.html#GtkButtonBoxStyle">GtkButtonBoxStyle</a>
	 */
	enum class Style(
		val key: Int,
		internal val gtk: GtkButtonBoxStyle
	) {
		/**
		 * Buttons are evenly spread across the box.
		 */
		SPREAD(0, GTK_BUTTONBOX_SPREAD),

		/**
		 * Buttons are placed at the edges of the box.
		 */
		EDGE(1, GTK_BUTTONBOX_EDGE),

		/**
		 * Buttons are grouped towards the start of the box,
		 * (on the left for a HBox, or the top for a VBox).
		 */
		START(2, GTK_BUTTONBOX_START),

		/**
		 * Buttons are grouped towards the end of the box,
		 * (on the right for a HBox, or the bottom for a VBox).
		 */
		END(3, GTK_BUTTONBOX_END),

		/**
		 * Buttons are centered in the box.
		 *
		 * @since 2.12
		 */
		CENTER(4, GTK_BUTTONBOX_CENTER),

		/**
		 * Buttons expand to fill the box.
		 *
		 * This entails giving buttons a "linked" appearance, making button sizes homogeneous,
		 * and setting spacing to 0 (same as setting [Box.isHomogeneous] and [Box.spacing] manually).
		 *
		 * @since 3.12
		 */
		EXPAND(5, GTK_BUTTONBOX_EXPAND);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			internal fun valueOf(gtk: GtkButtonBoxStyle) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkVButtonBox.html">GtkVButtonBox</a>
	 */
	class VerticalButtonBox : ButtonBox(Orientation.VERTICAL)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHButtonBox.html">GtkHButtonBox</a>
	 */
	class HorizontalButtonBox : ButtonBox(Orientation.HORIZONTAL)
}