package nativex.gtk.widgets.container.bin.combobox

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gdk.Device
import nativex.gtk.CellArea
import nativex.gtk.TreeModel
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.container.TreeViewRowSeparatorFunc
import nativex.gtk.widgets.container.bin.Bin
import nativex.gtk.widgets.range.Range

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ComboBox(
	internal val comboBoxPointer: CPointer<GtkComboBox>
) : Bin(comboBoxPointer.reinterpret()) {
	constructor(
		withEntry: Boolean = false
	) : this((if (withEntry) gtk_combo_box_new_with_entry() else gtk_combo_box_new())!!.reinterpret())

	constructor(
		model: TreeModel,
		withEntry: Boolean = false
	) : this(
		(if (withEntry)
			gtk_combo_box_new_with_model_and_entry(model.pointer)
		else gtk_combo_box_new_with_model(model.pointer))!!.reinterpret()
	)

	constructor(
		area: CellArea,
		withEntry: Boolean = false
	) : this(
		(if (withEntry)
			gtk_combo_box_new_with_area_and_entry(area.pointer)
		else gtk_combo_box_new_with_area(area.pointer))!!.reinterpret()
	)

	var wrapWidth: Int
		get() = gtk_combo_box_get_wrap_width(comboBoxPointer)
		set(value) = gtk_combo_box_set_wrap_width(comboBoxPointer, value)

	var rowSpanColumn: Int
		get() = gtk_combo_box_get_row_span_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_wrap_width(comboBoxPointer, value)

	var columnSpanColumn: Int
		get() = gtk_combo_box_get_column_span_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_column_span_column(
			comboBoxPointer,
			value
		)

	var active: Int
		get() = gtk_combo_box_get_active(comboBoxPointer)
		set(value) = gtk_combo_box_set_active(comboBoxPointer, value)

	fun getActiveIter(treeIter: TreeModel.Iter) = gtk_combo_box_get_active_iter(
		comboBoxPointer,
		treeIter.iter
	)
		.bool

	fun setActiveIter(iter: TreeModel.Iter) {
		gtk_combo_box_set_active_iter(comboBoxPointer, iter.iter)
	}

	var idColumn: Int
		get() = gtk_combo_box_get_id_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_id_column(comboBoxPointer, value)


	fun getActiveId(): String? =
		gtk_combo_box_get_active_id(comboBoxPointer)?.toKString()

	fun setActiveId(activeID: String) =
		gtk_combo_box_set_active_id(comboBoxPointer, activeID)
			.bool

	var model: TreeModel?
		get() = gtk_combo_box_get_model(comboBoxPointer)?.let { TreeModel(it) }
		set(value) = gtk_combo_box_set_model(comboBoxPointer, value?.pointer)


	fun popupForDevice(device: Device) {
		gtk_combo_box_popup_for_device(comboBoxPointer, device.pointer)
	}

	fun popup() {
		gtk_combo_box_popup(comboBoxPointer)
	}

	fun popdown() {
		gtk_combo_box_popdown(comboBoxPointer)
	}

	fun getPopupAccessible() {
		TODO("gtk_combo_box_get_popup_accessible")
	}

	var rowSeperatorFunc: TreeViewRowSeparatorFunc
		get() {
			TODO("gtk_combo_box_get_row_separator_func")
		}
		set(value) {
			TODO("gtk_combo_box_set_row_separator_func")
		}

	var buttonSensitivity: Range.SensitivityType
		get() = Range.SensitivityType.valueOf(
			gtk_combo_box_get_button_sensitivity(comboBoxPointer)
		)!!
		set(value) = gtk_combo_box_set_button_sensitivity(
			comboBoxPointer,
			value.gtk
		)

	val hasEntry
		get() = gtk_combo_box_get_has_entry(
			comboBoxPointer
		)
			.bool

	var entryTextColumn: Int
		get() = gtk_combo_box_get_entry_text_column(comboBoxPointer)
		set(value) = gtk_combo_box_set_entry_text_column(comboBoxPointer, value)

	var popupFixedWidth: Boolean
		get() = gtk_combo_box_get_popup_fixed_width(comboBoxPointer).bool
		set(value) = gtk_combo_box_set_popup_fixed_width(
			comboBoxPointer,
			value.gtk
		)


}