package kotlin.gtk.widgets.range

import gtk.*
import kotlinx.cinterop.*
import kotlin.gtk.Adjustment
import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.common.enums.PositionType
import kotlin.gtk.from
import kotlin.gtk.gtkValue

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Scale internal constructor(
	internal val scalePointer: CPointer<GtkScale>
) : Range(scalePointer.reinterpret()) {

	constructor(
		orientation: Orientation,
		adjustment: Adjustment? = null
	) : this(
		gtk_scale_new(orientation.gtk, adjustment?.pointer)!!.reinterpret()
	)

	constructor(
		orientation: Orientation,
		min: Double,
		max: Double,
		step: Double
	) : this(
		gtk_scale_new_with_range(
			orientation.gtk,
			min,
			max,
			step
		)!!.reinterpret()
	)

	var digits: Int
		get() = gtk_scale_get_digits(scalePointer)
		set(value) = gtk_scale_set_digits(scalePointer, value)

	var drawValue: Boolean
		get() = Boolean.from(gtk_scale_get_draw_value(scalePointer))
		set(value) = gtk_scale_set_draw_value(scalePointer, value.gtkValue)

	var hasOrigin: Boolean
		get() = Boolean.from(gtk_scale_get_has_origin(scalePointer))
		set(value) = gtk_scale_set_has_origin(scalePointer, value.gtkValue)

	var valuePos: PositionType
		get() = PositionType.valueOf(
			gtk_scale_get_value_pos(
				scalePointer
			)
		)!!
		set(value) = gtk_scale_set_value_pos(scalePointer, value.gtk)

	fun getLayout() {
		TODO("gtk_scale_get_layout")
	}

	fun getLayoutOffsets(): Pair<Int, Int> {
		val x = cValue<IntVar>()
		val y = cValue<IntVar>()
		gtk_scale_get_layout_offsets(scalePointer, x, y)
		return memScoped {
			x.ptr.pointed.value to y.ptr.pointed.value
		}
	}

	fun addMark(
		value: Double,
		positionType: PositionType,
		markup: String? = null
	) {
		gtk_scale_add_mark(scalePointer, value, positionType.gtk, markup)
	}

	fun clearMarks() {
		gtk_scale_clear_marks(scalePointer)
	}

}