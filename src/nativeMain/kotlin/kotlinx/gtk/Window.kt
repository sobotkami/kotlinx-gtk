package kotlinx.gtk

import kotlin.gtk.Application
import kotlin.gtk.container.bin.windows.ApplicationWindow
import kotlin.gtk.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */

@GtkDsl
inline fun Application.window(builder: Window.() -> Unit) {
	Window(this).apply(builder).showAll()
}

@GtkDsl
inline fun Application.applicationWindow(builder: ApplicationWindow.() -> Unit) {
	ApplicationWindow(this).apply(builder).showAll()
}