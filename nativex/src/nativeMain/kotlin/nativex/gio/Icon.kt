package nativex.gio

import gtk.*
import kotlinx.cinterop.toKString
import nativex.PointerHolder
import nativex.glib.Variant
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 28 / 03 / 2021
 */
interface Icon {
	val pointer: PointerHolder<GIcon>

	@ExperimentalUnsignedTypes
	val hash: UInt
		get() = g_icon_hash(pointer.ptr)

	fun equal(other: Icon): Boolean =
		g_icon_equal(pointer.ptr, other.pointer.ptr).bool

	fun iconToString(): String? =
		g_icon_to_string(pointer.ptr)?.toKString()

	fun serialize(): Variant? =
		g_icon_serialize(pointer.ptr)?.let { Variant(it) }

	companion object {
		fun newForString(string: String): Icon? =
			g_icon_new_for_string(string, null)?.let {
				ImplIcon(PointerHolder(it))
			}

		fun deserialize(variant: Variant): Icon? =
			g_icon_deserialize(variant.variantPointer)?.let {
				ImplIcon(PointerHolder(it))
			}


	}

}

internal class ImplIcon(override val pointer: PointerHolder<GIcon>) : Icon