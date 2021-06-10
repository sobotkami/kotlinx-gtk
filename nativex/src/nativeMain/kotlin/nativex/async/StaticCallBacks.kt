package nativex.async

import gtk.*
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import nativex.gtk.CString
import nativex.gtk.bool
import nativex.gtk.common.enums.MovementStep
import nativex.gtk.widgets.container.FlowBox

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
	                  arg1: gboolean,
	                  data: gpointer? ->
		data?.asStableRef<(Boolean) -> Unit>()?.get()
			?.invoke(arg1.bool)
		Unit
	}.reinterpret()

internal val staticCStringCallback: GCallback =
	staticCFunction { _: gpointer?,
	                  arg1: CString,
	                  data: gpointer? ->
		data?.asStableRef<(String) -> Unit>()?.get()
			?.invoke(arg1.toKString())
		Unit
	}.reinterpret()

/**
 * Most of the library uses a stable reference as the user data. This is just a generic destroy for it
 */
internal val staticDestroyStableRefFunction: GDestroyNotify = staticCFunction { pointer ->
	pointer?.asStableRef<Any>()?.dispose()
}



