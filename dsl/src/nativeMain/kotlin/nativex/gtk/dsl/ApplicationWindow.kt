package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.Application
import nativex.gtk.widgets.windows.ApplicationWindow

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@GtkDsl
inline fun Application.applicationWindow(builder: ApplicationWindow.() -> Unit): ApplicationWindow =
	ApplicationWindow(this).apply(builder)
