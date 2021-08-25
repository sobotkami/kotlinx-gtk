package org.gtk.gtk.widgets.box

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gtk.Accessible
import org.gtk.gtk.Buildable
import org.gtk.gtk.ConstraintTarget
import org.gtk.gtk.Orientable
import org.gtk.gtk.common.enums.BaselinePosition
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.widgets.Widget

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
	override val accessiblePointer: CPointer<GtkAccessible> by lazy { boxPointer.reinterpret() }
	override val buildablePointer: CPointer<GtkBuildable> by lazy { boxPointer.reinterpret() }
	override val constraintTargetPointer: CPointer<GtkConstraintTarget> by lazy { boxPointer.reinterpret() }
	override val orientablePointer: CPointer<GtkOrientable> by lazy { boxPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-new">gtk_box_new</a>
	 */
	constructor(
		orientation: Orientation,
		spacing: Int
	) : this(gtk_box_new(orientation.gtk, spacing)!!.reinterpret())

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Box.append.html">gtk_box_append</a>
	 */
	fun append(widget: Widget) {
		gtk_box_append(boxPointer, widget.widgetPointer)
	}

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
	 * @see <a href="https://docs.gtk.org/gtk4/method.Box.insert_child_after.html">gtk_box_insert_child_after</a>
	 */
	fun insertChildAfter(child: Widget, sibling: Widget) {
		gtk_box_insert_child_after(boxPointer, child.widgetPointer, sibling.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Box.prepend.html">gtk_box_prepend</a>
	 */
	fun prepend(widget: Widget) {
		gtk_box_prepend(boxPointer, widget.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Box.remove.html">gtk_box_remove</a>
	 */
	fun remove(widget: Widget) {
		gtk_box_remove(boxPointer, widget.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Box.reorder_child_after.html">gtk_box_reorder_child_after</a>
	 */
	fun reorderChildAfter(child: Widget, sibling: Widget) {
		gtk_box_reorder_child_after(boxPointer, child.widgetPointer, sibling.widgetPointer)
	}


	companion object {
		inline fun CPointer<GtkBox>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkBox>.wrap() =
			Box(this)
	}
}