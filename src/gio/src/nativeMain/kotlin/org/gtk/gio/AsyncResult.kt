package org.gtk.gio

import gio.GAsyncReadyCallback
import gio.GAsyncResult
import gio.g_async_result_get_source_object
import gio.g_async_result_get_user_data
import glib.gpointer
import gobject.GObject
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.staticCFunction
import org.gtk.gio.AsyncResult.Companion.wrap
import org.gtk.gobject.KGObject
import org.gtk.gobject.KGObject.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class AsyncResult(val asyncResultPointer: CPointer<GAsyncResult>) {
	val userData: gpointer?
		get() = g_async_result_get_user_data(asyncResultPointer)

	val sourceObject: CPointer<GObject>?
		get() = g_async_result_get_source_object(asyncResultPointer)

	companion object {
		fun CPointer<GAsyncResult>?.wrap() =
			this?.wrap()

		inline fun CPointer<GAsyncResult>.wrap() =
			AsyncResult(this)
	}
}

internal val staticAsyncReadyCallback: GAsyncReadyCallback =
	staticCFunction { sourceObject: CPointer<GObject>?, res: CPointer<GAsyncResult>?, data: gpointer? ->
		data?.asStableRef<AsyncReadyCallback>()?.get()?.invoke(sourceObject.wrap(), res!!.wrap())
		Unit
	}

typealias AsyncReadyCallback = (
	@ParameterName("source") KGObject?,
	@ParameterName("result") AsyncResult
) -> Unit