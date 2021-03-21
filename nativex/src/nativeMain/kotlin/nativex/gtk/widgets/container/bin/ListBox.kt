package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Adjustment
import nativex.gtk.bool
import nativex.gtk.common.enums.SelectionMode
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ListBox internal constructor(
	internal val listPointer: CPointer<GtkListBox>
) : Bin(listPointer.reinterpret()) {
	constructor() : this(gtk_list_box_new()!!.reinterpret())

	fun prepend(widget: Widget) =
		gtk_list_box_prepend(listPointer, widget.widgetPointer)

	fun insert(widget: Widget, position: Int) =
		gtk_list_box_insert(listPointer, widget.widgetPointer, position)

	fun selectRow(row: Row?) {
		gtk_list_box_select_row(listPointer, row?.rowPointer)
	}

	fun unselect(row: Row) {
		gtk_list_box_unselect_row(listPointer, row.rowPointer)
	}

	fun selectAll() {
		gtk_list_box_select_all(listPointer)
	}

	fun unselectAll() {
		gtk_list_box_unselect_all(listPointer)
	}

	fun getSelectedRow() =
		gtk_list_box_get_selected_row(listPointer)?.let { Row(it) }

	fun forEachSelected() {
		TODO("gtk_list_box_get_selected_rows")
	}

	fun getSelectedRows() {
		TODO("gtk_list_box_get_selected_rows")
	}

	var selectionMode: SelectionMode
		get() = SelectionMode.valueOf(
			gtk_list_box_get_selection_mode(
				listPointer
			)
		)!!
		set(value) = gtk_list_box_set_selection_mode(listPointer, value.gtk)

	var activateOnSingleClick: Boolean
		get() = gtk_list_box_get_activate_on_single_click(
			listPointer
		)
			.bool
		set(value) = gtk_list_box_set_activate_on_single_click(
			listPointer,
			value.gtk
		)

	var adjustment: Adjustment?
		get() = gtk_list_box_get_adjustment(listPointer)?.let { Adjustment(it) }
		set(value) = gtk_list_box_set_adjustment(listPointer, value?.adjustmentPointer)


	fun setPlaceholder(widget: Widget?) =
		gtk_list_box_set_placeholder(listPointer, widget?.widgetPointer)

	fun getRowAtIndex(index: Int): Row? =
		gtk_list_box_get_row_at_index(listPointer, index)?.let { Row(it) }


	fun getRowAtY(y: Int): Row? =
		gtk_list_box_get_row_at_y(listPointer, y)?.let { Row(it) }


	fun invalidateFilter() {
		gtk_list_box_invalidate_filter(listPointer)
	}

	fun invalidateHeaders() {
		gtk_list_box_invalidate_headers(listPointer)
	}

	fun invalidateSort() {
		gtk_list_box_invalidate_sort(listPointer)
	}

	fun setFilterFunction() {
		TODO("gtk_list_box_set_filter_func")
	}

	fun setHeaderFunction() {
		TODO("gtk_list_box_set_header_func")
	}

	fun setSortFunction() {
		TODO("gtk_list_box_set_sort_func")
	}

	fun dragHighlightRow(row: Row) {
		gtk_list_box_drag_highlight_row(listPointer, row.rowPointer)
	}

	fun dragUnhighlightRow() {
		gtk_list_box_drag_unhighlight_row(listPointer)
	}

	fun bindModel() {
		TODO("gtk_list_box_bind_model")
	}


	class Row internal constructor(
		internal val rowPointer: CPointer<GtkListBoxRow>
	) : Bin(rowPointer.reinterpret()) {
		constructor() : this(
			gtk_list_box_row_new()!!.reinterpret()
		)

		fun changed() {
			gtk_list_box_row_changed(rowPointer)
		}

		fun isSelected(): Boolean =
			gtk_list_box_row_is_selected(rowPointer).bool

		var selectable: Boolean
			get() = gtk_list_box_row_get_selectable(rowPointer).bool
			set(value) = gtk_list_box_row_set_selectable(
				rowPointer,
				value.gtk
			)

		var isActivatable: Boolean
			get() = gtk_list_box_row_get_activatable(rowPointer).bool
			set(value) = gtk_list_box_row_set_activatable(
				rowPointer,
				value.gtk
			)

		var header: Widget?
			get() = gtk_list_box_row_get_header(rowPointer)?.let { Widget(it) }
			set(value) = gtk_list_box_row_set_header(
				rowPointer,
				value?.widgetPointer
			)

	}


}