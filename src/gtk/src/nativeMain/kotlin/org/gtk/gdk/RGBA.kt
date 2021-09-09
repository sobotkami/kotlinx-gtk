package org.gtk.gdk

import gtk.GdkRGBA
import kotlinx.cinterop.*

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

	companion object {
		/**
		 * Using reflection, wraps the specific function that is reused often
		 */
		inline fun <T : CPointed> ((CValuesRef<T>?, CValuesRef<GdkRGBA>?) -> Unit).heavyWrap(pointer: CValuesRef<T>?): RGBA =
			memScoped {
				val color = cValue<GdkRGBA>()
				this@heavyWrap(pointer, color)
				color.ptr.wrap()
			}

		inline fun CPointer<GdkRGBA>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkRGBA>.wrap() =
			RGBA(this)
	}
}