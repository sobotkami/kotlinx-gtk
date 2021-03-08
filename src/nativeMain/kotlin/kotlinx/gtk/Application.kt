package kotlinx.gtk

import kotlinx.coroutines.*
import kotlin.gtk.Application

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */

@GtkDsl
inline fun application(
	id: String,
	crossinline builder: Application.() -> Unit
): Int {
	return Application(id).apply {
		builder()
	}.run()
}


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