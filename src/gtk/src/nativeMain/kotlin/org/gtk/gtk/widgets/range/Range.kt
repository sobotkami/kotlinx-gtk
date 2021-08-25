package org.gtk.gtk.widgets.range

import gtk.*
import gtk.GtkSensitivityType.*
import kotlinx.cinterop.*
import org.gtk.gdk.Rectangle
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gtk.Adjustment
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
open class Range(
	val rangePointer: CPointer<GtkRange>
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
		val gtk: GtkSensitivityType
	) {
		AUTO(0, GTK_SENSITIVITY_AUTO),
		ON(1, GTK_SENSITIVITY_ON),
		OFF(2, GTK_SENSITIVITY_OFF);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkSensitivityType) =
				values().find { it.gtk == gtk }
		}
	}
}