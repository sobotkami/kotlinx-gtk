package nativex.gmodule

import gtk.*
import kotlinx.cinterop.CPointer
import nativex.gtk.bool

/**
 * @see <a href="https://developer.gnome.org/glib/stable/glib-Byte-Arrays.html#GBytes">GBytes</a>
 */
class KGBytes internal constructor(
	internal val pointer: CPointer<GBytes>
):Comparable<KGBytes> {

	/**
	 * @see <a href=""></a>
	 */

	/**
	 * @see <a href="https://developer.gnome.org/glib/stable/glib-Byte-Arrays.html#g-bytes-new">g_bytes_new</a>
	 */
	constructor() : this(g_bytes_new(null, 0)!!)

	override fun hashCode(): Int = g_bytes_hash(pointer).toInt()
	override fun equals(other: Any?): Boolean {
		if (other == null) return false
		if (this === other) return true
		if (this::class != other::class) return false

		other as KGBytes

		return g_bytes_equal(pointer, other.pointer).bool
	}

	override fun compareTo(other: KGBytes): Int =
		g_bytes_compare(pointer,other.pointer)



}