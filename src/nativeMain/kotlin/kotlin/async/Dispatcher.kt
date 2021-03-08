package kotlin.async

import kotlinx.coroutines.*

/**
 * Defines the UI thread
 * All events from [gtk] will be translated into this context
 */
val Dispatchers.UI: CoroutineDispatcher
	get() = newSingleThreadContext("UI")

/**
 * Defines the IO thread
 * All events interacting with IO streams should occur here
 */
val Dispatchers.IO: CoroutineDispatcher
	get() = newSingleThreadContext("IO")

inline fun launchUI(crossinline block: suspend CoroutineScope.() -> Unit) {
	GlobalScope.launch(context = Dispatchers.UI) {
		block()
	}
}