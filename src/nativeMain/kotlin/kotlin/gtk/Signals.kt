package kotlin.gtk

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
}

@ExperimentalUnsignedTypes
internal fun VoidPointer.connectSignal(
	signal: String,
	handler: GCallback = staticCallback,
	callbackWrapper: COpaquePointer? = null,
	flags: UInt = 0u
): ULong =
	g_signal_connect_data(
		this,
		signal,
		handler,
		callbackWrapper,
		staticCFunction { void: gpointer?, _ ->
			void?.asStableRef<Any>()?.dispose()
		},
		connect_flags = flags
	)


internal val staticCallback: GCallback =
	staticCFunction { _: gpointer?, data: gpointer? ->
		data?.asStableRef<() -> Unit>()?.get()?.invoke()
		Unit
	}.reinterpret()
