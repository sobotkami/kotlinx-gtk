package org.gtk.gtk.controller.gesture.single

import gtk.GtkGestureSingle
import gtk.gtk_gesture_single_set_button
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.controller.gesture.Gesture

/**
 * gtk-kt
 *
 * 26 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.GestureSingle.html">GtkGestureSingle</a>
 */
open class GestureSingle(val gestureSinglePointer: CPointer<GtkGestureSingle>) :
	Gesture(gestureSinglePointer.reinterpret()) {
	fun setButton(button: UInt) {
		gtk_gesture_single_set_button(gestureSinglePointer, button)
	}

}