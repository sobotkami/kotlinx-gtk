package nativex.gio

import gtk.GCancellable
import gtk.g_cancellable_is_cancelled
import gtk.g_cancellable_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 *
 * TODO Complete
 */
class KCancellable internal constructor(
	internal val cancellablePointer: CPointer<GCancellable>
) : KObject(cancellablePointer.reinterpret()) {
	constructor() : this(g_cancellable_new()!!.reinterpret())

	val isCancelled: Boolean
		get() = g_cancellable_is_cancelled(cancellablePointer).bool


}