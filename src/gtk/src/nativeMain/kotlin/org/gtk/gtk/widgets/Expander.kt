package org.gtk.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html">GtkExpander</a>
 */
class Expander(
	val expanderPointer: CPointer<GtkExpander>
) : Widget(expanderPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-new">
	 *     gtk_expander_new</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-new-with-mnemonic">
	 *     gtk_expander_new_with_mnemonic</a>
	 */
	constructor(
		label: String?,
		mnemonic: Boolean = false
	) : this(
		(if (mnemonic) gtk_expander_new_with_mnemonic(label)
		else gtk_expander_new(label))!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-expanded">
	 *     gtk_expander_get_expanded</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-expanded">
	 *     gtk_expander_set_expanded</a>
	 */
	var expanded: Boolean
		get() = gtk_expander_get_expanded(expanderPointer).bool
		set(value) = gtk_expander_set_expanded(expanderPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-label">
	 *     gtk_expander_get_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-label">
	 *     gtk_expander_set_label</a>
	 */
	var label: String?
		get() = gtk_expander_get_label(expanderPointer)?.toKString()
		set(value) = gtk_expander_set_label(expanderPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-use-underline">
	 *     gtk_expander_get_use_underline</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-use-underline">
	 *     gtk_expander_set_use_underline</a>
	 */
	var useUnderline: Boolean
		get() = gtk_expander_get_use_underline(expanderPointer).bool
		set(value) = gtk_expander_set_use_underline(
			expanderPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-use-markup">
	 *     gtk_expander_get_use_markup</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-use-markup">
	 *     gtk_expander_set_use_markup</a>
	 */
	var useMarkup: Boolean
		get() = gtk_expander_get_use_markup(expanderPointer).bool
		set(value) = gtk_expander_set_use_markup(
			expanderPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-label-widget">
	 *     gtk_expander_get_label_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-label-widget">
	 *     gtk_expander_set_label_widget</a>
	 */
	var labelWidget: Widget?
		get() = gtk_expander_get_label_widget(expanderPointer)?.let { Widget(it) }
		set(value) = gtk_expander_set_label_widget(
			expanderPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-get-resize-toplevel">
	 *     gtk_expander_get_resize_toplevel</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#gtk-expander-set-resize-toplevel">
	 *     gtk_expander_set_resize_toplevel</a>
	 */
	var resizeTopLevel: Boolean
		get() = gtk_expander_get_resize_toplevel(expanderPointer).bool
		set(value) = gtk_expander_set_resize_toplevel(
			expanderPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkExpander.html#GtkExpander-activate">activate</a>
	 */
	fun addOnActivateCallback(action: () -> Unit) =
		addSignalCallback(Signals.ACTIVATE, action)
}