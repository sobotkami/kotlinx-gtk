package kotlin.gtk.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.common.enums.BaselinePosition
import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Box internal constructor(
	internal val boxPointer: CPointer<GtkBox>
) : Container(boxPointer.reinterpret()) {
	constructor(
		orientation: Orientation,
		spacing: Int
	) : this(gtk_box_new(orientation.gtk, spacing)!!.reinterpret())


	@ExperimentalUnsignedTypes
	fun packStart(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) {
		gtk_box_pack_start(
			boxPointer,
			child.widgetPointer,
			expand.gtkValue,
			fill.gtkValue,
			padding
		)
	}

	@ExperimentalUnsignedTypes
	fun packEnd(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) {
		gtk_box_pack_end(
			boxPointer,
			child.widgetPointer,
			expand.gtkValue,
			fill.gtkValue,
			padding
		)
	}

	var isHomogeneous: Boolean
		get() = Boolean.from(gtk_box_get_homogeneous(boxPointer))
		set(value) = gtk_box_set_homogeneous(boxPointer, value.gtkValue)

	var spacing: Int
		get() = gtk_box_get_spacing(boxPointer)
		set(value) = gtk_box_set_spacing(boxPointer, value)


	fun reorderChild(child: Widget, position: Int) {
		gtk_box_reorder_child(boxPointer, child.widgetPointer, position)
	}


	fun queryChildPacking() {
		TODO("gtk_box_query_child_packing")
	}

	fun setChildPacking() {
		TODO("gtk_box_set_child_packing")
	}

	var baselinePosition: BaselinePosition
		get() = BaselinePosition.valueOf(
			gtk_box_get_baseline_position(
				boxPointer
			)
		)!!
		set(value) = gtk_box_set_baseline_position(boxPointer, value.gtk)

	var centerWidget: Widget?
		get() = gtk_box_get_center_widget(boxPointer)?.let { Widget(it) }
		set(value) = gtk_box_set_center_widget(boxPointer, value?.widgetPointer)

}