package org.gtk.gobject

import gobject.GParameter
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Parameter(val pointer: CPointer<GParameter>) {
	var name: String
		get() = pointer.pointed.name!!.toKString()
		set(value) {
			memScoped {
				pointer.pointed.name = value.cstr.ptr
			}
		}

	val value: KGValue by lazy { memScoped { KGValue(pointer.pointed.value.ptr) } }
}