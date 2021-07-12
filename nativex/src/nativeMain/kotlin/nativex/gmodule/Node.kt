package nativex.gmodule

/**
 * kotlinx-gtk
 *
 * 09 / 07 / 2021
 *
 * @see <a href=""></a>
 */
;

import nativex.glib.VoidPointer

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-N-ary-Trees.html#GCopyFunc">GCopyFunc</a>
 */
typealias CopyFunction = (VoidPointer) -> VoidPointer