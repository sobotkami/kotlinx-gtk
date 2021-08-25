package org.gtk.gtk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.gdk.RGBA
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.signalManager
import org.gtk.gtk.common.enums.Orientation

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