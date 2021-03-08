package kotlin.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

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
		get() = Boolean.from(
			gtk_application_window_get_show_menubar(
				appWindowPointer
			)
		)
		set(value) =
			gtk_application_window_set_show_menubar(
				appWindowPointer,
				value.gtkValue
			)

	val uId: UInt by lazy {
		gtk_application_window_get_id(appWindowPointer)
	}

	var shortcutsWindow: ShortcutsWindow?
		get() =
			gtk_application_window_get_help_overlay(appWindowPointer)?.let {
				ShortcutsWindow(
					it
				)
			}
		set(value) = gtk_application_window_set_help_overlay(
			appWindowPointer,
			value?.shortCutsWindowPointer
		)

}