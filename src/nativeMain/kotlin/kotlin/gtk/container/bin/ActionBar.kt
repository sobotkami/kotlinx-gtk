package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ActionBar(
	internal val actionBarPointer: CPointer<GtkActionBar>
) : Bin(actionBarPointer.reinterpret()) {
	constructor() : this(gtk_action_bar_new()!!.reinterpret())

	fun packStart(child: Widget) {
		gtk_action_bar_pack_start(actionBarPointer, child.widgetPointer)
	}

	fun packEnd(child: Widget) {
		gtk_action_bar_pack_end(actionBarPointer, child.widgetPointer)
	}

	var centerWidget: Widget?
		get() = gtk_action_bar_get_center_widget(actionBarPointer)?.let {
			Widget(it)
		}
		set(value) = gtk_action_bar_set_center_widget(
			actionBarPointer,
			value?.widgetPointer
		)

}