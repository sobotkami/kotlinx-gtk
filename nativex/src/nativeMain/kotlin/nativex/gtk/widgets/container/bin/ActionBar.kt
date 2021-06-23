package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html">GtkActionBar</a>
 */
open class ActionBar(
	internal val actionBarPointer: CPointer<GtkActionBar>
) : Bin(actionBarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html#gtk-action-bar-get-center-widget">
	 *     gtk_action_bar_get_center_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html#gtk-action-bar-set-center-widget">
	 *     gtk_action_bar_set_center_widget</a>
	 */
	var centerWidget: Widget?
		get() = gtk_action_bar_get_center_widget(actionBarPointer)?.let {
			Widget(it)
		}
		set(value) = gtk_action_bar_set_center_widget(
			actionBarPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html#gtk-action-bar-new">
	 *     gtk_action_bar_new</a>
	 */
	constructor() : this(gtk_action_bar_new()!!.reinterpret())

	/**
	 * Constructor to allow foreign inheritance
	 *
	 * Used in DSL library
	 */
	constructor(actionBar: ActionBar) : this(actionBar.actionBarPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html#gtk-action-bar-pack-start">
	 *     gtk_action_bar_pack_start</a>
	 */
	fun packStart(child: Widget) {
		gtk_action_bar_pack_start(actionBarPointer, child.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkActionBar.html#gtk-action-bar-pack-end">
	 *     gtk_action_bar_pack_end</a>
	 */
	fun packEnd(child: Widget) {
		gtk_action_bar_pack_end(actionBarPointer, child.widgetPointer)
	}
}