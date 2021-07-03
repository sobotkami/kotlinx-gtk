package nativex.gdk

import gtk.GdkRectangle
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import nativex.gtk.Tooltip

/**
 * kotlinx-gtk
 * 15 / 03 / 2021
 */
class Rectangle(val rectanglePointer: CPointer<GdkRectangle>) {

	var x: Int
		get() = rectanglePointer.pointed.x
		set(value) {
			rectanglePointer.pointed.x = value
		}

	var y: Int
		get() = rectanglePointer.pointed.y
		set(value) {
			rectanglePointer.pointed.y = value
		}

	var width: Int
		get() = rectanglePointer.pointed.width
		set(value) {
			rectanglePointer.pointed.width = value
		}

	var height: Int
		get() = rectanglePointer.pointed.height
		set(value) {
			rectanglePointer.pointed.height = value
		}

	companion object{
		inline fun CPointer<GdkRectangle>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkRectangle>.wrap() =
			Rectangle(this)
	}
}
