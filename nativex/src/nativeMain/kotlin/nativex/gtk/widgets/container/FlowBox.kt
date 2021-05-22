package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Adjustment
import nativex.gtk.asKSequence
import nativex.gtk.bool
import nativex.gtk.common.enums.SelectionMode
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class FlowBox internal constructor(
	internal val flowBoxPointer: CPointer<GtkFlowBox>
) : Bin(flowBoxPointer.reinterpret()) {
	var isHomogeneous: Boolean
		get() = gtk_flow_box_get_homogeneous(flowBoxPointer).bool
		set(value) = gtk_flow_box_set_homogeneous(
			flowBoxPointer,
			value.gtk
		)

	
	var rowSpacing: UInt
		get() = gtk_flow_box_get_row_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_row_spacing(flowBoxPointer, value)

	
	var columnSpacing: UInt
		get() = gtk_flow_box_get_column_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_column_spacing(flowBoxPointer, value)

	
	var minChildrenPerLine: UInt
		get() = gtk_flow_box_get_min_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_min_children_per_line(
			flowBoxPointer,
			value
		)

	
	var maxChildrenPerLine: UInt
		get() = gtk_flow_box_get_max_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_max_children_per_line(
			flowBoxPointer,
			value
		)
	var activateOnSingleClick: Boolean
		get() = gtk_flow_box_get_activate_on_single_click(
			flowBoxPointer
		)
			.bool
		set(value) = gtk_flow_box_set_activate_on_single_click(
			flowBoxPointer,
			value.gtk
		)
	var selectionMode: SelectionMode
		get() = SelectionMode.valueOf(
			gtk_flow_box_get_selection_mode(flowBoxPointer)
		)!!
		set(value) = gtk_flow_box_set_selection_mode(flowBoxPointer, value.gtk)

	constructor() : this(gtk_flow_box_new()!!.reinterpret())

	fun insert(widget: Widget, position: Int) {
		gtk_flow_box_insert(flowBoxPointer, widget.widgetPointer, position)
	}

	fun getChildAtIndex(index: Int): Child? =
		gtk_flow_box_get_child_at_index(
			flowBoxPointer,
			index
		)?.let { Child(it) }

	fun getChildAtPosition(x: Int, y: Int) =
		gtk_flow_box_get_child_at_pos(flowBoxPointer, x, y)?.let { Child(it) }

	fun setHorizontalAdjustment(adjustment: Adjustment) {
		gtk_flow_box_set_hadjustment(
			flowBoxPointer,
			adjustment.adjustmentPointer
		)
	}

	fun setVerticalAdjustment(adjustment: Adjustment) {
		gtk_flow_box_set_vadjustment(
			flowBoxPointer,
			adjustment.adjustmentPointer
		)
	}

	fun forEachSelected() {
		TODO("gtk_flow_box_selected_foreach")
	}

	fun getSelectedChildren(): Sequence<Widget> =
		gtk_flow_box_get_selected_children(flowBoxPointer).asKSequence<GtkWidget, Widget> {
			Widget(
				it
			)
		}

	fun selectChild(child: Child) =
		gtk_flow_box_select_child(flowBoxPointer, child.flowBoxChildPointer)

	fun unselectChild(child: Child) {
		gtk_flow_box_unselect_child(flowBoxPointer, child.flowBoxChildPointer)
	}

	fun selectAll() {
		gtk_flow_box_select_all(flowBoxPointer)
	}

	fun unselectAll() {
		gtk_flow_box_unselect_all(flowBoxPointer)
	}

	fun setFilterFunction() {
		TODO("gtk_flow_box_set_filter_func")
	}

	fun invalidateFilter() {
		gtk_flow_box_invalidate_filter(flowBoxPointer)
	}

	fun setSortFunction() {
		TODO("gtk_flow_box_set_sort_func")
	}

	fun invalidateSort() {
		gtk_flow_box_invalidate_sort(flowBoxPointer)
	}

	fun bindModel() {
		TODO("gtk_flow_box_bind_model")
	}

	class Child(
		internal val flowBoxChildPointer: CPointer<GtkFlowBoxChild>
	) : Bin(flowBoxChildPointer.reinterpret()) {

		val index: Int
			get() = gtk_flow_box_child_get_index(flowBoxChildPointer)
		val isSelected: Boolean
			get() = gtk_flow_box_child_is_selected(
				flowBoxChildPointer
			).bool

		constructor() : this(gtk_flow_box_child_new()!!.reinterpret())

		fun childChanged() {
			gtk_flow_box_child_changed(flowBoxChildPointer)
		}
	}
}