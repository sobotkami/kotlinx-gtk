package nativex.gio

import gio.*
import glib.GError
import kotlinx.cinterop.*
import nativex.glib.KGError
import nativex.glib.bool
import nativex.gobject.KGObject
import nativex.gobject.staticDestroyStableRefFunction
import nativex.gobject.staticNoArgGCallback

/**
 * kotlinx-gtk
 *
 * 14 / 04 / 2021
 *
 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html">
 *     GCancellable</a>
 */
class KGCancellable(
	val cancellablePointer: CPointer<GCancellable>
) : KGObject(cancellablePointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-new">
	 *     g_cancellable_new</a>
	 */
	constructor() : this(g_cancellable_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-is-cancelled">
	 *     g_cancellable_is_cancelled</a>
	 */
	val isCancelled: Boolean
		get() = g_cancellable_is_cancelled(cancellablePointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-set-error-if-cancelled">
	 *     g_cancellable_set_error_if_cancelled</a>
	 */
	fun setErrorIfCancelled(error: KGError): Boolean =
		memScoped {
			val v: CPointer<GError> = error.pointer
			val b = allocPointerTo<GError>()
			b.value = v
			g_cancellable_set_error_if_cancelled(cancellablePointer, b.ptr).bool
		}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-get-fd">
	 *     g_cancellable_get_fd</a>
	 */
	val fd: Int
		get() = g_cancellable_get_fd(cancellablePointer)

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-make-pollfd">
	 *     g_cancellable_make_pollfd</a>
	 */
	fun makePollFd(): PollFD? = memScoped {
		val pollFD = PollFD(this)
		return if (g_cancellable_make_pollfd(cancellablePointer, pollFD.pollFDPointer).bool) {
			pollFD
		} else {
			null
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-release-fd">
	 *     g_cancellable_release_fd</a>
	 */
	fun releaseFD() {
		g_cancellable_release_fd(cancellablePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-pop-current">
	 *     g_cancellable_pop_current</a>
	 */
	fun popCurrent() {
		g_cancellable_pop_current(cancellablePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-push-current">
	 *     g_cancellable_push_current</a>
	 */
	fun pushCurrent() {
		g_cancellable_push_current(cancellablePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-reset">
	 *     g_cancellable_reset</a>
	 */
	fun reset() {
		g_cancellable_reset(cancellablePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-connect">
	 *     g_cancellable_connect</a>
	 */
	fun connectCallback(action: () -> Unit): ULong =
		g_cancellable_connect(
			cancellablePointer,
			staticNoArgGCallback,
			StableRef.create(action).asCPointer(),
			staticDestroyStableRefFunction
		)

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-disconnect">
	 *     g_cancellable_disconnect</a>
	 */
	fun disconnectCallback(handlerId: ULong) {
		g_cancellable_disconnect(cancellablePointer, handlerId)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-cancel">
	 *     g_cancellable_cancel</a>
	 */
	fun cancel() {
		g_cancellable_cancel(cancellablePointer)
	}

	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gio/stable/GCancellable.html#g-cancellable-get-current">
		 *     g_cancellable_get_current</a>
		 */
		val current: KGCancellable?
			get() = g_cancellable_get_current().wrap()

		inline fun CPointer<GCancellable>?.wrap() =
			this?.wrap()

		inline fun CPointer<GCancellable>.wrap() =
			KGCancellable(this)
	}


}