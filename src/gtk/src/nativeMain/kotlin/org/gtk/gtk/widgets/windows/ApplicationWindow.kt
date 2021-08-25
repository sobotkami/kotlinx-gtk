package org.gtk.gtk.widgets.windows

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gtk.Application
import org.gtk.gtk.widgets.windows.ShortcutsWindow.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 07 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html">GtkApplicationWindow</a>
 */
class ApplicationWindow(val appWindowPointer: CPointer<GtkApplicationWindow>) : Window(appWindowPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-new">
	 *     gtk_application_window_new</a>
	 */
	constructor(application: Application) : this(
		gtk_application_window_new(application.applicationPointer)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-get-show-menubar">
	 *     gtk_application_window_get_show_menubar</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-set-show-menubar">
	 *     gtk_application_window_set_show_menubar</a>
	 */
	var showMenuBar: Boolean
		get() = gtk_application_window_get_show_menubar(appWindowPointer).bool
		set(value) = gtk_application_window_set_show_menubar(appWindowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-get-id">
	 *     gtk_application_window_get_id</a>
	 */
	val id: UInt by lazy {
		gtk_application_window_get_id(appWindowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-get-help-overlay">
	 *     gtk_application_window_get_help_overlay</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html#gtk-application-window-set-help-overlay">
	 *     gtk_application_window_set_help_overlay</a>
	 */
	var shortcutsWindow: ShortcutsWindow?
		get() = gtk_application_window_get_help_overlay(appWindowPointer).wrap()
		set(value) = gtk_application_window_set_help_overlay(appWindowPointer, value?.shortCutsWindowPointer)
}