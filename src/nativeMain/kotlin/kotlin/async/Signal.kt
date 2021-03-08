package kotlin.async

import gtk.g_signal_handler_disconnect
import kotlinx.cinterop.StableRef
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.g.KotlinGObject
import kotlin.gtk.Signals
import kotlin.gtk.connectSignal


internal inline fun KotlinGObject.callbackSignalFlow(signal: String): Flow<Unit> =
	callbackFlow {
		val id = pointer.connectSignal(
			signal = Signals.CLICKED,
			callbackWrapper = StableRef.create {
				offer(Unit)
			}.asCPointer()
		)

		awaitClose {
			g_signal_handler_disconnect(pointer, id)
		}
	}

/**
 * TODO Create signals for events and other complex data
 */
internal inline fun KotlinGObject.callbackEventSignalFlow(signal: String) =
	callbackFlow {
		val id = pointer.connectSignal(
			signal = Signals.CLICKED,
			callbackWrapper = StableRef.create {
				offer(Unit)
			}.asCPointer()
		)

		awaitClose {
			g_signal_handler_disconnect(pointer, id)
		}
	}