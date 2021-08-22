package org.gnome.gtkx.coroutines

import gobject.GCallback
import gobject.GObject
import gobject.g_signal_handler_disconnect
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nativex.gobject.KGObject
import nativex.gobject.connectSignal


@Deprecated("Use callbacks instead")
inline fun KGObject.signalFlow(signal: String): Lazy<Flow<Unit>> = lazy(kotlin.LazyThreadSafetyMode.NONE) {
	callbackFlow {
		val id = pointer.connectSignal(
			signal = signal,
			callbackWrapper = kotlinx.cinterop.StableRef.create {
				trySend(kotlin.Unit)
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
@Deprecated("Use callbacks instead")
inline fun <T> KGObject.signalFlow(
	signal: String,
	handler: GCallback,
): Lazy<Flow<T>> = lazy(kotlin.LazyThreadSafetyMode.NONE) {
	pointer.callbackSignalFlow(signal, handler)
}

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [connectSignal.callbackWrapper]
 */
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