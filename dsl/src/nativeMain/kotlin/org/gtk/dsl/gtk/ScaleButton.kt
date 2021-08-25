package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.button.scalable.ScaleButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Box.scaleButton(
	min: Double = 0.0,
	max: Double = 100.0,
	step: Double = 2.0,
	icons: List<String>? = null,
	buttonBuilder: ScaleButton.() -> Unit = {}
): ScaleButton =
	ScaleButton(min, max, step, icons)
		.apply(buttonBuilder)
		.also { append(it) }