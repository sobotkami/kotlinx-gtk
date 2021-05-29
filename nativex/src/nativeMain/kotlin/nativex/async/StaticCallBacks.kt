package nativex.async

import gtk.GCallback
import gtk.gboolean
import gtk.gpointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.gtk.bool

// This file contains generic static callbacks that are frequently used in the program

internal val staticIntCallback: GCallback =
	staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
		data?.asStableRef<(Int) -> Unit>()
			?.get()
			?.invoke(arg1)
		Unit
	}.reinterpret()

internal val staticBooleanCallback: GCallback =
	staticCFunction { _: gpointer?,
	                  select: gboolean,
	                  data: gpointer? ->
		data?.asStableRef<(Boolean) -> Unit>()?.get()
			?.invoke(select.bool)
		Unit
	}.reinterpret()