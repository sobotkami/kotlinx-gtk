package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.Widget

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
		get() = Boolean.from(gtk_expander_get_expanded(expanderPointer))
		set(value) = gtk_expander_set_expanded(expanderPointer, value.gtkValue)

	var label: String?
		get() = gtk_expander_get_label(expanderPointer)?.toKString()
		set(value) = gtk_expander_set_label(expanderPointer, value)

	var useUnderline: Boolean
		get() = Boolean.from(gtk_expander_get_use_underline(expanderPointer))
		set(value) = gtk_expander_set_use_underline(
			expanderPointer,
			value.gtkValue
		)

	var useMarkup: Boolean
		get() = Boolean.from(gtk_expander_get_use_markup(expanderPointer))
		set(value) = gtk_expander_set_use_markup(
			expanderPointer,
			value.gtkValue
		)

	var labelWidget: Widget?
		get() = gtk_expander_get_label_widget(expanderPointer)?.let { Widget(it) }
		set(value) = gtk_expander_set_label_widget(
			expanderPointer,
			value?.widgetPointer
		)

	var labelFill: Boolean
		get() = Boolean.from(gtk_expander_get_label_fill(expanderPointer))
		set(value) = gtk_expander_set_label_fill(
			expanderPointer,
			value.gtkValue
		)

	var resizeTopLevel: Boolean
		get() = Boolean.from(gtk_expander_get_resize_toplevel(expanderPointer))
		set(value) = gtk_expander_set_resize_toplevel(
			expanderPointer,
			value.gtkValue
		)


}