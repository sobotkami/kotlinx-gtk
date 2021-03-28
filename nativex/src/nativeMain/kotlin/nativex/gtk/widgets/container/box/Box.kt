package nativex.gtk.widgets.container.box

import gtk.*
import kotlinx.cinterop.*
import nativex.gtk.bool
import nativex.gtk.common.enums.BaselinePosition
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PackType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.Container

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Box internal constructor(
	internal val boxPointer: CPointer<GtkBox>
) : Container(boxPointer.reinterpret()) {
	constructor(other: Box) : this(other.boxPointer)
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
			expand.gtk,
			fill.gtk,
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
			expand.gtk,
			fill.gtk,
			padding
		)
	}

	var isHomogeneous: Boolean
		get() = gtk_box_get_homogeneous(boxPointer).bool
		set(value) = gtk_box_set_homogeneous(boxPointer, value.gtk)

	var spacing: Int
		get() = gtk_box_get_spacing(boxPointer)
		set(value) = gtk_box_set_spacing(boxPointer, value)


	fun reorderChild(child: Widget, position: Int) {
		gtk_box_reorder_child(boxPointer, child.widgetPointer, position)
	}

	data class ChildPacking @ExperimentalUnsignedTypes constructor(
		val expand: Boolean,
		val fill: Boolean,
		val padding: UInt,
		val packType: PackType
	)

	@ExperimentalUnsignedTypes
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