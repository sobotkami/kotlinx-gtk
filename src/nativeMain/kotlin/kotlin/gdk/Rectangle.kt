package kotlin.gdk

import gtk.GdkRectangle
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.cValue
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 15 / 03 / 2021
 */
class Rectangle internal constructor(
	internal val rectanglePointer: CPointer<GdkRectangle>
) {

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
}
