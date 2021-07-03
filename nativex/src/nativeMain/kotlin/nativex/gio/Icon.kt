package nativex.gio

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import nativex.glib.Variant
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 28 / 03 / 2021
 */
interface Icon {
	val pointer: CPointer<GIcon>


	val hash: UInt
		get() = g_icon_hash(pointer)

	fun equal(other: Icon): Boolean =
		g_icon_equal(pointer, other.pointer).bool

	fun iconToString(): String? =
		g_icon_to_string(pointer)?.toKString()

	fun serialize(): Variant? =
		g_icon_serialize(pointer)?.let { Variant(it) }

	companion object {
		fun newForString(string: String): Icon? =
			g_icon_new_for_string(string, null)?.let {
				ImplIcon(it)
			}

		fun deserialize(variant: Variant): Icon? =
			g_icon_deserialize(variant.variantPointer)?.let {
				ImplIcon(it)
			}


	}

}

class ImplIcon(override val pointer: CPointer<GIcon>) : Icon {

	companion object {
		 inline fun CPointer<GIcon>?.wrap() =
			this?.let { ImplIcon(it) }

		 inline fun CPointer<GIcon>.wrap() =
			ImplIcon(this)
	}
}