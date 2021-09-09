package org.gtk.gobject

import gobject.g_signal_handler_disconnect
import org.gtk.glib.VoidPointer

/**
 * Manages a signal connection
 *
 * @param pointer pointer the signal is attached to
 * @param T type of function this manager is responsible
 * @param signalId id of the signal
 */
class SignalManager(val pointer: VoidPointer, val signalId: ULong) {
	fun disconnect() {
		g_signal_handler_disconnect(pointer, signalId)
	}
}