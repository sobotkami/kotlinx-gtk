package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.box.Box

/**
 * kotlinx-gtk
 *
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html">GtkHeaderBar</a>
 */
open class HeaderBar(
	 val headerBarPointer: CPointer<GtkHeaderBar>
) : Container(headerBarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-new">gtk_header_bar_new</a>
	 */
	constructor() : this(gtk_header_bar_new()!!.reinterpret())

	constructor(headerBar: HeaderBar) : this(headerBar.headerBarPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-title">
	 *     gtk_header_bar_get_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-title">
	 *     gtk_header_bar_set_title</a>
	 */
	var title: String?
		get() = gtk_header_bar_get_title(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_title(headerBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-subtitle">
	 *     gtk_header_bar_get_subtitle</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-subtitle">
	 *     gtk_header_bar_set_subtitle</a>
	 */
	var subTitle: String?
		get() = gtk_header_bar_get_subtitle(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_subtitle(headerBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-has-subtitle">
	 *     gtk_header_bar_get_has_subtitle</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-has-subtitle">
	 *     gtk_header_bar_set_has_subtitle</a>
	 */
	var hasSubtitle: Boolean
		get() = gtk_header_bar_get_has_subtitle(headerBarPointer).bool
		set(value) = gtk_header_bar_set_has_subtitle(
			headerBarPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-custom-title">
	 *     gtk_header_bar_get_custom_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-custom-title">
	 *     gtk_header_bar_set_custom_title</a>
	 */
	var customTitle: Widget?
		get() = gtk_header_bar_get_custom_title(headerBarPointer).asWidgetOrNull()
		set(value) = gtk_header_bar_set_custom_title(
			headerBarPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-pack-start">
	 *     gtk_header_bar_pack_start</a>
	 */
	fun packStart(child: Widget) {
		gtk_header_bar_pack_start(headerBarPointer, child.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-pack-end">
	 *     gtk_header_bar_pack_end</a>
	 */
	fun packEnd(child: Widget) {
		gtk_header_bar_pack_end(headerBarPointer, child.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-show-close-button">
	 *     gtk_header_bar_get_show_close_button</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-show-close-button">
	 *     gtk_header_bar_set_show_close_button</a>
	 */
	var showCloseButton: Boolean
		get() = gtk_header_bar_get_show_close_button(headerBarPointer).bool
		set(value) = gtk_header_bar_set_show_close_button(
			headerBarPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-get-decoration-layout">
	 *     gtk_header_bar_get_decoration_layout</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-set-decoration-layout">
	 *     gtk_header_bar_set_decoration_layout</a>
	 */
	var decorationLayout: String?
		get() = gtk_header_bar_get_decoration_layout(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_decoration_layout(
			headerBarPointer,
			value
		)

	companion object{
		 inline fun CPointer<GtkHeaderBar>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkHeaderBar>.wrap() =
			HeaderBar(this)
	}


}