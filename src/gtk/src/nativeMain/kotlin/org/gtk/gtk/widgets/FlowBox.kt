package org.gtk.gtk.widgets

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.gio.ListModel
import org.gtk.glib.asKSequence
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gobject.staticDestroyStableRefFunction
import org.gtk.gtk.Adjustment
import org.gtk.gtk.Orientable
import org.gtk.gtk.common.enums.SelectionMode
import org.gtk.gtk.common.events.MoveCursorEvent
import org.gtk.gtk.widgets.FlowBox.Child.Companion.wrap

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html">GtkFlowBox</a>
 */
class FlowBox(
	val flowBoxPointer: CPointer<GtkFlowBox>
) : Widget(flowBoxPointer.reinterpret()), Orientable {

	override val orientablePointer: CPointer<GtkOrientable> by lazy { flowBoxPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-homogeneous">gtk_flow_box_get_homogeneous</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-homogeneous">gtk_flow_box_set_homogeneous</a>
	 */
	var isHomogeneous: Boolean
		get() = gtk_flow_box_get_homogeneous(flowBoxPointer).bool
		set(value) = gtk_flow_box_set_homogeneous(
			flowBoxPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-row-spacing">gtk_flow_box_get_row_spacing</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-row-spacing">gtk_flow_box_set_row_spacing</a>
	 */
	var rowSpacing: UInt
		get() = gtk_flow_box_get_row_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_row_spacing(flowBoxPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-column-spacing">gtk_flow_box_get_column_spacing</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-column-spacing">gtk_flow_box_set_column_spacing</a>
	 */
	var columnSpacing: UInt
		get() = gtk_flow_box_get_column_spacing(flowBoxPointer)
		set(value) = gtk_flow_box_set_column_spacing(flowBoxPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-min-children-per-line">gtk_flow_box_get_min_children_per_line</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-min-children-per-line">gtk_flow_box_set_min_children_per_line</a>
	 */
	var minChildrenPerLine: UInt
		get() = gtk_flow_box_get_min_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_min_children_per_line(
			flowBoxPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-max-children-per-line">gtk_flow_box_get_max_children_per_line</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-max-children-per-line">gtk_flow_box_set_max_children_per_line</a>
	 */
	var maxChildrenPerLine: UInt
		get() = gtk_flow_box_get_max_children_per_line(flowBoxPointer)
		set(value) = gtk_flow_box_set_max_children_per_line(
			flowBoxPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-activate-on-single-click">gtk_flow_box_get_activate_on_single_click</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-activate-on-single-click">gtk_flow_box_set_activate_on_single_click</a>
	 */
	var activateOnSingleClick: Boolean
		get() = gtk_flow_box_get_activate_on_single_click(
			flowBoxPointer
		)
			.bool
		set(value) = gtk_flow_box_set_activate_on_single_click(
			flowBoxPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-selection-mode">gtk_flow_box_get_selection_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-selection-mode">gtk_flow_box_set_selection_mode</a>
	 */
	var selectionMode: SelectionMode
		get() = SelectionMode.valueOf(
			gtk_flow_box_get_selection_mode(flowBoxPointer)
		)!!
		set(value) = gtk_flow_box_set_selection_mode(flowBoxPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-new">gtk_flow_box_new</a>
	 */
	constructor() : this(gtk_flow_box_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-insert">gtk_flow_box_insert</a>
	 */
	fun insert(widget: Widget, position: Int) {
		gtk_flow_box_insert(flowBoxPointer, widget.widgetPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-child-at-index">gtk_flow_box_get_child_at_index</a>
	 */
	fun getChildAtIndex(index: Int): Child? =
		gtk_flow_box_get_child_at_index(
			flowBoxPointer,
			index
		)?.let { Child(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-child-at-pos">gtk_flow_box_get_child_at_pos</a>
	 */
	fun getChildAtPosition(x: Int, y: Int) =
		gtk_flow_box_get_child_at_pos(flowBoxPointer, x, y)?.let { Child(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-hadjustment">gtk_flow_box_set_hadjustment</a>
	 */
	fun setHorizontalAdjustment(adjustment: Adjustment) {
		gtk_flow_box_set_hadjustment(
			flowBoxPointer,
			adjustment.adjustmentPointer
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-vadjustment">gtk_flow_box_set_vadjustment</a>
	 */
	fun setVerticalAdjustment(adjustment: Adjustment) {
		gtk_flow_box_set_vadjustment(
			flowBoxPointer,
			adjustment.adjustmentPointer
		)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-selected-foreach">gtk_flow_box_selected_foreach</a>
	 */
	fun forEachSelected(action: FlowBoxForEachFunction) {
		val ref = StableRef.create(action)
		gtk_flow_box_selected_foreach(
			flowBoxPointer,
			staticFlowBoxForeachFunction,
			data = ref.asCPointer()
		)
		ref.dispose()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-get-selected-children">gtk_flow_box_get_selected_children</a>
	 */
	fun getSelectedChildren(): Sequence<Widget> =
		gtk_flow_box_get_selected_children(flowBoxPointer).asKSequence<GtkWidget, Widget> { Widget(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-select-child">gtk_flow_box_select_child</a>
	 */
	fun selectChild(child: Child) =
		gtk_flow_box_select_child(flowBoxPointer, child.flowBoxChildPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-unselect-child">gtk_flow_box_unselect_child</a>
	 */
	fun unselectChild(child: Child) {
		gtk_flow_box_unselect_child(flowBoxPointer, child.flowBoxChildPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-select-all">gtk_flow_box_select_all</a>
	 */
	fun selectAll() {
		gtk_flow_box_select_all(flowBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-unselect-all">gtk_flow_box_unselect_all</a>
	 */
	fun unselectAll() {
		gtk_flow_box_unselect_all(flowBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-filter-func">gtk_flow_box_set_filter_func</a>
	 */
	fun setFilterFunction(filter: FlowBoxFilterFunction) {
		gtk_flow_box_set_filter_func(
			flowBoxPointer,
			staticFlowBoxFilterFunction,
			StableRef.create(filter).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-invalidate-filter">gtk_flow_box_invalidate_filter</a>
	 */
	fun invalidateFilter() {
		gtk_flow_box_invalidate_filter(flowBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-set-sort-func">gtk_flow_box_set_sort_func</a>
	 */
	fun setSortFunction(sort: FlowBoxSortFunction) {
		gtk_flow_box_set_sort_func(
			flowBoxPointer,
			staticFlowBoxSortFunction,
			StableRef.create(sort).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-invalidate-sort">gtk_flow_box_invalidate_sort</a>
	 */
	fun invalidateSort() {
		gtk_flow_box_invalidate_sort(flowBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-bind-model">gtk_flow_box_bind_model</a>
	 */
	fun bindModel(model: ListModel, createWidget: FlowBoxCreateWidgetFunction) {
		gtk_flow_box_bind_model(
			flowBoxPointer,
			model = model.listModelPointer,
			staticFlowBoxCreateWidgetFunction,
			user_data = StableRef.create(createWidget).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxChild-struct">GtkFlowBoxChild</a>
	 */
	class Child(
		val flowBoxChildPointer: CPointer<GtkFlowBoxChild>
	) : Widget(flowBoxChildPointer.reinterpret()) {

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-child-get-index">gtk_flow_box_child_get_index</a>
		 */
		val index: Int
			get() = gtk_flow_box_child_get_index(flowBoxChildPointer)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-child-is-selected">gtk_flow_box_child_is_selected</a>
		 */
		val isSelected: Boolean
			get() = gtk_flow_box_child_is_selected(
				flowBoxChildPointer
			).bool

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-child-new">gtk_flow_box_child_new</a>
		 */
		constructor() : this(gtk_flow_box_child_new()!!.reinterpret())

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#gtk-flow-box-child-changed">gtk_flow_box_child_changed</a>
		 */
		fun childChanged() {
			gtk_flow_box_child_changed(flowBoxChildPointer)
		}

		companion object {

			inline fun CPointer<GtkFlowBoxChild>?.wrap() =
				this?.wrap()

			inline fun CPointer<GtkFlowBoxChild>.wrap() =
				Child(this)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxChild-activate">activate</a>
		 */
		fun addOnActivateCallback(action: () -> Unit) =
			addSignalCallback(Signals.ACTIVATE, action)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-activate-cursor-child">activate-cursor-child</a>
	 */
	fun addOnActivateCursorChildCallback(action: () -> Unit) =
		addSignalCallback(Signals.ACTIVATE_CURSOR_CHILD, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-child-activated">child-activated</a>
	 */
	fun addOnChildActivatedCallback(action: (Child) -> Unit) =
		addSignalCallback(Signals.CHILD_ACTIVATED, action, staticChildActivatedCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-move-cursor">move-cursor</a>
	 */
	fun addOnMoveCursorCallback(action: (MoveCursorEvent) -> Unit) =
		addSignalCallback(Signals.MOVE_CURSOR, action, MoveCursorEvent.staticCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-select-all">select-all</a>
	 */
	fun addOnSelectAllCallback(action: () -> Unit) = addSignalCallback(Signals.SELECT_ALL, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-selected-children-changed">selected-children-changed</a>
	 */
	fun addOnSelectedChildrenChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.SELECTED_CHILDREN_CHANGED, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-toggle-cursor-child">toggle-cursor-child</a>
	 */
	fun addOnToggleCursorChildCallback(action: () -> Unit) = addSignalCallback(Signals.TOGGLE_CURSOR_CHILD, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBox-unselect-all">unselect-all</a>
	 */
	fun addOnUnselectAllCallback(action: () -> Unit) = addSignalCallback(Signals.UNSELECT_ALL, action)

	companion object {
		val staticChildActivatedCallback: GCallback =
			staticCFunction { _: CPointer<GtkFlowBox>, child: CPointer<GtkFlowBoxChild>, data: gpointer? ->
				data?.asStableRef<(Child) -> Unit>()?.get()?.invoke(child.wrap())
				Unit
			}.reinterpret()


		val staticFlowBoxForeachFunction: GtkFlowBoxForeachFunc = staticCFunction { _, child, data ->
			data?.asStableRef<FlowBoxForEachFunction>()?.get()?.invoke(child!!.wrap())
			Unit
		}

		val staticFlowBoxFilterFunction: GtkFlowBoxFilterFunc = staticCFunction { child, data ->
			data?.asStableRef<FlowBoxFilterFunction>()?.get()?.invoke(child!!.wrap())?.gtk ?: 0
		}

		val staticFlowBoxSortFunction: GtkFlowBoxSortFunc = staticCFunction { child1, child2, data ->
			data?.asStableRef<FlowBoxSortFunction>()?.get()?.invoke(child1!!.wrap(), child2!!.wrap())!!
		}

		val staticFlowBoxCreateWidgetFunction: GtkFlowBoxCreateWidgetFunc = staticCFunction { item, data ->
			data?.asStableRef<FlowBoxCreateWidgetFunction>()?.get()
				?.invoke(KGObject(item!!.reinterpret()))?.widgetPointer
		}


		inline fun CPointer<GtkFlowBox>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkFlowBox>.wrap() =
			FlowBox(this)
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxForeachFunc">
 *     GtkFlowBoxForeachFunc</a>
 */
typealias FlowBoxForEachFunction = (FlowBox.Child) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxFilterFunc">
 *     GtkFlowBoxFilterFunc</a>
 */
typealias FlowBoxFilterFunction = (FlowBox.Child) -> Boolean

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxSortFunc">
 *     GtkFlowBoxSortFunc</a>
 */
typealias FlowBoxSortFunction = (FlowBox.Child, FlowBox.Child) -> Int

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFlowBox.html#GtkFlowBoxCreateWidgetFunc">
 *     GtkFlowBoxCreateWidgetFunc</a>
 */
typealias FlowBoxCreateWidgetFunction = (KGObject) -> Widget