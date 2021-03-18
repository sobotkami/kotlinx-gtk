package nativex.gtk.dsl

import kotlinx.coroutines.*
import nativex.gtk.Application

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