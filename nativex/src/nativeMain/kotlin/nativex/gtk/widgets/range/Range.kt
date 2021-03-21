package nativex.gtk.widgets.range

import gtk.*
import gtk.GtkSensitivityType.*
import kotlinx.cinterop.*
import nativex.gdk.Rectangle
import nativex.gtk.Adjustment
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
open class Range internal constructor(
	internal val rangePointer: CPointer<GtkRange>
) : Widget(rangePointer.reinterpret()) {
	var fillLevel: Double
		get() = gtk_range_get_fill_level(rangePointer)
		set(value) = gtk_range_set_fill_level(rangePointer, value)

	var restrictToFillLevel: Boolean
		get() = gtk_range_get_restrict_to_fill_level(rangePointer).bool
		set(value) = gtk_range_set_restrict_to_fill_level(
			rangePointer,
			value.gtk
		)

	var showFillLevel: Boolean
		get() = gtk_range_get_show_fill_level(rangePointer).bool
		set(value) = gtk_range_set_show_fill_level(
			rangePointer,
			value.gtk
		)

	var adjustment: Adjustment
		get() = Adjustment(gtk_range_get_adjustment(rangePointer)!!)
		set(value) = gtk_range_set_adjustment(rangePointer, value.adjustmentPointer)

	var isInverted: Boolean
		get() = gtk_range_get_inverted(rangePointer).bool
		set(value) = gtk_range_set_inverted(
			rangePointer,
			value.gtk
		)

	var value: Double
		get() = gtk_range_get_value(rangePointer)
		set(value) = gtk_range_set_value(rangePointer, value)

	fun setIncrements(step: Double, page: Double) {
		gtk_range_set_increments(rangePointer, step, page)
	}

	fun setRange(min: Double, max: Double) {
		gtk_range_set_range(rangePointer, min, max)
	}

	var roundDigits: Int
		get() = gtk_range_get_round_digits(rangePointer)
		set(value) = gtk_range_set_round_digits(rangePointer, value)

	var lowerStepperSensitivity: SensitivityType
		get() = SensitivityType.valueOf(
			gtk_range_get_lower_stepper_sensitivity(rangePointer)
		)!!
		set(value) = gtk_range_set_lower_stepper_sensitivity(
			rangePointer,
			value.gtk
		)

	var upperStepperSensitivity: SensitivityType
		get() = SensitivityType.valueOf(
			gtk_range_get_upper_stepper_sensitivity(rangePointer)
		)!!
		set(value) = gtk_range_set_upper_stepper_sensitivity(
			rangePointer,
			value.gtk
		)
	var isFlippable: Boolean
		get() = gtk_range_get_flippable(rangePointer).bool
		set(value) = gtk_range_set_flippable(
			rangePointer,
			value.gtk
		)

	val rangeRect: Rectangle
		get() {
			val rectanglePointer = cValue<GdkRectangle>()
			gtk_range_get_range_rect(rangePointer, rectanglePointer)
			return memScoped {
				Rectangle(rectanglePointer.ptr)
			}
		}

	val sliderRange: IntRange
		get() {
			val start = cValue<IntVar>()
			val end = cValue<IntVar>()
			gtk_range_get_slider_range(rangePointer, start, end)
			return memScoped {
				IntRange(start.ptr.pointed.value, end.ptr.pointed.value)
			}
		}

	var sliderSizeFixed: Boolean
		get() = gtk_range_get_slider_size_fixed(rangePointer).bool
		set(value) = gtk_range_set_slider_size_fixed(
			rangePointer,
			value.gtk
		)

	enum class SensitivityType(
		val key: Int,
		internal val gtk: GtkSensitivityType
	) {
		AUTO(0, GTK_SENSITIVITY_AUTO),
		ON(1, GTK_SENSITIVITY_ON),
		OFF(2, GTK_SENSITIVITY_OFF);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkSensitivityType) =
				values().find { it.gtk == gtk }
		}
	}
}