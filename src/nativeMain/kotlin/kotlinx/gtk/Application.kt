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
	onActivate {
		this@onCreateUI.apply(uiBuilder)
	}
}