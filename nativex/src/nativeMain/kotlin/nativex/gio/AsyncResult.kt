package nativex.gio

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.staticCFunction
import nativex.gio.AsyncResult.Companion.wrap
import nativex.gio.KObject.Companion.wrap

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
	@ParameterName("source") KObject?,
	@ParameterName("result") AsyncResult
) -> Unit