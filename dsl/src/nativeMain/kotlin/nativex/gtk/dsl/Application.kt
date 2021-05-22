package nativex.gtk.dsl

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import nativex.GtkDsl
import nativex.async.launchUnconfined
import nativex.gio.Application.*
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





@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onQueryEnd(crossinline onQueryEnd: suspend () -> Unit) {
	launchUnconfined {
		queryEndSignal.collectLatest {
			onQueryEnd()
		}
	}
}

@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onWindowAdded(crossinline onWindowAdded: suspend (Window) -> Unit) {
	launchUnconfined {
		windowAddedSignal.collectLatest {
			onWindowAdded(it)
		}
	}
}



@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onWindowRemoved(crossinline onWindowRemoved: suspend (Window) -> Unit) {
	launchUnconfined {
		windowRemovedSignal.collectLatest {
			onWindowRemoved(it)
		}
	}
}