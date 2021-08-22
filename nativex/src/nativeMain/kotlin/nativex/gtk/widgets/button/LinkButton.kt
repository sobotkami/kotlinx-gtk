package nativex.gtk.widgets.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html">GtkLinkButton</a>
 */
class LinkButton(
	val linkButtonPointer: CPointer<GtkLinkButton>
) : Button(linkButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-new">
	 *     gtk_link_button_new</a>
	 */
	constructor(uri: String) : this(gtk_link_button_new(uri)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-new-with-label">
	 *     gtk_link_button_new_with_label</a>
	 */
	constructor(uri: String, label: String) : this(
		gtk_link_button_new_with_label(uri, label)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-get-uri">
	 *     gtk_link_button_get_uri</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-set-uri">
	 *     gtk_link_button_set_uri</a>
	 */
	var uri: String
		get() = gtk_link_button_get_uri(linkButtonPointer)!!.toKString()
		set(value) = gtk_link_button_set_uri(linkButtonPointer, uri)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-get-visited">
	 *     gtk_link_button_get_visited</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#gtk-link-button-set-visited">
	 *     gtk_link_button_set_visited</a>
	 */
	var visited: Boolean
		get() = gtk_link_button_get_visited(linkButtonPointer).bool
		set(value) = gtk_link_button_set_visited(
			linkButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLinkButton.html#GtkLinkButton-activate-link">
	 *     activate-link</a>
	 */
	fun addOnActivateLinkCallback(action: () -> Unit): SignalManager =
		addSignalCallback(Signals.ACTIVATE_LINK, action)
}