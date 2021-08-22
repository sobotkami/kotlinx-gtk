package nativex.gtk.widgets.box

import gtk.*
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.Accessible
import nativex.gtk.Buildable
import nativex.gtk.ConstraintTarget
import nativex.gtk.Orientable
import nativex.gtk.common.enums.BaselinePosition
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PackType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 07 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html">GtkBox</a>
 */
open class Box(
	val boxPointer: CPointer<GtkBox>
) : Widget(boxPointer.reinterpret()), Accessible, Buildable, ConstraintTarget, Orientable {

	override val orientablePointer: CPointer<GtkOrientable> by lazy { boxPointer.reinterpret() }

	constructor(other: Box) : this(other.boxPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-new">gtk_box_new</a>
	 */
	constructor(
		orientation: Orientation,
		spacing: Int
	) : this(gtk_box_new(orientation.gtk, spacing)!!.reinterpret())

	/**
	 * Cast constructor
	 */
	constructor(widget: Widget) : this(widget.widgetPointer.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-get-homogeneous">
	 *     gtk_box_get_homogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-set-homogeneous">
	 *     gtk_box_set_homogeneous</a>
	 */
	var isHomogeneous: Boolean
		get() = gtk_box_get_homogeneous(boxPointer).bool
		set(value) = gtk_box_set_homogeneous(boxPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-get-spacing">gtk_box_get_spacing</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-set-spacing">gtk_box_set_spacing</a>
	 */
	var spacing: Int
		get() = gtk_box_get_spacing(boxPointer)
		set(value) = gtk_box_set_spacing(boxPointer, value)


	/**
	 * Data returned from [queryChildPacking]
	 */
	data class ChildPacking constructor(
		val expand: Boolean,
		val fill: Boolean,
		val padding: UInt,
		val packType: PackType
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-get-baseline-position">
	 *     gtk_box_get_baseline_position</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-set-baseline-position">
	 *     gtk_box_set_baseline_position</a>
	 */
	var baselinePosition: BaselinePosition
		get() = BaselinePosition.valueOf(
			gtk_box_get_baseline_position(
				boxPointer
			)
		)!!
		set(value) = gtk_box_set_baseline_position(boxPointer, value.gtk)

	companion object {
		inline fun CPointer<GtkBox>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkBox>.wrap() =
			Box(this)
	}

	override val accessiblePointer: CPointer<GtkAccessible> by lazy { boxPointer.reinterpret() }
	override val buildablePointer: CPointer<GtkBuildable> by lazy { boxPointer.reinterpret() }
	override val constraintTargetPointer: CPointer<GtkConstraintTarget> by lazy { boxPointer.reinterpret() }
}