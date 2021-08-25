package nativex.gtk.widgets

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.gio.ListModel
import nativex.glib.asKSequence
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.KGObject
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback
import nativex.gobject.staticDestroyStableRefFunction
import nativex.gtk.Adjustment
import nativex.gtk.common.enums.SelectionMode
import nativex.gtk.common.events.MoveCursorEvent

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html">GtkListBox</a>
 */
class ListBox(
	val listBoxPointer: CPointer<GtkListBox>
) : Widget(listBoxPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-new">gtk_list_box_new</a>
	 */
	constructor() : this(gtk_list_box_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-prepend">gtk_list_box_prepend</a>
	 */
	fun prepend(widget: Widget) =
		gtk_list_box_prepend(listBoxPointer, widget.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-insert">gtk_list_box_insert</a>
	 */
	fun insert(widget: Widget, position: Int) =
		gtk_list_box_insert(listBoxPointer, widget.widgetPointer, position)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-select-row">gtk_list_box_select_row</a>
	 */
	fun select(row: Row?) {
		gtk_list_box_select_row(listBoxPointer, row?.rowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-unselect-row">gtk_list_box_unselect_row</a>
	 */
	fun unselect(row: Row) {
		gtk_list_box_unselect_row(listBoxPointer, row.rowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-select-all">gtk_list_box_select_all</a>
	 */
	fun selectAll() {
		gtk_list_box_select_all(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-unselect-all">gtk_list_box_unselect_all</a>
	 */
	fun unselectAll() {
		gtk_list_box_unselect_all(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-selected-row">gtk_list_box_get_selected_row</a>
	 */
	val selectedRow
		get() = gtk_list_box_get_selected_row(listBoxPointer)?.let { Row(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-selected-foreach">gtk_list_box_selected_foreach</a>
	 */
	fun forEachSelected(action: ListBoxForEachFunction) {
		val ref = StableRef.create(action)
		gtk_list_box_selected_foreach(listBoxPointer, staticListBoxForEachFunction, ref.asCPointer())
		ref.dispose()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-selected-rows">gtk_list_box_get_selected_rows</a>
	 */
	val selectedRows: Sequence<Row>
		get() = gtk_list_box_get_selected_rows(listBoxPointer).asKSequence<GtkListBoxRow, Row> { Row(it) }


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-selection-mode">gtk_list_box_get_selection_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-selection-mode">gtk_list_box_set_selection_mode</a>
	 */
	var selectionMode: SelectionMode
		get() = SelectionMode.valueOf(
			gtk_list_box_get_selection_mode(
				listBoxPointer
			)
		)!!
		set(value) = gtk_list_box_set_selection_mode(listBoxPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-activate-on-single-click">gtk_list_box_get_activate_on_single_click</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-activate-on-single-click">gtk_list_box_set_activate_on_single_click</a>
	 */
	var activateOnSingleClick: Boolean
		get() = gtk_list_box_get_activate_on_single_click(
			listBoxPointer
		)
			.bool
		set(value) = gtk_list_box_set_activate_on_single_click(
			listBoxPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-adjustment">gtk_list_box_get_adjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-adjustment">gtk_list_box_set_adjustment</a>
	 */
	var adjustment: Adjustment?
		get() = gtk_list_box_get_adjustment(listBoxPointer)?.let { Adjustment(it) }
		set(value) = gtk_list_box_set_adjustment(listBoxPointer, value?.adjustmentPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-placeholder">gtk_list_box_set_placeholder</a>
	 */
	fun setPlaceholder(widget: Widget?) =
		gtk_list_box_set_placeholder(listBoxPointer, widget?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-row-at-index">gtk_list_box_get_row_at_index</a>
	 */
	fun getRowAtIndex(index: Int): Row? =
		gtk_list_box_get_row_at_index(listBoxPointer, index)?.let { Row(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-get-row-at-y">gtk_list_box_get_row_at_y</a>
	 */
	fun getRowAtY(y: Int): Row? =
		gtk_list_box_get_row_at_y(listBoxPointer, y)?.let { Row(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-invalidate-filter">gtk_list_box_invalidate_filter</a>
	 */
	fun invalidateFilter() {
		gtk_list_box_invalidate_filter(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-invalidate-headers">gtk_list_box_invalidate_headers</a>
	 */
	fun invalidateHeaders() {
		gtk_list_box_invalidate_headers(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-invalidate-sort">gtk_list_box_invalidate_sort</a>
	 */
	fun invalidateSort() {
		gtk_list_box_invalidate_sort(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-filter-func">gtk_list_box_set_filter_func</a>
	 */
	fun setFilterFunction(predicate: ListBoxFilterFunction) {
		gtk_list_box_set_filter_func(
			listBoxPointer,
			staticListBoxFilterFunction,
			StableRef.create(predicate).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-header-func">gtk_list_box_set_header_func</a>
	 */
	fun setHeaderFunction(headerFunc: ListBoxUpdateHeaderFunction) {
		gtk_list_box_set_header_func(
			listBoxPointer,
			staticListBoxUpdateHeaderFunction,
			StableRef.create(headerFunc).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-set-sort-func">gtk_list_box_set_sort_func</a>
	 */
	fun setSortFunction(sort: ListBoxSortFunction) {
		gtk_list_box_set_sort_func(
			listBoxPointer,
			staticListBoxSortFunction,
			StableRef.create(sort).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-drag-highlight-row">gtk_list_box_drag_highlight_row</a>
	 */
	fun dragHighlightRow(row: Row) {
		gtk_list_box_drag_highlight_row(listBoxPointer, row.rowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-drag-unhighlight-row">gtk_list_box_drag_unhighlight_row</a>
	 */
	fun dragUnhighlightRow() {
		gtk_list_box_drag_unhighlight_row(listBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-bind-model">gtk_list_box_bind_model</a>
	 */
	fun bindModel(model: ListModel, createWidgetFunction: ListBoxCreateWidgetFunction) {
		gtk_list_box_bind_model(
			listBoxPointer,
			model.listModelPointer,
			staticListBoxCreateWidgetFunction,
			StableRef.create(createWidgetFunction).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-new">GtkListBoxRow</a>
	 */
	class Row(
		val rowPointer: CPointer<GtkListBoxRow>
	) : Widget(rowPointer.reinterpret()) {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-new">gtk_list_box_row_new</a>
		 */
		constructor() : this(
			gtk_list_box_row_new()!!.reinterpret()
		)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-changed">gtk_list_box_row_changed</a>
		 */
		fun changed() {
			gtk_list_box_row_changed(rowPointer)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-is-selected">gtk_list_box_row_is_selected</a>
		 */
		val isSelected: Boolean
			get() = gtk_list_box_row_is_selected(rowPointer).bool

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-get-selectable">gtk_list_box_row_get_selectable</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-set-selectable">gtk_list_box_row_set_selectable</a>
		 */
		var selectable: Boolean
			get() = gtk_list_box_row_get_selectable(rowPointer).bool
			set(value) = gtk_list_box_row_set_selectable(
				rowPointer,
				value.gtk
			)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-get-activatable">gtk_list_box_row_get_activatable</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-set-activatable">gtk_list_box_row_set_activatable</a>
		 */
		var activatable: Boolean
			get() = gtk_list_box_row_get_activatable(rowPointer).bool
			set(value) = gtk_list_box_row_set_activatable(
				rowPointer,
				value.gtk
			)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-get-header">gtk_list_box_row_get_header</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-set-header">gtk_list_box_row_set_header</a>
		 */
		var header: Widget?
			get() = gtk_list_box_row_get_header(rowPointer)?.let { Widget(it) }
			set(value) = gtk_list_box_row_set_header(
				rowPointer,
				value?.widgetPointer
			)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#gtk-list-box-row-get-index>gtk_list_box_row_get_index</a>
		 */
		val index: Int
			get() = gtk_list_box_row_get_index(rowPointer)

	}

	fun addOnActivateCursorRowCallback(action: () -> Unit) =
		addSignalCallback(Signals.ACTIVATE_CURSOR_ROW, action)

	fun addOnMoveCursorCallback(action: (MoveCursorEvent) -> Unit) =
		addSignalCallback(Signals.MOVE_CURSOR, action, MoveCursorEvent.staticCallback)

	fun addOnRowActivatedCallback(action: (Row) -> Unit) =
		addSignalCallback(Signals.ROW_ACTIVATED, action, staticRowEventCallback)

	fun addOnRowSelectedCallback(action: (Row) -> Unit) =
		addSignalCallback(Signals.ROW_SELECTED, action, staticRowEventCallback)

	fun addOnSelectAllCallback(action: () -> Unit) = addSignalCallback(Signals.SELECT_ALL, action)

	fun addOnSelectedRowsChangedCallback(action: () -> Unit) = addSignalCallback(Signals.SELECTED_ROWS_CHANGED, action)

	fun addOnToggleCursorRowCallback(action: () -> Unit) = addSignalCallback(Signals.TOGGLE_CURSOR_ROW, action)

	fun addOnUnselectAllCallback(action: () -> Unit) = addSignalCallback(Signals.UNSELECT_ALL, action)

	fun addOnActivateCallback(action: () -> Unit) = addSignalCallback(Signals.ACTIVATE, action)

	companion object {
		val staticRowEventCallback: GCallback =
			staticCFunction { _: CPointer<GtkListBox>, row: CPointer<GtkListBoxRow>, data: gpointer ->
				data.asStableRef<(Row) -> Unit>().get().invoke(Row(row))
				Unit
			}.reinterpret()

		val staticListBoxFilterFunction: GtkListBoxFilterFunc = staticCFunction { row, data ->
			data?.asStableRef<ListBoxFilterFunction>()?.get()?.invoke(Row(row!!))?.gtk ?: 0
		}

		val staticListBoxForEachFunction: GtkListBoxForeachFunc = staticCFunction { _, row, data ->
			data?.asStableRef<ListBoxForEachFunction>()?.get()?.invoke(Row(row!!))
			Unit
		}

		val staticListBoxSortFunction: GtkListBoxSortFunc = staticCFunction { row1, row2, data ->
			data?.asStableRef<ListBoxSortFunction>()?.get()?.invoke(Row(row1!!), Row(row2!!)) ?: 0
		}

		val staticListBoxUpdateHeaderFunction: GtkListBoxUpdateHeaderFunc =
			staticCFunction { row, before, data ->
				data?.asStableRef<ListBoxUpdateHeaderFunction>()?.get()?.invoke(Row(row!!), Row(before!!))
				Unit
			}

		val staticListBoxCreateWidgetFunction: GtkListBoxCreateWidgetFunc = staticCFunction { item, data ->
			data?.asStableRef<ListBoxCreateWidgetFunction>()?.get()
				?.invoke(KGObject(item!!.reinterpret()))?.widgetPointer
		}
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#GtkListBoxFilterFunc">GtkListBoxFilterFunc</a>
 */
typealias ListBoxFilterFunction = (ListBox.Row) -> Boolean

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#GtkListBoxSortFunc">GtkListBoxSortFunc</a>
 */
typealias ListBoxSortFunction = (@ParameterName("row1") ListBox.Row, @ParameterName("row2") ListBox.Row) -> Int

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#GtkListBoxUpdateHeaderFunc">GtkListBoxUpdateHeaderFunc</a>
 */
typealias ListBoxUpdateHeaderFunction = (ListBox.Row, @ParameterName("before") ListBox.Row) -> Unit


/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#GtkListBoxForeachFunc">GtkListBoxForeachFunc</a>
 */
typealias ListBoxForEachFunction = (ListBox.Row) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkListBox.html#GtkListBoxCreateWidgetFunc">GtkListBoxCreateWidgetFunc</a>
 */
typealias ListBoxCreateWidgetFunction = (KGObject) -> Widget
