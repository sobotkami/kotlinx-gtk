package org.gtk.dsl.gtk

import nativex.GtkDsl
import nativex.gtk.Application
import nativex.gtk.widgets.windows.Window


/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
@GtkDsl
inline fun window( builder: Window.() -> Unit) =
	Window().apply(builder)

@GtkDsl
/**
 * Creates a window, adds it to the application
 */
inline fun Application.addWindow(
	builder: Window.() -> Unit
) = Window().apply(builder).also { this.addWindow(it) }
