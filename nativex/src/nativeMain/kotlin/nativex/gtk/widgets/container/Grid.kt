package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.common.enums.BaselinePosition
import nativex.gtk.common.enums.PositionType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class Grid internal constructor(
	internal val gridPointer: CPointer<GtkGrid>
) : Container(gridPointer.reinterpret()) {
	var homogeneous: Boolean
		get() = gtk_grid_get_row_homogeneous(gridPointer).bool
		set(value) = gtk_grid_set_row_homogeneous(
			gridPointer,
			value.gtk
		)

	
	var rowSpacing: UInt
		get() = gtk_grid_get_row_spacing(gridPointer)
		set(value) = gtk_grid_set_row_spacing(gridPointer, value)

	
	var columnSpacing: UInt
		get() = gtk_grid_get_column_spacing(gridPointer)
		set(value) = gtk_grid_set_column_spacing(gridPointer, value)
	var baselineRow: Int
		get() = gtk_grid_get_baseline_row(gridPointer)
		set(value) = gtk_grid_set_baseline_row(gridPointer, value)

	constructor() : this(gtk_grid_new()!!.reinterpret())

	fun attach(widget: Widget, left: Int, top: Int, width: Int, height: Int) {
		gtk_grid_attach(
			gridPointer,
			widget.widgetPointer,
			left,
			top,
			width,
			height
		)
	}

	fun attachNextTo(
		child: Widget,
		sibling: Widget?,
		side: PositionType,
		width: Int,
		height: Int
	) {
		gtk_grid_attach_next_to(
			gridPointer,
			child.widgetPointer,
			sibling?.widgetPointer,
			side.gtk,
			width,
			height
		)
	}

	fun getChildAt(left: Int, top: Int): Widget? =
		gtk_grid_get_child_at(gridPointer, left, top).asWidgetOrNull()

	fun insertRow(position: Int) {
		gtk_grid_insert_row(gridPointer, position)
	}

	fun insertColumn(position: Int) {
		gtk_grid_insert_column(gridPointer, position)
	}

	fun removeRow(position: Int) {
		gtk_grid_remove_row(gridPointer, position)
	}

	fun removeColumn(position: Int) {
		gtk_grid_remove_column(gridPointer, position)
	}

	fun insertNextTo(sibling: Widget, side: PositionType) {
		gtk_grid_insert_next_to(gridPointer, sibling.widgetPointer, side.gtk)
	}

	fun getRowBaselinePosition(row: Int) {
		gtk_grid_get_row_baseline_position(gridPointer, row)
	}

	fun setRowBaselinePosition(row: Int, pos: BaselinePosition) {
		gtk_grid_set_row_baseline_position(gridPointer, row, pos.gtk)
	}

}