package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class Adjustment internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val adjustmentPointer: CPointer<GtkAdjustment>
) : KObject(adjustmentPointer.reinterpret()) {
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

	
	@ExperimentalCoroutinesApi
	val changed: Flow<Unit> by signalFlow(Signals.CHANGED)


	
	@ExperimentalCoroutinesApi
	val valueChanged: Flow<Unit> by signalFlow(Signals.VALUE_CHANGED)


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
}