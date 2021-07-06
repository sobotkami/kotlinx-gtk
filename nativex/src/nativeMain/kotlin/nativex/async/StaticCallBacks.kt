package nativex.async

import glib.gboolean
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import nativex.glib.CString
import nativex.glib.bool

// This file contains generic static callbacks that are frequently used in the program

val staticIntCallback: GCallback =
	staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
		data?.asStableRef<(Int) -> Unit>()
			?.get()
			?.invoke(arg1)
		Unit
	}.reinterpret()

val staticBooleanCallback: GCallback =
	staticCFunction { _: gpointer?,
	                  arg1: gboolean,
	                  data: gpointer? ->
		data?.asStableRef<(Boolean) -> Unit>()?.get()
			?.invoke(arg1.bool)
		Unit
	}.reinterpret()

val staticCStringCallback: GCallback =
	staticCFunction { _: gpointer?,
	                  arg1: CString,
	                  data: gpointer? ->
		data?.asStableRef<(String) -> Unit>()?.get()
			?.invoke(arg1.toKString())
		Unit
	}.reinterpret()




