package nativex.gtk.widgets.combobox

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.async.popdownSignalManager
import nativex.async.popupSignalManager
import nativex.gdk.Device
import nativex.glib.CString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gobject.staticDestroyStableRefFunction
import nativex.gtk.CellLayout
import nativex.gtk.TreeModel
import nativex.gtk.common.enums.ScrollType
import nativex.gtk.widgets.TreeView.Companion.staticTreeViewRowSeparatorFunc
import nativex.gtk.widgets.TreeViewRowSeparatorFunc
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.range.Range

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html">GtkComboBox</a>
 */
open class ComboBox(
	val comboBoxPointer: CPointer<GtkComboBox>
) : Widget(comboBoxPointer.reinterpret()), CellLayout {
	override val cellLayoutHolder: CPointer<GtkCellLayout> by lazy { comboBoxPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-new">
	 *     gtk_combo_box_new</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-new-with-entry">
	 *     gtk_combo_box_new_with_entry</a>
	 */
	constructor(
		withEntry: Boolean = false
	) : this((if (withEntry) gtk_combo_box_new_with_entry() else gtk_combo_box_new())!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-new-with-model">
	 *     gtk_combo_box_new_with_model</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-new-with-model-and-entry">
	 *     gtk_combo_box_new_with_model_and_entry</a>
	 */
	constructor(
		model: TreeModel,
		withEntry: Boolean = false
	) : this(
		(if (withEntry)
			gtk_combo_box_new_with_model_and_entry(model.treeModelPointer)
		else gtk_combo_box_new_with_model(model.treeModelPointer))!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-active">
	 *     gtk_combo_box_get_active</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-active">
	 *     gtk_combo_box_set_active</a>
	 */
	var active: Int
		get() = gtk_combo_box_get_active(comboBoxPointer)
		set(value) = gtk_combo_box_set_active(comboBoxPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-active-iter"></a>
	 */
	fun getActiveIter(treeIter: TreeModel.TreeIter): Boolean =
		gtk_combo_box_get_active_iter(comboBoxPointer, treeIter.treeIterPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-active-iter">
	 *     gtk_combo_box_set_active_iter</a>
	 */
	fun setActiveIter(iter: TreeModel.TreeIter) {
		gtk_combo_box_set_active_iter(comboBoxPointer, iter.treeIterPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-id-column">
	 *     gtk_combo_box_get_id_column</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-id-column">
	 *     gtk_combo_box_set_id_column</a>
	 */
	var idColumn: Int
		get() = gtk_combo_box_get_id_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_id_column(comboBoxPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-active-id">
	 *     gtk_combo_box_get_active_id</a>
	 */
	val activeId: String?
		get() = gtk_combo_box_get_active_id(comboBoxPointer)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-active-id">
	 *     gtk_combo_box_set_active_id</a>
	 */
	fun setActiveId(activeID: String): Boolean =
		gtk_combo_box_set_active_id(comboBoxPointer, activeID).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-model">
	 *     gtk_combo_box_get_model</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-model">
	 *     gtk_combo_box_set_model</a>
	 */
	var model: TreeModel?
		get() = gtk_combo_box_get_model(comboBoxPointer)?.let { TreeModel(it) }
		set(value) = gtk_combo_box_set_model(comboBoxPointer, value?.treeModelPointer)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-popup-for-device">
	 *     gtk_combo_box_popup_for_device</a>
	 */
	fun popupForDevice(device: Device) {
		gtk_combo_box_popup_for_device(comboBoxPointer, device.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-popup">
	 *     gtk_combo_box_popup</a>
	 */
	fun popup() {
		gtk_combo_box_popup(comboBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-popdown">
	 *     gtk_combo_box_popdown</a>
	 */
	fun popdown() {
		gtk_combo_box_popdown(comboBoxPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-button-sensitivity">
	 *     gtk_combo_box_get_button_sensitivity</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-button-sensitivity">
	 *     gtk_combo_box_set_button_sensitivity</a>
	 */
	var buttonSensitivity: Range.SensitivityType
		get() = Range.SensitivityType.valueOf(gtk_combo_box_get_button_sensitivity(comboBoxPointer))!!
		set(value) = gtk_combo_box_set_button_sensitivity(comboBoxPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-has-entry">
	 *     gtk_combo_box_get_has_entry</a>
	 */
	val hasEntry
		get() = gtk_combo_box_get_has_entry(comboBoxPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-entry-text-column">
	 *     gtk_combo_box_get_entry_text_column</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-entry-text-column">
	 *     gtk_combo_box_set_entry_text_column</a>
	 */
	var entryTextColumn: Int
		get() = gtk_combo_box_get_entry_text_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_entry_text_column(comboBoxPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-popup-fixed-width">
	 *     gtk_combo_box_get_popup_fixed_width</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-popup-fixed-width">
	 *     gtk_combo_box_set_popup_fixed_width</a>
	 */
	var popupFixedWidth: Boolean
		get() = gtk_combo_box_get_popup_fixed_width(comboBoxPointer).bool
		set(value) = gtk_combo_box_set_popup_fixed_width(
			comboBoxPointer,
			value.gtk
		)


	/**
	 * No point to getting the row separator function. Store it yourself.
	 *
	 * @param function Separator function, set as null to remove
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-set-row-separator-func">
	 *     gtk_combo_box_set_row_separator_func</a>
	 */
	// @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#gtk-combo-box-get-row-separator-func"></a>
	fun setRowSeparatorFunc(function: TreeViewRowSeparatorFunc?) {
		gtk_combo_box_set_row_separator_func(
			comboBoxPointer,
			staticTreeViewRowSeparatorFunc,
			if (function != null) StableRef.create(function).asCPointer() else null,
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-changed"></a>
	 */
	fun addOnChangedCallback(action: () -> Unit): SignalManager =
		SignalManager(
			comboBoxPointer,
			comboBoxPointer.connectSignal(
				Signals.CHANGED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-format-entry-text">
	 *     format-entry-text</a>
	 */
	fun addOnFormatEntryTextCallback(action: ComboBoxFormatEntryTextFunction): SignalManager =
		SignalManager(
			comboBoxPointer,
			comboBoxPointer.connectSignal(
				Signals.FORMAT_ENTRY_TEXT,
				callbackWrapper = StableRef.create(action).asCPointer(),
				staticFormatEntryFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-move-active">
	 *     move-active</a>
	 */
	fun addOnMoveActiveCallback(action: ComboBoxMoveActiveFunction): SignalManager =
		SignalManager(
			comboBoxPointer,
			comboBoxPointer.connectSignal(
				Signals.MOVE_ACTIVE,
				callbackWrapper = StableRef.create(action).asCPointer(),
				staticMoveActiveFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-popdown">
	 *     popdown</a>
	 */
	fun addOnPopdownCallback(action: () -> Unit): SignalManager =
		popdownSignalManager(comboBoxPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-popup">
	 *     popup</a>
	 */
	fun addOnPopupCallback(action: () -> Unit): SignalManager =
		popupSignalManager(comboBoxPointer, action)

	companion object {
		private val staticFormatEntryFunction: GCallback =
			staticCFunction { _: gpointer, path: CString, data: gpointer ->
				memScoped {
					data.asStableRef<ComboBoxFormatEntryTextFunction>().get().invoke(path.toKString()).cstr.ptr
				}
			}.reinterpret()

		private val staticMoveActiveFunction: GCallback =
			staticCFunction { _: gpointer, type: GtkScrollType, data: gpointer ->
				data.asStableRef<ComboBoxMoveActiveFunction>().get().invoke(ScrollType.valueOf(type)!!)
				Unit
			}.reinterpret()
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-move-active">
 *     move-active</a>
 */
typealias ComboBoxMoveActiveFunction = (ScrollType) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBox.html#GtkComboBox-format-entry-text">
 *     format-entry-text</a>
 */
typealias ComboBoxFormatEntryTextFunction = (@ParameterName("path") String) -> String