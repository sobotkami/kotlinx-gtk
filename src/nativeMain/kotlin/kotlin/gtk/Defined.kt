package kotlin.gtk

import gtk.GCallback
import gtk.g_signal_connect_data
import gtk.gpointer
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */

@Suppress("FunctionName")
@ExperimentalUnsignedTypes
internal fun g_signal_connect(
	instance: gpointer,
	detailedSignal: String,
	handler: GCallback,
	data: gpointer? = null
) {
	g_signal_connect_data(
		instance,
		detailedSignal,
		handler,
		data,
		null,
		(0).toUInt()
	)
}
