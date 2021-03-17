package kotlin.gtk.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlin.async.callbackSignalFlow
import kotlin.gtk.IconSize
import kotlin.gtk.Signals.CLICKED
import kotlin.gtk.common.enums.PositionType
import kotlin.gtk.common.enums.ReliefType
import kotlin.gtk.container.bin.Bin
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
open class Button internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val buttonPointer: CPointer<GtkButton>
) : Bin(buttonPointer.reinterpret()) {

	constructor() : this(
		gtk_button_new()!!.reinterpret()
	)

	constructor(label: String, mnemonic: Boolean = false) : this(
		(if (mnemonic)
			gtk_button_new_with_mnemonic(
				label
			)
		else gtk_button_new_with_label(
			label
		))!!.reinterpret()
	)

	constructor(
		icon: String,
		size: IconSize
	) : this(gtk_button_new_from_icon_name(icon, size.gtk)!!.reinterpret())

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val clickedSignal: Flow<Unit> by lazy {
		callbackSignalFlow(CLICKED)
	}

	fun clicked() {
		gtk_button_clicked(buttonPointer)
	}

	var relief: ReliefType
		get() = ReliefType.valueOf(gtk_button_get_relief(buttonPointer))!!
		set(value) = gtk_button_set_relief(buttonPointer, value.gtk)

	var label: String?
		get() = gtk_button_get_label(buttonPointer)?.toKString()
		set(value) = gtk_button_set_label(buttonPointer, value)

	var userUnderline: Boolean
		get() = Boolean.from(gtk_button_get_use_underline(buttonPointer))
		set(value) = gtk_button_set_use_underline(buttonPointer, value.gtkValue)

	val image: Widget?
		get() = gtk_button_get_image(buttonPointer)?.let { Widget(it) }

	var imagePosition: PositionType
		get() = PositionType.valueOf(gtk_button_get_image_position(buttonPointer))!!
		set(value) = gtk_button_set_image_position(buttonPointer, value.gtk)

	var alwaysShowImage: Boolean
		get() = Boolean.from(gtk_button_get_always_show_image(buttonPointer))
		set(value) = gtk_button_set_always_show_image(
			buttonPointer,
			value.gtkValue
		)

	val eventWindow: kotlin.gdk.Window?
		get() = gtk_button_get_event_window(buttonPointer)?.let {
			kotlin.gdk.Window(
				it
			)
		}
}

