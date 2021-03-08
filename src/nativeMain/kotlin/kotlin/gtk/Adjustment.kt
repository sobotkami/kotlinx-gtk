package kotlin.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class Adjustment internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val pointer: CPointer<GtkAdjustment>
) {
	var value: Double
		get() = gtk_adjustment_get_value(pointer)
		set(value) = gtk_adjustment_set_value(pointer, value)

	var pageIncrement: Double
		get() = gtk_adjustment_get_page_increment(pointer)
		set(value) = gtk_adjustment_set_page_increment(pointer, value)

	var pageSize: Double
		get() = gtk_adjustment_get_page_size(pointer)
		set(value) = gtk_adjustment_set_page_size(pointer, value)

	var stepIncrement: Double
		get() = gtk_adjustment_get_step_increment(pointer)
		set(value) = gtk_adjustment_set_step_increment(pointer, value)

	var upper: Double
		get() = gtk_adjustment_get_upper(pointer)
		set(value) = gtk_adjustment_set_upper(pointer, value)

	var lower: Double
		get() = gtk_adjustment_get_lower(pointer)
		set(value) = gtk_adjustment_set_lower(pointer, value)

	val changed: Flow<Int> by lazy {
		MutableStateFlow(0).apply {
			pointer.connectSignal(
				Signals.CHANGED,
				handler = staticCallback,
				callbackWrapper = StableRef.create {
					tryEmit(0)
				}.asCPointer()
			)
		}
	}

	val valueChanged: Flow<Int> by lazy {
		MutableStateFlow(0).apply {
			pointer.connectSignal(
				Signals.VALUE_CHANGED,
				handler = staticCallback,
				callbackWrapper = StableRef.create {
					tryEmit(0)
				}.asCPointer()
			)
		}
	}

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