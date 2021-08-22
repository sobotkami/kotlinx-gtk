package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import nativex.gobject.KGObject
import nativex.gtk.LayoutChild.Companion.wrap
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.SizeRequestMode
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Widget.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 01 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.LayoutManager.html">GtkLayoutManager</a>
 */
class LayoutManager(val layoutManagerPointer: CPointer<GtkLayoutManager>) :
	KGObject(layoutManagerPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.allocate.html">
	 *     gtk_layout_manager_allocate</>
	 */
	fun allocate(widget: Widget, width: Int, height: Int, baseline: Int) {
		gtk_layout_manager_allocate(layoutManagerPointer, widget.widgetPointer, width, height, baseline)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.get_layout_child.html">
	 *     gtk_layout_manager_get_layout_child</>
	 */
	fun getLayoutChild(child: Widget): LayoutChild =
		gtk_layout_manager_get_layout_child(layoutManagerPointer, child.widgetPointer)!!.wrap()


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.get_widget.html">
	 *     gtk_layout_manager_get_request_mode</>
	 */
	val requestMode: SizeRequestMode
		get() = SizeRequestMode.valueOf(gtk_layout_manager_get_request_mode(layoutManagerPointer))!!

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.get_widget.html">
	 *     gtk_layout_manager_get_widget</>
	 */
	val widget: Widget?
		get() = gtk_layout_manager_get_widget(layoutManagerPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.layout_changed.html">
	 *     gtk_layout_manager_layout_changed</>
	 */
	fun layoutChanged() {
		gtk_layout_manager_layout_changed(layoutManagerPointer)
	}

	/**
	 * Data class that holds results from [measure]
	 * @see measure
	 */
	data class MeasureResult(
		val minimum: Int,
		val natural: Int,
		val minimumBaseline: Int,
		val naturalBaseline: Int
	)

	/**
	 * @return [MeasureResult] The values returned from function
	 *
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutManager.measure.html"></>
	 */
	fun measure(
		widget: Widget,
		orientation: Orientation,
		forSize: Int,
	): MeasureResult =
		memScoped {
			val minimum = cValue<IntVar>()
			val natural = cValue<IntVar>()
			val minimumBaseline = cValue<IntVar>()
			val naturalBaseline = cValue<IntVar>()

			gtk_layout_manager_measure(
				layoutManagerPointer,
				widget.widgetPointer,
				orientation.gtk,
				forSize,
				minimum,
				natural,
				minimumBaseline,
				naturalBaseline
			)

			MeasureResult(
				minimum.ptr.pointed.value,
				natural.ptr.pointed.value,
				minimumBaseline.ptr.pointed.value,
				naturalBaseline.ptr.pointed.value,
			)
		}

	companion object {
		inline fun CPointer<GtkLayoutManager>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkLayoutManager>.wrap() =
			LayoutManager(this)

	}
}