package nativex.gtk.widgets.container
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.asWidgetOrNull
import nativex.gtk.common.enums.BaselinePosition
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 20 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html">GtkGrid</a>
 */
class Grid(
	 val gridPointer: CPointer<GtkGrid>
) : Container(gridPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-row-homogeneous">
	 *     gtk_grid_get_row_homogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-row-homogeneous">
	 *     gtk_grid_set_row_homogeneous</a>
	 */
	var homogeneous: Boolean
		get() = gtk_grid_get_row_homogeneous(gridPointer).bool
		set(value) = gtk_grid_set_row_homogeneous(
			gridPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-row-spacing">
	 *     gtk_grid_get_row_spacing</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-row-spacing">
	 *     gtk_grid_set_row_spacing</a>
	 */
	var rowSpacing: UInt
		get() = gtk_grid_get_row_spacing(gridPointer)
		set(value) = gtk_grid_set_row_spacing(gridPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-column-homogeneous">
	 *     gtk_grid_get_column_homogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-column-homogeneous">
	 *     gtk_grid_set_column_homogeneous</a>
	 */
	var columnHomogeneous:Boolean
		get() = gtk_grid_get_column_homogeneous(gridPointer).bool
		set(value) = gtk_grid_set_column_homogeneous(
			gridPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-column-spacing">
	 *     gtk_grid_get_column_spacing</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-column-spacing">
	 *     gtk_grid_set_column_spacing</a>
	 */
	var columnSpacing: UInt
		get() = gtk_grid_get_column_spacing(gridPointer)
		set(value) = gtk_grid_set_column_spacing(gridPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-baseline-row">
	 *     gtk_grid_get_baseline_row</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-baseline-row">
	 *     gtk_grid_set_baseline_row</a>
	 */
	var baselineRow: Int
		get() = gtk_grid_get_baseline_row(gridPointer)
		set(value) = gtk_grid_set_baseline_row(gridPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-new">gtk_grid_new</a>
	 */
	constructor() : this(gtk_grid_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-attach">gtk_grid_attach</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-attach-next-to">
	 *     gtk_grid_attach_next_to</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-child-at">
	 *     gtk_grid_get_child_at</a>
	 */
	fun getChildAt(left: Int, top: Int): Widget? =
		gtk_grid_get_child_at(gridPointer, left, top).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-insert-row">
	 *     gtk_grid_insert_row</a>
	 */
	fun insertRow(position: Int) {
		gtk_grid_insert_row(gridPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-insert-column">
	 *     gtk_grid_insert_column</a>
	 */
	fun insertColumn(position: Int) {
		gtk_grid_insert_column(gridPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-remove-row">gtk_grid_remove_row</a>
	 */
	fun removeRow(position: Int) {
		gtk_grid_remove_row(gridPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-remove-column">
	 *     gtk_grid_remove_column</a>
	 */
	fun removeColumn(position: Int) {
		gtk_grid_remove_column(gridPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-insert-next-to">
	 *     gtk_grid_insert_next_to</a>
	 */
	fun insertNextTo(sibling: Widget, side: PositionType) {
		gtk_grid_insert_next_to(gridPointer, sibling.widgetPointer, side.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-get-row-baseline-position">
	 *     gtk_grid_get_row_baseline_position</a>
	 */
	fun getRowBaselinePosition(row: Int): BaselinePosition =
		BaselinePosition.valueOf(gtk_grid_get_row_baseline_position(gridPointer, row))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkGrid.html#gtk-grid-set-row-baseline-position">
	 *     gtk_grid_set_row_baseline_position</a>
	 */
	fun setRowBaselinePosition(row: Int, pos: BaselinePosition) {
		gtk_grid_set_row_baseline_position(gridPointer, row, pos.gtk)
	}

}