import cairo.CAIRO_CONTENT_COLOR
import gobject.G_CONNECT_AFTER
import gtk.GDK_BUTTON_PRIMARY
import gtk.GDK_BUTTON_SECONDARY
import org.gtk.cairo.Cairo
import org.gtk.cairo.Surface
import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.frame
import org.gtk.dsl.gtk.x
import org.gtk.gtk.controller.gesture.single.GestureClick
import org.gtk.gtk.controller.gesture.single.GestureDrag
import org.gtk.gtk.widgets.DrawingArea
import org.gtk.gtk.widgets.Widget

/*
<a href="https://docs.gtk.org/gtk4/getting_started.html#custom-drawing">custom-drawing</a>
 */

/** Surface to store current scribbles */
var surface: Surface? = null

fun clearSurface() {
	val cairo = Cairo(surface!!)
	cairo.setSourceRGB(1.0, 1.0, 1.0)
	cairo.paint()
	cairo.destroy()
}

/**
 *  Create a new getSurface of the appropriate size to store our scribbles
 */
fun resizeCB(widget: Widget, width: Int, height: Int) {
	surface?.destroy()
	surface = null

	if (widget.native?.surface != null) {
		surface = widget.native!!.surface.createSimilarSurface(CAIRO_CONTENT_COLOR, widget.width, widget.height)

		/* Initialize the getSurface to white */
		clearSurface()
	}
}

/**
 * Redraw the screen from the getSurface. Note that the draw
 * callback receives a ready-to-be-used cairo_t that is already
 * clipped to only draw the exposed areas of the widget
 */
fun drawCB(cairo: Cairo) {
	cairo.setSourceSurface(surface!!, 0.0, 0.0)
	cairo.paint()
}

/** Draw a rectangle on the getSurface at the given position */
fun drawBrush(widget: Widget, x: Double, y: Double) {
	val cairo = Cairo(surface!!)

	/* Paint to the getSurface, where we store our state */
	cairo.rectangle(x - 3, y - 3, 6.0, 6.0)
	cairo.fill()
	cairo.destroy()

	/* Now invalidate the drawing area. */
	widget.queueDraw()
}

var startX: Double = 0.0
var startY: Double = 0.0

fun dragBegin(area: Widget, x: Double, y: Double) {
	startX = x
	startY = y
	drawBrush(area, x, y)
}

fun dragUpdate(area: Widget, x: Double, y: Double) {
	drawBrush(area, startX + x, startY + y)
}

fun dragEnd(area: Widget, x: Double, y: Double) {
	drawBrush(area, startX + x, startY + y)
}

fun pressed(area: Widget) {
	clearSurface()
	area.queueDraw()
}

fun closeWindow() {
	surface?.destroy()
}

fun main() {
	application("org.gtk.example") {
		onCreateUI {
			lateinit var drawingArea: DrawingArea
			lateinit var drag: GestureDrag
			lateinit var press: GestureClick

			applicationWindow {
				title = "Drawing Area"
				sizeRequest = 300 x 100
				addOnDestroyCallback {
					closeWindow()
				}

				frame {
					drawingArea = DrawingArea()
					drawingArea.sizeRequest = 300 x 100
					child = drawingArea
					drawingArea.setOnDrawFunction { cairo, _, _ ->
						drawCB(cairo)
					}

					drawingArea.addOnResizeCallback(flags = G_CONNECT_AFTER) { width: Int, height: Int ->
						resizeCB(drawingArea, width, height)
					}

					drag = GestureDrag()
					drag.setButton(GDK_BUTTON_PRIMARY.toUInt())
					drawingArea.addController(drag)
					drag.addOnDragBeginCallback { startX, startY ->
						dragBegin(drawingArea, startX, startY)
					}
					drag.addOnDragUpdateCallback { offsetX, offsetY ->
						dragUpdate(drawingArea, offsetX, offsetY)
					}
					drag.addOnDragEndCallback { offsetX, offsetY ->
						dragEnd(drawingArea, offsetX, offsetY)
					}

					press = GestureClick()
					press.setButton(GDK_BUTTON_SECONDARY.toUInt())
					drawingArea.addController(press)

					press.addOnPressedCallback { _, _, _ ->
						pressed(drawingArea)
					}
				}
			}.show()
		}
	}
}