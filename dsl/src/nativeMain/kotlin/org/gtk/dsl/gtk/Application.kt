package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gio.Application.Flags
import org.gtk.gtk.Application
import org.gtk.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */

@GtkDsl
inline fun application(
	id: String,
	flags: Flags = Flags.NONE,
	crossinline builder: Application.() -> Unit
): Int =
	Application(id, flags).apply {
		builder()
	}.run()