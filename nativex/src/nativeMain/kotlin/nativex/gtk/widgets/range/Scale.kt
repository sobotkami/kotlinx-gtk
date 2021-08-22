package nativex.gtk.widgets.range

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gtk.Adjustment
import nativex.gtk.Orientable
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PositionType
import nativex.pango.Layout
import nativex.pango.Layout.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 14 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html">GtkScale</a>
 */
class Scale(
	val scalePointer: CPointer<GtkScale>
) : Range(scalePointer.reinterpret()), Orientable {

	override val orientablePointer: CPointer<GtkOrientable> by lazy { scalePointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-new">
	 *     gtk_scale_new</a>
	 */
	constructor(
		orientation: Orientation,
		adjustment: Adjustment? = null
	) : this(
		gtk_scale_new(orientation.gtk, adjustment?.adjustmentPointer)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-new-with-range">
	 *     gtk_scale_new_with_range</a>
	 */
	constructor(
		orientation: Orientation,
		min: Double,
		max: Double,
		step: Double
	) : this(
		gtk_scale_new_with_range(
			orientation.gtk,
			min,
			max,
			step
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-digits">
	 *     gtk_scale_get_digits</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-set-digits">
	 *     gtk_scale_set_digits</a>
	 */
	var digits: Int
		get() = gtk_scale_get_digits(scalePointer)
		set(value) = gtk_scale_set_digits(scalePointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-draw-value">
	 *     gtk_scale_get_draw_value</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-set-draw-value">
	 *     gtk_scale_set_draw_value</a>
	 */
	var drawValue: Boolean
		get() = gtk_scale_get_draw_value(scalePointer).bool
		set(value) = gtk_scale_set_draw_value(scalePointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-has-origin">
	 *     gtk_scale_get_has_origin</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-set-has-origin">
	 *     gtk_scale_set_has_origin</a>
	 */
	var hasOrigin: Boolean
		get() = gtk_scale_get_has_origin(scalePointer).bool
		set(value) = gtk_scale_set_has_origin(scalePointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-value-pos">
	 *     gtk_scale_get_value_pos</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-set-value-pos">
	 *     gtk_scale_set_value_pos</a>
	 */
	var valuePos: PositionType
		get() = PositionType.valueOf(
			gtk_scale_get_value_pos(
				scalePointer
			)
		)!!
		set(value) = gtk_scale_set_value_pos(scalePointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-layout">
	 *     gtk_scale_get_layout</a>
	 */
	val layout: Layout?
		get() = gtk_scale_get_layout(scalePointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-get-layout-offsets">
	 *     gtk_scale_get_layout_offsets</a>
	 */
	fun getLayoutOffsets(): Pair<Int, Int> {
		val x = cValue<IntVar>()
		val y = cValue<IntVar>()
		gtk_scale_get_layout_offsets(scalePointer, x, y)
		return memScoped {
			x.ptr.pointed.value to y.ptr.pointed.value
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-add-mark">gtk_scale_add_mark</a>
	 */
	fun addMark(
		value: Double,
		positionType: PositionType,
		markup: String? = null
	) {
		gtk_scale_add_mark(scalePointer, value, positionType.gtk, markup)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#gtk-scale-clear-marks">
	 *     gtk_scale_clear_marks</a>
	 */
	fun clearMarks() {
		gtk_scale_clear_marks(scalePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#GtkScale-format-value">format-value</a>
	 */
	fun addOnFormatValueCallback(action: ScaleFormatValueFunction): SignalManager =
		addSignalCallback(
			Signals.FORMAT_VALUE,
			StableRef.create(action).asCPointer(),
			staticFormatValueCallback,
			0u
		)

	companion object {
		private val staticFormatValueCallback: GCallback =
			staticCFunction { _: gpointer, value: Double, data: gpointer ->
				memScoped {
					data.asStableRef<ScaleFormatValueFunction>().get().invoke(value).cstr.ptr
				}
			}.reinterpret()
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScale.html#GtkScale-format-value">format-value</a>
 */
typealias ScaleFormatValueFunction = (value: Double) -> String