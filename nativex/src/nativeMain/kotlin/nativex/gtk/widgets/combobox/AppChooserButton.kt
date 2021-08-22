package nativex.gtk.widgets.combobox

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.async.staticCStringCallback
import nativex.gio.Icon
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html">GtkAppChooserButton</a>
 */
class AppChooserButton(val appChooserButton: CPointer<GtkAppChooserButton>) : ComboBox(appChooserButton.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-new">
	 *     gtk_app_chooser_button_new</a>
	 */
	constructor(contentType: String) : this(gtk_app_chooser_button_new(contentType)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-append-custom-item">
	 *     gtk_app_chooser_button_append_custom_item</a>
	 */
	fun appendCustomItem(name: String, label: String, icon: Icon) {
		gtk_app_chooser_button_append_custom_item(appChooserButton, name, label, icon.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-append-separator">
	 *     gtk_app_chooser_button_append_separator</a>
	 */
	fun appendSeparator() {
		gtk_app_chooser_button_append_separator(appChooserButton)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-set-active-custom-item">
	 *     gtk_app_chooser_button_set_active_custom_item</a>
	 */
	fun setActiveCustomItem(name: String) {
		gtk_app_chooser_button_set_active_custom_item(appChooserButton, name)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-get-show-default-item">
	 *     gtk_app_chooser_button_get_show_default_item</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-set-show-default-item">
	 *     gtk_app_chooser_button_set_show_default_item</a>
	 */
	var showDefaultItem: Boolean
		get() = gtk_app_chooser_button_get_show_default_item(appChooserButton).bool
		set(value) = gtk_app_chooser_button_set_show_default_item(appChooserButton, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-get-show-dialog-item">
	 *     gtk_app_chooser_button_get_show_dialog_item</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-set-show-dialog-item">
	 *     gtk_app_chooser_button_set_show_dialog_item</a>
	 */
	var showDialogItem: Boolean
		get() = gtk_app_chooser_button_get_show_dialog_item(appChooserButton).bool
		set(value) = gtk_app_chooser_button_set_show_dialog_item(appChooserButton, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-get-heading">
	 *     gtk_app_chooser_button_get_heading</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#gtk-app-chooser-button-set-heading">
	 *     gtk_app_chooser_button_set_heading</a>
	 */
	var heading: String?
		get() = gtk_app_chooser_button_get_heading(appChooserButton)?.toKString()
		set(value) = gtk_app_chooser_button_set_heading(appChooserButton, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAppChooserButton.html#GtkAppChooserButton-custom-item-activated">
	 *     custom-item-activated</a>
	 */
	fun addOnCustomItemActivatedCallback(action: (String) -> Unit) {
		addSignalCallback(Signals.CUSTOM_ITEM_ACTIVATED, action, staticCStringCallback,)
	}

}