package nativex.gtk

import gtk.GCallback
import gtk.g_signal_connect_data
import gtk.gpointer
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
object Signals {
	const val CLICKED = "clicked"
	const val ACTIVATE = "activate"
	const val CHANGED = "changed"
	const val VALUE_CHANGED = "value-changed"
	const val TOGGLED = "toggled"

	const val PREVIOUS_MATCH = "previous-match"
	const val SEARCH_CHANGED = "search-changed"
	const val STOP_SEARCH = "stop-search"
}

@ExperimentalUnsignedTypes
internal fun VoidPointer.connectSignal(
	signal: String,
	handler: GCallback = staticNoArgGCallback,
	callbackWrapper: COpaquePointer? = null,
	flags: UInt = 0u
): ULong =
	g_signal_connect_data(
		this,
		detailed_signal = signal,
		c_handler = handler,
		data = callbackWrapper,
		destroy_data = staticCFunction { void: gpointer?, _ ->
			void?.asStableRef<Any>()?.dispose()
		},
		connect_flags = flags
	)


/**
 * [GCallback] that calls a function with only no arguments
 */
internal val staticNoArgGCallback: GCallback =
	staticCFunction { _: gpointer?, data: gpointer? ->
		data?.asStableRef<() -> Unit>()?.get()?.invoke()
		Unit
	}.reinterpret()
