package org.gtk.gdk

import gtk.GdkRGBA
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class RGBA(
	 val rgbaPointer: CPointer<GdkRGBA>
) {
	var red: Float
		get() = rgbaPointer.pointed.red
		set(value) {
			rgbaPointer.pointed.red = value
		}

	var green: Float
		get() = rgbaPointer.pointed.green
		set(value) {
			rgbaPointer.pointed.green = value
		}

	var blue: Float
		get() = rgbaPointer.pointed.blue
		set(value) {
			rgbaPointer.pointed.blue = value
		}

	var alpha: Float
		get() = rgbaPointer.pointed.alpha
		set(value) {
			rgbaPointer.pointed.alpha = value
		}

	override fun toString(): String = "RGBA(red=${red}, green=${green}, blue=${blue}, alpha=${alpha})"

}