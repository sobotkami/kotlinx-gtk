package org.gtk.gtk.widgets

import glib.gpointer
import gobject.GCallback
import gtk.GtkDrawingArea
import gtk.GtkDrawingAreaDrawFunc
import gtk.gtk_drawing_area_new
import gtk.gtk_drawing_area_set_draw_func
import kotlinx.cinterop.*
import org.gtk.cairo.Cairo
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gobject.staticDestroyStableRefFunction

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class DrawingArea(
	val drawingAreaPointer: CPointer<GtkDrawingArea>
) : Widget(drawingAreaPointer.reinterpret()) {
	constructor() : this(gtk_drawing_area_new()!!.reinterpret())

	fun setOnDrawFunction(function: DrawingAreaDrawFunction) {
		gtk_drawing_area_set_draw_func(
			drawingAreaPointer,
			staticDrawingAreaDrawFunction,
			StableRef.create(function).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	fun addOnResizeCallback(flags: UInt = 0u, action: DrawingAreaResizeFunction) =
		addSignalCallback(Signals.RESIZE, action, staticDrawingAreaResizeFunction, flags)

	companion object {
		private val staticDrawingAreaDrawFunction: GtkDrawingAreaDrawFunc =
			staticCFunction { area, cr, width, height, data ->
				data?.asStableRef<DrawingAreaDrawFunction>()?.get()?.invoke(Cairo(cr!!), width, height)
				Unit
			}

		private val staticDrawingAreaResizeFunction: GCallback =
			staticCFunction { _: gpointer, width: Int, height: Int, data: gpointer ->
				data.asStableRef<DrawingAreaResizeFunction>().get().invoke(width, height)
				Unit
			}.reinterpret()
	}
}

typealias DrawingAreaDrawFunction = (Cairo, @ParameterName("width") Int, @ParameterName("height") Int) -> Unit

typealias DrawingAreaResizeFunction = (width: Int, height: Int) -> Unit