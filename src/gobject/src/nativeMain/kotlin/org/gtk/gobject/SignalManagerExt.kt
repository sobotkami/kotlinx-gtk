package org.gtk.gobject

import glib.gpointer
import gobject.GCallback
import gobject.g_signal_connect_data
import kotlinx.cinterop.*
import org.gtk.glib.VoidPointer

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [callbackWrapper]
 * @param callbackWrapper Passed as the data parameter to `g_signal_connect_data`. Invoked by [handler]
 * @param flags Flags
 */
fun VoidPointer.connectSignal(
	signal: String,
	callbackWrapper: COpaquePointer? = null,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): ULong =
	g_signal_connect_data(
		this,
		detailed_signal = signal,
		c_handler = handler,
		data = callbackWrapper,
		// Destroys the callbackWrapper
		destroy_data = staticCFunction { void: gpointer?, _ ->
			void?.asStableRef<Any>()?.dispose()
		},
		connect_flags = flags
	)

/**
 * Convenience function merging [connectSignal] with [SignalManager]
 */
inline fun <T : CPointed> signalManager(
	pointer: CPointer<T>,
	signal: String,
	callbackWrapper: COpaquePointer? = null,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, callbackWrapper, handler, flags)
	)

/**
 * Convenience function merging [connectSignal] with [SignalManager]
 */
inline fun KGObject.addSignalCallback(
	signal: String,
	callbackWrapper: COpaquePointer?,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, callbackWrapper, handler, flags)
	)

inline fun <O> KGObject.addSignalCallback(
	signal: String,
	noinline action: () -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I0, I1, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)


inline fun <I0, I1, I2, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1, I2) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I0, I1, I2, I3, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1, I2, I3) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)
