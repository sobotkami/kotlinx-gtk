package nativex.gobject

import gobject.GClosure
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 08 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gobject/stable/gobject-Closures.html#GClosure-struct">GClosure</a>
 */
class Closure(val closurePointer: CPointer<GClosure>)