package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gtk.Signals
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Expander(
	internal val expanderPointer: CPointer<GtkExpander>
) : Bin(expanderPointer.reinterpret()) {
	constructor(
		label: String?,
		mnemonic: Boolean = false
	) : this(
		(if (mnemonic) gtk_expander_new_with_mnemonic(label)
		else gtk_expander_new(label))!!.reinterpret()
	)

	var expanded: Boolean
		get() = gtk_expander_get_expanded(expanderPointer).bool
		set(value) = gtk_expander_set_expanded(expanderPointer, value.gtk)

	var label: String?
		get() = gtk_expander_get_label(expanderPointer)?.toKString()
		set(value) = gtk_expander_set_label(expanderPointer, value)

	var useUnderline: Boolean
		get() = gtk_expander_get_use_underline(expanderPointer).bool
		set(value) = gtk_expander_set_use_underline(
			expanderPointer,
			value.gtk
		)

	var useMarkup: Boolean
		get() = gtk_expander_get_use_markup(expanderPointer).bool
		set(value) = gtk_expander_set_use_markup(
			expanderPointer,
			value.gtk
		)

	var labelWidget: Widget?
		get() = gtk_expander_get_label_widget(expanderPointer)?.let { Widget(it) }
		set(value) = gtk_expander_set_label_widget(
			expanderPointer,
			value?.widgetPointer
		)

	var labelFill: Boolean
		get() = gtk_expander_get_label_fill(expanderPointer).bool
		set(value) = gtk_expander_set_label_fill(
			expanderPointer,
			value.gtk
		)

	var resizeTopLevel: Boolean
		get() = gtk_expander_get_resize_toplevel(expanderPointer).bool
		set(value) = gtk_expander_set_resize_toplevel(
			expanderPointer,
			value.gtk
		)

	val activate: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.ACTIVATE)
	}


}