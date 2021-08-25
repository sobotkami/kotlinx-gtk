package org.gtk.gtk

import glib.gpointer
import gobject.GCallback
import gobject.g_signal_connect_data

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */

@Suppress("FunctionName")

fun g_signal_connect(
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
