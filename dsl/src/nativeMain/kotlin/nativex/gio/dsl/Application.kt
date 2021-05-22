package nativex.gio.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.GtkDsl
import nativex.async.launchUnconfined
import nativex.gio.Application
import nativex.gio.Icon
import nativex.gio.Notification


@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onStartup(crossinline onStartup: suspend () -> Unit) {
	launchUnconfined {
		startupSignal.collectLatest {
			onStartup()
		}
	}
}


@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onShutdown(crossinline onShutdown: suspend () -> Unit) {
	launchUnconfined {
		shutdownSignal.collectLatest {
			onShutdown()
		}
	}
}


@ExperimentalCoroutinesApi
@GtkDsl
inline fun Application.onNameLost(crossinline onNameLost: suspend () -> Unit) {
	launchUnconfined {
		nameLostSignal.collectLatest {
			onNameLost()
		}
	}
}


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


@GtkDsl
inline fun Application.sendNotification(
	title: String,
	id: String? = null,
	body: String = "",
	icon: Icon? = null,
	priority: Notification.Priority = Notification.Priority.NORMAL,
	builder: Notification.() -> Unit = {}
) = Notification(title).apply {
	setBody(body)
	icon?.let { setIcon(it) }
	setPriority(priority)
	builder()
}.also {
	sendNotification(it, id)
}


