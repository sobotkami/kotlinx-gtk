package org.gtk.async

import glib.gboolean
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.*
import org.gtk.glib.CStringPointer
import org.gtk.glib.VoidPointer
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.signalManager
import org.gtk.gtk.WidgetPointer
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.Widget.Companion.wrap

// This file contains generic static callbacks that are frequently used in the program

val staticIntCallback: GCallback =
	staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
		data?.asStableRef<(Int) -> Unit>()
			?.get()
			?.invoke(arg1)
		Unit
	}.reinterpret()

val staticDoubleCallback: GCallback =
	staticCFunction { _: gpointer?, arg1: Double, data: gpointer? ->
		data?.asStableRef<(Double) -> Unit>()
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
	                  arg1: CStringPointer,
	                  data: gpointer? ->
		data?.asStableRef<(String) -> Unit>()?.get()
			?.invoke(arg1.toKString())
		Unit
	}.reinterpret()


/**
 * Used for [org.gtk.gobject.Signals.ACTIVATE_LINK]
 */
internal val staticActivateLinkFunction: GCallback by lazy {
	staticCFunction { _: gpointer?, char: CStringPointer, data: gpointer? ->
		data?.asStableRef<ActivateLinkFunction>()?.get()?.invoke(char.toKString()).gtk
	}.reinterpret()
}

typealias ActivateLinkFunction = (@ParameterName("uri") String) -> Boolean

internal inline fun activateLinkSignalManager(pointer: VoidPointer, noinline action: ActivateLinkFunction) =
	signalManager(
		pointer,
		Signals.ACTIVATE_LINK,
		StableRef.create(action).asCPointer(),
		staticActivateLinkFunction
	)

/**
 * Used for [org.gtk.gobject.Signals.POPULATE_POPUP]
 */
internal val staticPopulatePopupFunction: GCallback =
	staticCFunction { _: gpointer?, previous: WidgetPointer, data: gpointer? ->
		data?.asStableRef<PopulatePopupFunction>()?.get()?.invoke(previous.wrap())
		Unit
	}.reinterpret()

typealias PopulatePopupFunction = (Widget) -> Unit

internal inline fun populatePopupSignalManager(pointer: VoidPointer, noinline action: PopulatePopupFunction) =
	signalManager(
		pointer,
		Signals.POPULATE_POPUP,
		StableRef.create(action).asCPointer(),
		staticPopulatePopupFunction
	)


inline fun popdownSignalManager(pointer: VoidPointer, noinline action: () -> Unit): SignalManager =
	signalManager(pointer, Signals.POPDOWN, StableRef.create(action).asCPointer())


inline fun popupSignalManager(pointer: VoidPointer, noinline action: () -> Unit): SignalManager =
	signalManager(pointer, Signals.POPUP, StableRef.create(action).asCPointer())

