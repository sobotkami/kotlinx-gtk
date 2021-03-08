package kotlinx.gtk

import kotlin.gtk.Application
import kotlin.gtk.ApplicationWindow
import kotlin.gtk.Window

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