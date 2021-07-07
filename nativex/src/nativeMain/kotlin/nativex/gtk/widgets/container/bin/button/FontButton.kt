package nativex.gtk.widgets.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.signalManager
import nativex.gtk.FontChooser

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html">GtkFontButton</a>
 */
class FontButton(
	val fontButtonPointer: CPointer<GtkFontButton>
) : Button(fontButtonPointer.reinterpret()), FontChooser {
	override val fontChooserPointer: CPointer<GtkFontChooser> by lazy { fontButtonPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-new">
	 *     gtk_font_button_new</a>
	 */
	constructor() : this(gtk_font_button_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-new-with-font">
	 *     gtk_font_button_new_with_font</a>
	 */
	constructor(fontName: String) : this(gtk_font_button_new_with_font(fontName)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-get-show-style">
	 *     gtk_font_button_get_show_style</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-set-show-style">
	 *     gtk_font_button_set_show_style</a>
	 */
	var showStyle: Boolean
		get() = gtk_font_button_get_show_style(fontButtonPointer).bool
		set(value) = gtk_font_button_set_show_style(
			fontButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-get-show-size">
	 *     gtk_font_button_get_show_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-set-show-size">
	 *     gtk_font_button_set_show_size</a>
	 */
	var showSize: Boolean
		get() = gtk_font_button_get_show_size(fontButtonPointer).bool
		set(value) = gtk_font_button_set_show_size(
			fontButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-get-use-font">
	 *     gtk_font_button_get_use_font</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-set-use-font">
	 *     gtk_font_button_set_use_font</a>
	 */
	var useFont: Boolean
		get() = gtk_font_button_get_use_font(fontButtonPointer).bool
		set(value) = gtk_font_button_set_use_font(
			fontButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-get-use-size">
	 *     gtk_font_button_get_use_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-set-use-size">
	 *     gtk_font_button_set_use_size</a>
	 */
	var useSize: Boolean
		get() = gtk_font_button_get_use_size(fontButtonPointer).bool
		set(value) = gtk_font_button_set_use_size(
			fontButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-get-title">
	 *     gtk_font_button_get_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#gtk-font-button-set-title">
	 *     gtk_font_button_set_title</a>
	 */
	var title: String?
		get() = gtk_font_button_get_title(fontButtonPointer)?.toKString()
		set(value) = gtk_font_button_set_title(fontButtonPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontButton.html#GtkFontButton-font-set">font-set</a>
	 */
	fun addOnFontSetCallback(action: () -> Unit): SignalManager =
		signalManager(fontButtonPointer, Signals.FONT_SET, StableRef.create(action).asCPointer())
}