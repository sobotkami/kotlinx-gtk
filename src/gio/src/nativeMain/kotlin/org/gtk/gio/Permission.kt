package org.gtk.gio
import gio.*
import glib.GError
import kotlinx.cinterop.*
import org.gtk.glib.KGError
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.glib.unwrap

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html">GPermission</a>
 */
class Permission(val permissionPointer: CPointer<GPermission>) {

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-get-allowed">
	 *     g_permission_get_allowed</a>
	 */
	val allowed: Boolean
		get() = g_permission_get_allowed(permissionPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-get-can-acquire">
	 *     g_permission_get_can_acquire</a>
	 */
	val canAcquire: Boolean
		get() = g_permission_get_can_acquire(permissionPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-get-can-release">
	 *     g_permission_get_can_release</a>
	 */
	val canRelease: Boolean
		get() = g_permission_get_can_release(permissionPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-acquire">
	 *     g_permission_acquire</a>
	 * @throws KGError
	 */
	@Throws(KGError::class)
	fun acquire(cancellable: KGCancellable? = null): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val result = g_permission_acquire(permissionPointer, cancellable?.cancellablePointer, err)
		err.unwrap()
		result.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-acquire-async">
	 *     g_permission_acquire_async</a>
	 */
	fun acquireAsync(cancellable: KGCancellable? = null, callback: AsyncReadyCallback) {
		g_permission_acquire_async(
			permissionPointer,
			cancellable?.cancellablePointer,
			staticAsyncReadyCallback,
			StableRef.create(callback).asCPointer()
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-acquire-finish">
	 *     g_permission_acquire_finish</a>
	 * @throws KGError
	 */
	@Throws(KGError::class)
	fun acquireFinish(asyncResult: AsyncResult): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val result = g_permission_acquire_finish(permissionPointer, asyncResult.asyncResultPointer, err)
		err.unwrap()
		result.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-release">
	 *     g_permission_release</a>
	 * @throws KGError
	 */
	@Throws(KGError::class)
	fun release(cancellable: KGCancellable? = null): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val result = g_permission_release(permissionPointer, cancellable?.cancellablePointer, err)
		err.unwrap()
		result.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-release-async">
	 *     g_permission_release_async</a>
	 */
	fun releaseAsync(cancellable: KGCancellable? = null, callback: AsyncReadyCallback) {
		g_permission_release_async(
			permissionPointer,
			cancellable?.cancellablePointer,
			staticAsyncReadyCallback,
			StableRef.create(callback).asCPointer()
		)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-release-finish">
	 *     g_permission_release_finish</a>
	 * @throws KGError
	 */
	@Throws(KGError::class)
	fun releaseFinish(asyncResult: AsyncResult): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val result = g_permission_release_finish(permissionPointer, asyncResult.asyncResultPointer, err)
		err.unwrap()
		result.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GPermission.html#g-permission-impl-update">
	 *     g_permission_impl_update</a>
	 */
	fun implUpdate(allowed: Boolean, canAcquire: Boolean, canRelease: Boolean) {
		g_permission_impl_update(permissionPointer, allowed.gtk, canAcquire.gtk, canRelease.gtk)
	}

	companion object {
		fun CPointer<GPermission>?.wrap() =
			this?.wrap()

		inline fun CPointer<GPermission>.wrap() =
			Permission(this)
	}
}