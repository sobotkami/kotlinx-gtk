package org.gtk.gtk.controller.gesture.single

import glib.gpointer
import gobject.GCallback
import gtk.GdkEventSequence
import gtk.GtkGestureClick
import gtk.gtk_gesture_click_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import org.gtk.gdk.EventSequence
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * gtk-kt
 *
 * 26 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.GestureClick.html"></a>
 */
class GestureClick(val gestureClickPointer: CPointer<GtkGestureClick>) :
	GestureSingle(gestureClickPointer.reinterpret()) {

	constructor() : this(gtk_gesture_click_new()!!.reinterpret())

	fun addOnPressedCallback(action: GestureClickForceFunction) =
		addSignalCallback(Signals.PRESSED, action, staticGestureForceFunction)

	fun addOnReleasedCallback(action: GestureClickForceFunction) =
		addSignalCallback(Signals.RELEASED, action, staticGestureForceFunction)

	fun addOnStoppedCallback(action: () -> Unit) =
		addSignalCallback(Signals.STOPPED, action)

	fun addOnUnpairedReleaseCallback(action: GestureClickUnpairedReleaseFunction) =
		addSignalCallback(Signals.UNPAIRED_RELEASE, action, staticGestureClickUnpairedReleaseFunction)

	companion object {
		private val staticGestureForceFunction: GCallback =
			staticCFunction { _: gpointer, nPress: Int, x: Double, y: Double, data: gpointer ->
				data.asStableRef<GestureClickForceFunction>().get().invoke(nPress, x, y)
				Unit
			}.reinterpret()

		private val staticGestureClickUnpairedReleaseFunction: GCallback =
			staticCFunction { _: gpointer, x: Double, y: Double, button: UInt, sequence: CPointer<GdkEventSequence>, data: gpointer ->
				data.asStableRef<GestureClickUnpairedReleaseFunction>().get().invoke(
					x,
					y,
					button,
					EventSequence(sequence)
				)
				Unit
			}.reinterpret()
	}
}

typealias GestureClickForceFunction = (
	@ParameterName("nPress") Int,
	@ParameterName("x") Double,
	@ParameterName("y") Double
) -> Unit

typealias GestureClickUnpairedReleaseFunction = (
	@ParameterName("x") Double,
	@ParameterName("y") Double,
	@ParameterName("button") UInt,
	@ParameterName("sequence") EventSequence
) -> Unit