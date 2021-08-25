package org.gtk.glib

import glib.GCompareDataFunc
import glib.GFunc
import glib.gpointer
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href=""></a>
 */

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GFunc">GFunc</a>
 */
typealias KFunc = (VoidPointer) -> Unit

val staticForeachFunction: GFunc = staticCFunction { entity: gpointer, data: gpointer ->
	data.asStableRef<KFunc>().get().invoke(entity)
	@Suppress("RedundantUnitExpression")
	Unit
}.reinterpret()

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GCompareDataFunc">
 *     GCompareDataFunc</a>
 */
typealias CompareDataFunction = (a: VoidPointer?, b: VoidPointer?) -> Int

val staticCompareDataFunction: GCompareDataFunc =
	staticCFunction { p1: gpointer, p2: gpointer, data: gpointer ->
		data.asStableRef<CompareDataFunction>().get().invoke(p1, p2)
	}.reinterpret()

/**
 * @see <a href="https://docs.gtk.org/glib/callback.DestroyNotify.html">GDestroyNotify</a>
 */
typealias DestroyNotify = CPointer<CFunction<(gpointer?) -> Unit>>