package org.gtk.glib

/**
 * kotlinx-gtk
 *
 * 09 / 07 / 2021
 *
 * @see <a href=""></a>
 */
;

import glib.GCopyFunc
import glib.gconstpointer
import glib.gpointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-N-ary-Trees.html#GCopyFunc">GCopyFunc</a>
 */
typealias CopyFunction = (VoidPointer) -> VoidPointer

val staticCopyFunction: GCopyFunc = staticCFunction { src: gconstpointer, data: gpointer ->
	data.asStableRef<CopyFunction>().get().invoke(src)
}.reinterpret()