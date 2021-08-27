package org.gtk.gtk.widgets.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.connectSignal
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 08 / 02 / 2021
 *
 * @see <a href=""></a>
 */
open class Button(val buttonPointer: CPointer<GtkButton>) : Widget(buttonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-new"></a>
	 */
	constructor() : this(
		gtk_button_new()!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-new-with-label"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-new-with-mnemonic"></a>
	 */
	constructor(label: String, mnemonic: Boolean = false) : this(
		(if (mnemonic)
			gtk_button_new_with_mnemonic(
				label
			)
		else gtk_button_new_with_label(
			label
		))!!.reinterpret()
	)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#GtkButton-clicked"></a>
	 */
	fun addOnClickedCallback(flags: UInt = 0u, action: () -> Unit) =
		SignalManager(
			buttonPointer,
			buttonPointer.connectSignal(
				Signals.CLICKED,
				callbackWrapper = StableRef.create(action).asCPointer(),
				flags = flags
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#GtkButton-activate"></a>
	 */
	fun addOnActivateCallback(action: () -> Unit) =
		SignalManager(
			buttonPointer,
			buttonPointer.connectSignal(
				Signals.ACTIVATE,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-label"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-label"></a>
	 */
	var label: String?
		get() = gtk_button_get_label(buttonPointer)?.toKString()
		set(value) = gtk_button_set_label(buttonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-use-underline"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-use-underline"></a>
	 */
	var userUnderline: Boolean
		get() = gtk_button_get_use_underline(buttonPointer).bool
		set(value) = gtk_button_set_use_underline(buttonPointer, value.gtk)


	companion object {
		inline fun CPointer<GtkButton>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkButton>.wrap() =
			Button(this)
	}
}