package nativex.gtk.dsl

import nativex.gtk.Application
import nativex.gtk.widgets.container.bin.windows.ApplicationWindow
import nativex.gtk.widgets.container.bin.windows.Window


/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */

@GtkDsl
inline fun Application.window(builder: Window.() -> Unit) {
	ApplicationWindow(this).apply(builder).showAll()
}

@GtkDsl
inline fun Application.applicationWindow(builder: ApplicationWindow.() -> Unit) {
	ApplicationWindow(this).apply(builder).showAll()
}