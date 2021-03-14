package kotlin.gtk.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.*
import kotlin.gtk.common.enums.SelectionMode
import kotlin.gtk.container.bin.Bin
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class FlowBox internal constructor(
	internal val flowBoxPointer: CPointer<GtkFlowBox>
) : Bin(flowBoxPointer.reinterpret()) {
	constructor() : this(gtk_flow_box_new()!!.reinterpret())

	class Child(
		internal val flowBoxChildPointer: CPointer<GtkFlowBoxChild>
	) : Bin(flowBoxChildPointer.reinterpret()) {
		constructor() : this(gtk_flow_box_child_new()!!.reinterpret())

		val index: Int
			get() = gtk_flow_box_child_get_index(flowBoxChildPointer)

		val isSelected: Boolean
			get() = Boolean.from(
				gtk_flow_box_child_is_selected(
					flowBoxChildPointer
				)
			)

		fun childChanged() {
			gtk_flow_box_child_changed(flowBoxChildPointer)
		}
	}

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
		gtk_flow_box_set_hadjustment(flowBoxPointer, adjustment.pointer)
	}

	fun setVerticalAdjustment(adjustment: Adjustment) {
		gtk_flow_box_set_vadjustment(flowBoxPointer, adjustment.pointer)
	}

	var isHomogeneous: Boolean
		get() = Boolean.from(gtk_flow_box_get_homogeneous(flowBoxPointer))
		set(value) = gtk_flow_box_set_homogeneous(
			flowBoxPointer,
			value.gtkValue
		)

	@ExperimentalUnsignedTypes
	var rowSpacing: UInt
		get() = gtk_flow_box_get_row_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_row_spacing(flowBoxPointer, value)

	@ExperimentalUnsignedTypes
	var columnSpacing: UInt
		get() = gtk_flow_box_get_column_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_column_spacing(flowBoxPointer, value)

	@ExperimentalUnsignedTypes
	var minChildrenPerLine: UInt
		get() = gtk_flow_box_get_min_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_min_children_per_line(
			flowBoxPointer,
			value
		)

	@ExperimentalUnsignedTypes
	var maxChildrenPerLine: UInt
		get() = gtk_flow_box_get_max_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_max_children_per_line(
			flowBoxPointer,
			value
		)

	var activateOnSingleClick: Boolean
		get() = Boolean.from(
			gtk_flow_box_get_activate_on_single_click(
				flowBoxPointer
			)
		)
		set(value) = gtk_flow_box_set_activate_on_single_click(
			flowBoxPointer,
			value.gtkValue
		)

	fun forEachSelected() {
		TODO("gtk_flow_box_selected_foreach")
	}

	fun getSelectedChildren(): Sequence<Widget> = object : Sequence<Widget> {
		private val gList by lazy {
			gtk_flow_box_get_selected_children(flowBoxPointer)
		}

		private val gListIterator: Iterator<CPointer<GtkWidget>> =
			gList.asSequence<GtkWidget>().iterator()

		private val _iterator: Iterator<Widget> by lazy {
			object : Iterator<Widget> {
				override fun hasNext(): Boolean =
					gListIterator.hasNext().also {
						if (!it)
							gList.free()
					}

				override fun next(): Widget =
					Widget(gListIterator.next())

			}
		}

		override fun iterator(): Iterator<Widget> =
			_iterator
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

	var selectionMode: SelectionMode
		get() = SelectionMode.valueOf(
			gtk_flow_box_get_selection_mode(flowBoxPointer)
		)!!
		set(value) = gtk_flow_box_set_selection_mode(flowBoxPointer, value.gtk)

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
}