package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.Application
import nativex.gtk.widgets.container.bin.windows.Window


/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
@GtkDsl
inline fun window(type: Window.Type, builder: Window.() -> Unit) =
	Window(type).apply(builder)

@GtkDsl
/**
 * Creates a window, adds it to the application
 */
inline fun Application.addWindow(
	type: Window.Type,
	builder: Window.() -> Unit
) = Window(type).apply(builder).also { this.addWindow(it) }
