package kotlin.gtk

import gtk.*
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.flow.Flow
import kotlin.gdk.RGBA
import kotlin.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
interface ColorChooser {
	val colorChooserPointer: PointerHolder<GtkColorChooser>

	var rGBA: RGBA
		get() = memScoped {
			val v = cValue<GdkRGBA>()
			gtk_color_chooser_get_rgba(colorChooserPointer.ptr, v)
			RGBA(v.ptr)
		}
		set(value) {
			gtk_color_chooser_set_rgba(
				colorChooserPointer.ptr,
				value.rgbaPointer
			)
		}

	var useAlpha: Boolean
		get() = Boolean.from(
			gtk_color_chooser_get_use_alpha(colorChooserPointer.ptr)
		)
		set(value) = gtk_color_chooser_set_use_alpha(
			colorChooserPointer.ptr,
			value.gtkValue
		)


	fun addPalette(
		orientation: Orientation,
		colorsPerLine: Int,
		totalColors: Int,
		colors: RGBA
	) {
		gtk_color_chooser_add_palette(
			colorChooserPointer.ptr,
			orientation.gtk,
			colorsPerLine,
			totalColors,
			colors.rgbaPointer
		)
	}

	val colorActivated: Flow<RGBA>
		get() = TODO("color-activated")
}