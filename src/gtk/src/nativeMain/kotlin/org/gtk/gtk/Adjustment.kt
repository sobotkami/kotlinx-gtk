package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class Adjustment(val adjustmentPointer: CPointer<GtkAdjustment>) : KGObject(adjustmentPointer.reinterpret()) {
	var value: Double
		get() = gtk_adjustment_get_value(adjustmentPointer)
		set(value) = gtk_adjustment_set_value(adjustmentPointer, value)

	var pageIncrement: Double
		get() = gtk_adjustment_get_page_increment(adjustmentPointer)
		set(value) = gtk_adjustment_set_page_increment(adjustmentPointer, value)

	var pageSize: Double
		get() = gtk_adjustment_get_page_size(adjustmentPointer)
		set(value) = gtk_adjustment_set_page_size(adjustmentPointer, value)

	var stepIncrement: Double
		get() = gtk_adjustment_get_step_increment(adjustmentPointer)
		set(value) = gtk_adjustment_set_step_increment(adjustmentPointer, value)

	var upper: Double
		get() = gtk_adjustment_get_upper(adjustmentPointer)
		set(value) = gtk_adjustment_set_upper(adjustmentPointer, value)

	var lower: Double
		get() = gtk_adjustment_get_lower(adjustmentPointer)
		set(value) = gtk_adjustment_set_lower(adjustmentPointer, value)

	fun addOnChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.CHANGED, action)

	fun addOnValueChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.VALUE_CHANGED, action)

	constructor(
		value: Double,
		lower: Double,
		upper: Double,
		stepIncrement: Double,
		pageIncrement: Double,
		pageSize: Double
	) : this(
		gtk_adjustment_new(
			value,
			lower,
			upper,
			stepIncrement,
			pageIncrement,
			pageSize
		)!!
	)

	companion object {
		inline fun CPointer<GtkAdjustment>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkAdjustment>.wrap() =
			Adjustment(this)
	}
}