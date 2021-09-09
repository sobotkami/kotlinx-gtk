package org.gtk.gtk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import org.gtk.gdk.RGBA
import org.gtk.gdk.RGBA.Companion.heavyWrap
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
interface ColorChooser {

	val colorChooserPointer: CPointer<GtkColorChooser>

	var rGBA: RGBA
		get() = ::gtk_color_chooser_get_rgba.heavyWrap(colorChooserPointer)
		set(value) {
			gtk_color_chooser_set_rgba(
				colorChooserPointer,
				value.rgbaPointer
			)
		}

	var useAlpha: Boolean
		get() = gtk_color_chooser_get_use_alpha(colorChooserPointer).bool
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
		KGObject(colorChooserPointer.reinterpret()).addSignalCallback(
			Signals.COLOR_ACTIVATED,
			action,
			staticColorActivatedFunction
		)

	companion object {
		private val staticColorActivatedFunction: GCallback =
			staticCFunction { _: gpointer?, rgba: CPointer<GdkRGBA>, data: gpointer? ->
				data?.asStableRef<(RGBA) -> Unit>()?.get()?.invoke(RGBA(rgba))
				Unit
			}.reinterpret()
	}
}