package nativex.gtk.widgets.container.bin.windows

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Application
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class ApplicationWindow internal constructor(
	internal val appWindowPointer: CPointer<GtkApplicationWindow>
) : Window(
	appWindowPointer.reinterpret()
) {
	constructor(application: Application) : this(
		gtk_application_window_new(application.pointer)!!.reinterpret()
	)

	var showMenuBar: Boolean
		get() = gtk_application_window_get_show_menubar(
			appWindowPointer
		)
			.bool
		set(value) =
			gtk_application_window_set_show_menubar(
				appWindowPointer,
				value.gtk
			)

	val uId: UInt by lazy {
		gtk_application_window_get_id(appWindowPointer)
	}

	var shortcutsWindow: ShortcutsWindow?
		get() =
			gtk_application_window_get_help_overlay(appWindowPointer)?.let {
				ShortcutsWindow(it)
			}
		set(value) = gtk_application_window_set_help_overlay(
			appWindowPointer,
			value?.shortCutsWindowPointer
		)

}