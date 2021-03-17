package kotlin.gtk.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.Adjustment
import kotlin.gtk.IconSize
import kotlin.gtk.toNullTermCStringArray
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class ScaleButton internal constructor(
	internal val scaleButtonPointer: CPointer<GtkScaleButton>
) : Button(scaleButtonPointer.reinterpret()) {

	constructor(
		iconSize: IconSize,
		min: Double = 0.0,
		max: Double = 100.0,
		step: Double = 2.0,
		icons: List<String>? = null
	) : this(
		gtk_scale_button_new(
			iconSize.gtk,
			min,
			max,
			step,
			icons?.toNullTermCStringArray()
		)!!.reinterpret()
	)

	var adjustment: Adjustment?
		get() = gtk_scale_button_get_adjustment(scaleButtonPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scale_button_set_adjustment(
			scaleButtonPointer,
			value?.pointer
		)

	fun setIcons(icons: List<String>) {
		gtk_scale_button_set_icons(
			scaleButtonPointer,
			icons.toNullTermCStringArray()
		)
	}

	var value: Double
		get() = gtk_scale_button_get_value(scaleButtonPointer)
		set(value) = gtk_scale_button_set_value(scaleButtonPointer, value)

	val popup: Widget
		get() = Widget(gtk_scale_button_get_popup(scaleButtonPointer)!!)

	val plusButton: Widget
		get() = Widget(gtk_scale_button_get_plus_button(scaleButtonPointer)!!)

	val minusButton: Widget
		get() = Widget(gtk_scale_button_get_minus_button(scaleButtonPointer)!!)

}