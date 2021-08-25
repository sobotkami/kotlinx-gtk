package org.gtk.gdk

import gtk.GdkKeymapKey
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gdk4/struct.KeymapKey.html">GdkKeymapKey</a>
 */
class KeymapKey(val struct: CPointer<GdkKeymapKey>) {
	var group: Int
		get() = struct.pointed.group
		set(value) {
			struct.pointed.group = value
		}

	var level: Int
		get() = struct.pointed.level
		set(value) {
			struct.pointed.level = value
		}
}