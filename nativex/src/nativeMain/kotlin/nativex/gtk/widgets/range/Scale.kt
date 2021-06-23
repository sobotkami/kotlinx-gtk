package nativex.gtk.widgets.range

import gtk.*
import kotlinx.cinterop.*
import nativex.PointerHolder
import nativex.gtk.Adjustment
import nativex.gtk.Orientable
import nativex.gtk.bool
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PositionType
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Scale internal constructor(
	internal val scalePointer: CPointer<GtkScale>
) : Range(scalePointer.reinterpret()), Orientable {

	override val orientablePointer: PointerHolder<GtkOrientable> by lazy { PointerHolder(scalePointer.reinterpret()) }

	constructor(
		orientation: Orientation,
		adjustment: Adjustment? = null
	) : this(
		gtk_scale_new(orientation.gtk, adjustment?.adjustmentPointer)!!.reinterpret()
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
		get() = gtk_scale_get_draw_value(scalePointer).bool
		set(value) = gtk_scale_set_draw_value(scalePointer, value.gtk)

	var hasOrigin: Boolean
		get() = gtk_scale_get_has_origin(scalePointer).bool
		set(value) = gtk_scale_set_has_origin(scalePointer, value.gtk)

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