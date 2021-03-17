package kotlin.gdk

import gtk.GdkRGBA
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class RGBA internal constructor(
	internal val rgbaPointer: CPointer<GdkRGBA>
) {
	var red: Double
		get() = rgbaPointer.pointed.red
		set(value) {
			rgbaPointer.pointed.red = value
		}

	var green: Double
		get() = rgbaPointer.pointed.green
		set(value) {
			rgbaPointer.pointed.green = value
		}

	var blue: Double
		get() = rgbaPointer.pointed.blue
		set(value) {
			rgbaPointer.pointed.blue = value
		}

	var alpha: Double
		get() = rgbaPointer.pointed.alpha
		set(value) {
			rgbaPointer.pointed.alpha = value
		}
}