package org.gtk.gtk.widgets
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString

/**
 * kotlinx-gtk
 *
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html">GtkHeaderBar</a>
 */
open class HeaderBar(
	 val headerBarPointer: CPointer<GtkHeaderBar>
) : Widget(headerBarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html#gtk-header-bar-new">gtk_header_bar_new</a>
	 */
	constructor() : this(gtk_header_bar_new()!!.reinterpret())

	constructor(headerBar: HeaderBar) : this(headerBar.headerBarPointer)


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