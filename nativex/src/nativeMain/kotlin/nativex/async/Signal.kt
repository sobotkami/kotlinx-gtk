package nativex.async

import gtk.GCallback
import gtk.GObject
import gtk.g_signal_handler_disconnect
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nativex.gio.KObject
import nativex.gtk.VoidPointer
import nativex.gtk.connectSignal


@ExperimentalCoroutinesApi
 inline fun KObject.signalFlow(signal: String): Lazy<Flow<Unit>> = lazy(LazyThreadSafetyMode.NONE) {
	callbackFlow {
		val id = pointer.connectSignal(
			signal = signal,
			callbackWrapper = StableRef.create {
				trySend(Unit)
			}.asCPointer()
		)

		awaitClose {
			g_signal_handler_disconnect(pointer, id)
		}
	}
}

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [connectSignal.callbackWrapper]
 */
@ExperimentalCoroutinesApi
 inline fun <T> KObject.signalFlow(
	signal: String,
	handler: GCallback,
): Lazy<Flow<T>> = lazy(LazyThreadSafetyMode.NONE) {
	pointer.callbackSignalFlow(signal, handler)
}


@Deprecated("simplify codebase with lazy inlined", ReplaceWith("signalFlow(signal)", "nativex.async.signalFlow"))
@ExperimentalCoroutinesApi
 inline fun KObject.callbackSignalFlow(signal: String): Flow<Unit> =
	callbackFlow {
		val id = pointer.connectSignal(
			signal = signal,
			callbackWrapper = StableRef.create {
				trySend(Unit)
			}.asCPointer()
		)

		awaitClose {
			g_signal_handler_disconnect(pointer, id)
		}
	}

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [connectSignal.callbackWrapper]
 */
@Deprecated(
	"simplify codebase with lazy inlined",
	ReplaceWith("signalFlow(signal,handler)", "nativex.async.signalFlow")
)
@ExperimentalCoroutinesApi
 inline fun <T> KObject.callbackSignalFlow(
	signal: String,
	handler: GCallback,
): Flow<T> = pointer.callbackSignalFlow(signal, handler)

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [connectSignal.callbackWrapper]
 */
@ExperimentalCoroutinesApi
 inline fun <T> CPointer<GObject>.callbackSignalFlow(
	signal: String,
	handler: GCallback
): Flow<T> = callbackFlow {
	val id = this@callbackSignalFlow.connectSignal(
		signal = signal,
		callbackWrapper = StableRef.create { t: T ->
			trySend(t)
		}.asCPointer(),
		handler = handler
	)

	awaitClose {
		g_signal_handler_disconnect(this@callbackSignalFlow, id)
	}
}


/**
 * Manages a signal connection
 *
 * @param pointer pointer the signal is attached to
 * @param T type of function this manager is responsible
 * @param signalId id of the signal
 */
class SignalManager( val pointer: VoidPointer,  val signalId: ULong) {
	fun disconnect() {
		g_signal_handler_disconnect(pointer, signalId)
	}
}