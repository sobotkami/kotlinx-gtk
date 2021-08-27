package org.gtk.gtk.controller.gesture

import gtk.GtkGesture
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.controller.EventController

/**
 * gtk-kt
 *
 * 26 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.Gesture.html">GtkGesture</a>
 */
open class Gesture(val gesturePointer: CPointer<GtkGesture>) : EventController(gesturePointer.reinterpret()) {

}