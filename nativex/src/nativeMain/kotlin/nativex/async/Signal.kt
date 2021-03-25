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
import nativex.gio.KotlinGObject
import nativex.gtk.connectSignal


@ExperimentalCoroutinesApi
@ExperimentalUnsignedTypes
internal inline fun KotlinGObject.callbackSignalFlow(signal: String): Flow<Unit> =
	callbackFlow {
		val id = pointer.connectSignal(
			signal = signal,
			callbackWrapper = StableRef.create {
				offer(Unit)
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
@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
internal inline fun <T> KotlinGObject.callbackSignalFlow(
	signal: String,
	handler: GCallback,
): Flow<T> = pointer.callbackSignalFlow(signal, handler)

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [connectSignal.callbackWrapper]
 */
@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
internal inline fun <T> CPointer<GObject>.callbackSignalFlow(
	signal: String,
	handler: GCallback
): Flow<T> = callbackFlow {
	val id = this@callbackSignalFlow.connectSignal(
		signal = signal,
		callbackWrapper = StableRef.create { t: T ->
			offer(t)
		}.asCPointer(),
		handler = handler
	)

	awaitClose {
		g_signal_handler_disconnect(this@callbackSignalFlow, id)
	}
}