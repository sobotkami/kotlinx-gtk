package nativex.gio.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.GtkDsl
import nativex.async.launchUnconfined
import nativex.gio.Application

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onStartup(crossinline onStartup: suspend () -> Unit) {
	launchUnconfined {
		startupSignal.collectLatest {
			onStartup()
		}
	}
}

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onShutdown(crossinline onShutdown: suspend () -> Unit) {
	launchUnconfined {
		shutdownSignal.collectLatest {
			onShutdown()
		}
	}
}

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onNameLost(crossinline onNameLost: suspend () -> Unit) {
	launchUnconfined {
		nameLostSignal.collectLatest {
			onNameLost()
		}
	}
}

@ExperimentalUnsignedTypes
@GtkDsl
/**
 * Invokes [uiBuilder] when [Application.onActivate] occurs
 *
 * This will only occur once
 */
inline fun <T> T.onCreateUI(crossinline uiBuilder: T.() -> Unit) where T : Application {
	// Building the UI has to occur on the main thread
	onActivate {
		this@onCreateUI.apply(uiBuilder)
	}
}


