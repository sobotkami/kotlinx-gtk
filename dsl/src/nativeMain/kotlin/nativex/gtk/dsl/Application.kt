package nativex.gtk.dsl

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import nativex.async.launchUnconfined
import nativex.g.Application.*
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


@ExperimentalUnsignedTypes
@GtkDsl
/**
 * Invokes [uiBuilder] when [Application.onActivate] occurs
 *
 * This will only occur once
 */
inline fun Application.onCreateUI(crossinline uiBuilder: Application.() -> Unit) {
	// Building the UI has to occur on the main thread
	onActivate {
		this@onCreateUI.apply(uiBuilder)
	}
}

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onWindowAdded(crossinline onWindowAdded: suspend (Window) -> Unit) {
	launchUnconfined {
		windowAddedSignal.collectLatest {
			onWindowAdded(it)
		}
	}
}


@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onWindowRemoved(crossinline onWindowRemoved: suspend (Window) -> Unit) {
	launchUnconfined {
		windowRemovedSignal.collectLatest {
			onWindowRemoved(it)
		}
	}
}