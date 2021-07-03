package nativex.gtk.widgets.container.box

import gtk.*
import kotlinx.cinterop.*
import nativex.gtk.Orientable
import nativex.gtk.bool
import nativex.gtk.common.enums.BaselinePosition
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PackType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.Button

/**
 * kotlinx-gtk
 *
 * 07 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html">GtkBox</a>
 */
open class Box(
	 val boxPointer: CPointer<GtkBox>
) : Container(boxPointer.reinterpret()), Orientable {

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-pack-start">gtk_box_pack_start</a>
	 */
	fun packStart(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) {
		gtk_box_pack_start(
			boxPointer,
			child.widgetPointer,
			expand.gtk,
			fill.gtk,
			padding
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-pack-end">gtk_box_pack_end</a>
	 */
	fun packEnd(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) {
		gtk_box_pack_end(
			boxPointer,
			child.widgetPointer,
			expand.gtk,
			fill.gtk,
			padding
		)
	}

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-reorder-child">
	 *     gtk_box_reorder_child</a>
	 */
	fun reorderChild(child: Widget, position: Int) {
		gtk_box_reorder_child(boxPointer, child.widgetPointer, position)
	}

	/**
	 * Data returned from [queryChildPacking]
	 */
	data class ChildPacking  constructor(
		val expand: Boolean,
		val fill: Boolean,
		val padding: UInt,
		val packType: PackType
	)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-query-child-packing">
	 *     gtk_box_query_child_packing</a>
	 */
	fun queryChildPacking(child: Widget): ChildPacking =
		memScoped {
			val cExpand = cValue<gbooleanVar>()
			val cFill = cValue<gbooleanVar>()
			val cPadding = cValue<UIntVar>()
			val pack = cValue<GtkPackType.Var>()

			gtk_box_query_child_packing(
				boxPointer,
				child.widgetPointer,
				cExpand,
				cFill,
				cPadding,
				pack
			)

			ChildPacking(
				cExpand.ptr.pointed.value.bool,
				cFill.ptr.pointed.value.bool,
				cPadding.ptr.pointed.value,
				PackType.valueOf(pack.ptr.pointed.value)!!
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-set-child-packing">
	 *     gtk_box_set_child_packing</a>
	 */
	fun setChildPacking(child: Widget, childPacking: ChildPacking) {
		gtk_box_set_child_packing(
			boxPointer,
			child.widgetPointer,
			childPacking.expand.gtk,
			childPacking.fill.gtk,
			childPacking.padding,
			childPacking.packType.gtk
		)
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-get-center-widget">
	 *     gtk_box_get_center_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkBox.html#gtk-box-set-center-widget">
	 *     gtk_box_set_center_widget</a>
	 */
	var centerWidget: Widget?
		get() = gtk_box_get_center_widget(boxPointer)?.let { Widget(it) }
		set(value) = gtk_box_set_center_widget(boxPointer, value?.widgetPointer)

	companion object{
		 inline fun CPointer<GtkBox>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkBox>.wrap() =
			Box(this)
	}
}