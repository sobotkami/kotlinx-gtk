package org.gtk.gobject

import glib.GDestroyNotify
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * [GCallback] that calls a function with only no arguments
 */
val staticNoArgGCallback: GCallback =
	staticCFunction { _: gpointer?, data: gpointer? ->
		data?.asStableRef<() -> Unit>()?.get()?.invoke()
		Unit
	}.reinterpret()

/**
 * Most of the library uses a stable reference as the user data. This is just a generic destroy for it
 */
val staticDestroyStableRefFunction: GDestroyNotify = staticCFunction { pointer ->
	pointer?.asStableRef<Any>()?.dispose()
	Unit
}