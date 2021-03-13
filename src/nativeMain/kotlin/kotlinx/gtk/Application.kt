package kotlinx.gtk

import gtk.g_idle_add
import gtk.gpointer
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.gtk.Application
import kotlin.native.concurrent.isFrozen

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */

@GtkDsl
inline fun application(
	id: String,
	crossinline builder: Application.() -> Unit
): Int =
	Application(id).apply {
		builder()
	}.run()



@GtkDsl
/**
 * Invokes [uiBuilder] when [Application.activateSignal] occurs
 *
 * This will only occur once
 */
inline fun Application.onCreateUI(crossinline uiBuilder: Application.() -> Unit) {
	// Building the UI has to occur on the main thread
	onActivate {
		this@onCreateUI.apply(uiBuilder)
	}
}