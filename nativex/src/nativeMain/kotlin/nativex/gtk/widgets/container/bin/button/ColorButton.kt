package nativex.gtk.widgets.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.gdk.RGBA
import nativex.gobject.Signals
import nativex.gtk.ColorChooser

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class ColorButton(
	 val colorButtonPointer: CPointer<GtkColorButton>
) : Button(colorButtonPointer.reinterpret()), ColorChooser {

	override val colorChooserPointer: CPointer<GtkColorChooser> by lazy {
		colorButtonPointer.reinterpret()
	}

	@ExperimentalCoroutinesApi

	override val colorActivated: Flow<RGBA> by signalFlow(
		Signals.COLOR_ACTIVATED,
		ColorChooser.staticColorActivatedCallback
	)

	var title: String?
		get() = gtk_color_button_get_title(colorButtonPointer)?.toKString()
		set(value) = gtk_color_button_set_title(colorButtonPointer, value)

	constructor() : this(gtk_color_button_new()!!.reinterpret())
	constructor(rgba: RGBA) : this(gtk_color_button_new_with_rgba(rgba.rgbaPointer)!!.reinterpret())
}