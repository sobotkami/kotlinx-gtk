package nativex.gtk.widgets.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gtk.IconSize
import nativex.gtk.common.enums.PositionType
import nativex.gtk.common.enums.ReliefStyle
import nativex.gtk.widgets.Widget

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-new-from-icon-name"></a>
	 */
	constructor(
		icon: String,
		size: IconSize
	) : this(gtk_button_new_from_icon_name(icon, size.gtk)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#GtkButton-clicked"></a>
	 */
	fun addOnClickedCallback(action: () -> Unit) =
		SignalManager(
			buttonPointer,
			buttonPointer.connectSignal(
				Signals.CLICKED,
				callbackWrapper = StableRef.create(action).asCPointer()
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-relief"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-relief"></a>
	 */
	var relief: ReliefStyle
		get() = ReliefStyle.valueOf(gtk_button_get_relief(buttonPointer))!!
		set(value) = gtk_button_set_relief(buttonPointer, value.gtk)

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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-image"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-image"></a>
	 */
	var image: Widget?
		get() = gtk_button_get_image(buttonPointer).wrap()
		set(value) = gtk_button_set_image(buttonPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-image-position"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-image-position"></a>
	 */
	var imagePosition: PositionType
		get() = PositionType.valueOf(gtk_button_get_image_position(buttonPointer))!!
		set(value) = gtk_button_set_image_position(buttonPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-always-show-image"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-set-always-show-image"></a>
	 */
	var alwaysShowImage: Boolean
		get() = gtk_button_get_always_show_image(buttonPointer).bool
		set(value) = gtk_button_set_always_show_image(
			buttonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-get-event-window"></a>
	 */
	val eventWindow: nativex.gdk.Window?
		get() = gtk_button_get_event_window(buttonPointer)?.let {
			nativex.gdk.Window(
				it
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkButton.html#gtk-button-clicked"></a>
	 */
	fun clicked() {
		gtk_button_clicked(buttonPointer)
	}

	companion object {
		inline fun CPointer<GtkButton>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkButton>.wrap() =
			Button(this)
	}
}