package nativex.gtk.dsl

import nativex.gtk.Application
import nativex.gtk.widgets.container.bin.windows.ApplicationWindow

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@GtkDsl
inline fun Application.applicationWindow(builder: ApplicationWindow.() -> Unit) =
	ApplicationWindow(this).apply(builder)
