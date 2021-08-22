package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.IconSize
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.button.scalable.ScaleButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Widget.scaleButton(
	iconSize: IconSize,
	min: Double = 0.0,
	max: Double = 100.0,
	step: Double = 2.0,
	icons: List<String>? = null,
	buttonBuilder: ScaleButton.() -> Unit = {}
): ScaleButton =
	ScaleButton(iconSize, min, max, step, icons)
		.apply(buttonBuilder)
		.also { add(it) }