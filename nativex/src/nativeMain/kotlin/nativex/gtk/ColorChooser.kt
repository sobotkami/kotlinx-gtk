package nativex.gtk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.gdk.RGBA
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.signalManager
import nativex.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
interface ColorChooser {
	val colorChooserPointer: CPointer<GtkColorChooser>

	var rGBA: RGBA
		get() = memScoped {
			val v = cValue<GdkRGBA>()
			gtk_color_chooser_get_rgba(colorChooserPointer, v)
			RGBA(v.ptr)
		}
		set(value) {
			gtk_color_chooser_set_rgba(
				colorChooserPointer,
				value.rgbaPointer
			)
		}

	var useAlpha: Boolean
		get() = gtk_color_chooser_get_use_alpha(colorChooserPointer)
			.bool
		set(value) = gtk_color_chooser_set_use_alpha(
			colorChooserPointer,
			value.gtk
		)


	fun addPalette(
		orientation: Orientation,
		colorsPerLine: Int,
		totalColors: Int,
		colors: RGBA
	) {
		gtk_color_chooser_add_palette(
			colorChooserPointer,
			orientation.gtk,
			colorsPerLine,
			totalColors,
			colors.rgbaPointer
		)
	}

	fun addOnColorActivatedCallback(action: (RGBA) -> Unit): SignalManager =
		signalManager(
			colorChooserPointer,
			Signals.COLOR_ACTIVATED,
			StableRef.create(action).asCPointer(),
			staticColorActivatedFunction,
			0u
		)

	companion object {
		private val staticColorActivatedFunction: GCallback =
			staticCFunction { _: gpointer?, rgba: CPointer<GdkRGBA>, data: gpointer? ->
				data?.asStableRef<(RGBA) -> Unit>()?.get()?.invoke(RGBA(rgba))
				Unit
			}.reinterpret()
	}
}