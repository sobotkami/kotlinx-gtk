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
 * 24 / 03 / 2021
 */
open class HeaderBar internal constructor(
	internal val headerBarPointer: CPointer<GtkHeaderBar>
) : Container(headerBarPointer.reinterpret()) {
	constructor() : this(gtk_header_bar_new()!!.reinterpret())
	constructor(headerBar: HeaderBar) : this(headerBar.headerBarPointer)

	var title: String?
		get() = gtk_header_bar_get_title(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_title(headerBarPointer, value)

	var subTitle: String?
		get() = gtk_header_bar_get_subtitle(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_subtitle(headerBarPointer, value)

	var hasSubtitle: Boolean
		get() = gtk_header_bar_get_has_subtitle(headerBarPointer).bool
		set(value) = gtk_header_bar_set_has_subtitle(
			headerBarPointer,
			value.gtk
		)


	var customTitle: Widget?
		get() = gtk_header_bar_get_custom_title(headerBarPointer).asWidgetOrNull()
		set(value) = gtk_header_bar_set_custom_title(
			headerBarPointer,
			value?.widgetPointer
		)

	fun packStart(child: Widget) {
		gtk_header_bar_pack_start(headerBarPointer, child.widgetPointer)
	}

	fun packEnd(child: Widget) {
		gtk_header_bar_pack_end(headerBarPointer, child.widgetPointer)
	}

	var showCloseButton: Boolean
		get() = gtk_header_bar_get_show_close_button(headerBarPointer).bool
		set(value) = gtk_header_bar_set_show_close_button(
			headerBarPointer,
			value.gtk
		)
	var decorationLayout: String?
		get() = gtk_header_bar_get_decoration_layout(headerBarPointer)?.toKString()
		set(value) = gtk_header_bar_set_decoration_layout(
			headerBarPointer,
			value
		)

	companion object{
		internal inline fun CPointer<GtkHeaderBar>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkHeaderBar>.wrap() =
			HeaderBar(this)
	}


}