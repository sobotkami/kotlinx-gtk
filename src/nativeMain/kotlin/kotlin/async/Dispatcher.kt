package kotlin.async

import gtk.g_idle_add
import gtk.gpointer
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.isFrozen

/**
 * Defines the UI thread
 * All events from [gtk] will be translated into this context
 */
@ExperimentalCoroutinesApi
val Dispatchers.UI: CoroutineDispatcher
	get() = glibExecution

private val stack: ArrayList<Pair<CoroutineContext, Runnable>> by lazy {
	arrayListOf()
}

private val function = staticCFunction { _: gpointer? ->
	stack.firstOrNull { it.first.isActive && !it.first.isFrozen }
		?.let { (context, runnable) ->
			if (!context.isFrozen && context.isActive) {
				runnable.run()
				stack.removeFirst()
			}
		}
	1
}

private val glibExecution by lazy {
	object : CoroutineDispatcher() {
		init {
			g_idle_add(function, null)
		}

		override fun dispatch(context: CoroutineContext, block: Runnable) {
			stack.add(context to block)
		}
	}
}


/**
 * Defines the IO thread
 * All events interacting with IO streams should occur here
 */
@ExperimentalCoroutinesApi
val Dispatchers.IO: CoroutineDispatcher
	get() = newSingleThreadContext("IO")

@ExperimentalCoroutinesApi
inline fun launchUI(crossinline block: suspend CoroutineScope.() -> Unit) =
	GlobalScope.launch(context = Dispatchers.UI) {
		block()
	}

@ExperimentalCoroutinesApi
inline fun launchIO(crossinline block: suspend CoroutineScope.() -> Unit) =
	GlobalScope.launch(context = Dispatchers.IO) {
		block()
	}

@ExperimentalCoroutinesApi
inline fun launchDefault(crossinline block: suspend CoroutineScope.() -> Unit) =
	GlobalScope.launch(context = Dispatchers.Default) {
		block()
	}


