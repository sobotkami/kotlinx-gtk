package org.gtk.gtk.controller.gesture.single

import glib.gpointer
import gobject.GCallback
import gtk.GtkGestureDrag
import gtk.gtk_gesture_drag_get_offset
import gtk.gtk_gesture_drag_get_start_point
import gtk.gtk_gesture_drag_new
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * gtk-kt
 *
 * 26 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.GestureDrag.html">GtkGestureDrag</a>
 */
class GestureDrag(val gestureDragPointer: CPointer<GtkGestureDrag>) :
	GestureSingle(gestureDragPointer.reinterpret()) {

	val offset: Pair<Double, Double>?
		get() = memScoped {
			val x = cValue<DoubleVar>()
			val y = cValue<DoubleVar>()

			if (gtk_gesture_drag_get_offset(gestureDragPointer, x, y).bool)
				x.ptr.pointed.value to y.ptr.pointed.value
			else null
		}
	val startPoint: Pair<Double, Double>?
		get() = memScoped {
			val x = cValue<DoubleVar>()
			val y = cValue<DoubleVar>()

			if (gtk_gesture_drag_get_start_point(gestureDragPointer, x, y).bool)
				x.ptr.pointed.value to y.ptr.pointed.value
			else null
		}

	constructor() : this(gtk_gesture_drag_new()!!.reinterpret())

	fun addOnDragBeginCallback(action: GestureDragStartFunction) =
		addSignalCallback(Signals.DRAG_BEGIN, action, staticGestureStartFunction)

	fun addOnDragEndCallback(action: GestureDragOffsetFunction) =
		addSignalCallback(Signals.DRAG_END, action, staticGestureOffsetFunction)

	fun addOnDragUpdateCallback(action: GestureDragOffsetFunction) =
		addSignalCallback(Signals.DRAG_UPDATE, action, staticGestureOffsetFunction)


	companion object {
		private val staticGestureStartFunction: GCallback =
			staticCFunction { _: gpointer, startX: Double, startY: Double, data: gpointer ->
				data.asStableRef<GestureDragStartFunction>().get().invoke(startX, startY)
			}.reinterpret()

		private val staticGestureOffsetFunction: GCallback =
			staticCFunction { _: gpointer, offsetX: Double, offsetY: Double, data: gpointer ->
				data.asStableRef<GestureDragOffsetFunction>().get().invoke(offsetX, offsetY)
			}.reinterpret()
	}
}

typealias GestureDragStartFunction = (startX: Double, startY: Double) -> Unit
typealias GestureDragOffsetFunction = (offsetX: Double, offsetY: Double) -> Unit
