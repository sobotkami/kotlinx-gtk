package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.glib.WrappedKList
import org.gtk.glib.WrappedKList.Companion.asWrappedKList
import org.gtk.gobject.KGObject
import org.gtk.gtk.widgets.windows.Window
import org.gtk.gtk.widgets.windows.Window.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 08 / 02 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.WindowGroup.html">GtkWindowGroup</a>
 */
class WindowGroup(val windowGroupPointer: CPointer<GtkWindowGroup>) : KGObject(windowGroupPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.WindowGroup.new.html">
	 *     gtk_window_group_new</a>
	 */
	constructor() : this(gtk_window_group_new()!!)


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.WindowGroup.add_window.html">
	 *     gtk_window_group_add_window</a>
	 */
	fun addWindow(window: Window) {
		gtk_window_group_add_window(windowGroupPointer, window.windowPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.WindowGroup.list_windows.html">
	 *     gtk_window_group_remove_window</a>
	 */
	fun removeWindow(window: Window) {
		gtk_window_group_remove_window(windowGroupPointer, window.windowPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.WindowGroup.remove_window.html">
	 *     gtk_window_group_list_windows</a>
	 */
	val windowList: WrappedKList<Window>
		get() = gtk_window_group_list_windows(windowGroupPointer)!!
			.asWrappedKList({ reinterpret<GtkWindow>().wrap() }, { pointer })

	companion object {
		inline fun CPointer<GtkWindowGroup>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkWindowGroup>.wrap() =
			WindowGroup(this)
	}
}