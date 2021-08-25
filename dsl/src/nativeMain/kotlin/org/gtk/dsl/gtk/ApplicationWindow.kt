package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.Application
import org.gtk.gtk.widgets.windows.ApplicationWindow

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@GtkDsl
inline fun Application.applicationWindow(builder: ApplicationWindow.() -> Unit): ApplicationWindow =
	ApplicationWindow(this).apply(builder)
