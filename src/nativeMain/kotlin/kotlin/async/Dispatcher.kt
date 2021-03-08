package kotlin.async

import kotlinx.coroutines.*

/**
 * Defines the UI thread
 * All events from [gtk] will be translated into this context
 */
@ExperimentalCoroutinesApi
val Dispatchers.UI: CoroutineDispatcher
	get() = newSingleThreadContext("UI")

/**
 * Defines the IO thread
 * All events interacting with IO streams should occur here
 */
@ExperimentalCoroutinesApi
val Dispatchers.IO: CoroutineDispatcher
	get() = newSingleThreadContext("IO")

@ExperimentalCoroutinesApi
inline fun launchUI(crossinline block: suspend CoroutineScope.() -> Unit) {
	GlobalScope.launch(context = Dispatchers.UI) {
		block()
	}
}