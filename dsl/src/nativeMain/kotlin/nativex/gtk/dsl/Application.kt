package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gio.Application.Flags
import nativex.gtk.Application
import nativex.gtk.widgets.container.bin.windows.Window

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

@GtkDsl
inline fun Application.onQueryEnd(noinline onQueryEnd: () -> Unit) =
	addOnQueryEndCallback(onQueryEnd)

@GtkDsl
inline fun Application.onWindowAdded(noinline onWindowAdded: (Window) -> Unit) =
	addOnWindowAddedCallback(onWindowAdded)

@GtkDsl
inline fun Application.onWindowRemoved(noinline onWindowRemoved: (Window) -> Unit) =
	addOnWindowRemovedCallback(onWindowRemoved)
