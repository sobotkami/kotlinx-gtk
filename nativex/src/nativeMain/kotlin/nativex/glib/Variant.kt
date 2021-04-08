package nativex.glib

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCStringArray
import kotlinx.cinterop.toKString
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
open class Variant internal constructor(
	internal val variantPointer: CPointer<GVariant>
) {

	val type: VariantType
		get() = VariantType.OpenVariant(g_variant_get_type(variantPointer)!!)

	val typeString: String
		get() = g_variant_get_type_string(variantPointer)!!.toKString()


	override operator fun equals(other: Any?): Boolean {
		if (other is Variant)
			return g_variant_compare(variantPointer, other.variantPointer).bool

		if (other is VariantType)
			g_variant_is_of_type(variantPointer, other.variantTypePointer).bool

		return false
	}

	val isContainer: Boolean
		get() = g_variant_is_container(variantPointer).bool

	@ExperimentalUnsignedTypes
	fun classify(): UInt =
		g_variant_classify(variantPointer)

	/**
	 * @see <a href="https://developer.gnome.org/glib/stable/glib-GVariant.html#g-variant-get-va">
	 *     g_variant_get_va</a>
	 */
	fun get(formatString: String, vararg args: Any) {
		TODO("g_variant_get_va")
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/stable/glib-GVariant.html#g-variant-new-va">
	 *     g_variant_new_va</a>
	 */
	constructor(formatString: String, vararg args: Any) : this(
		g_variant_new_va(
			formatString,
			null,
			null
		)!!
	) {
		TODO("g_variant_new_va")
	}

	class BooleanVariant(value: Boolean) :
		Variant(g_variant_new_boolean(value.gtk)!!)

	class ByteVariant @ExperimentalUnsignedTypes constructor(value: UByte) :
		Variant(g_variant_new_byte(value)!!)

	class ShortVariant(value: Short) :
		Variant(g_variant_new_int16(value)!!)

	class UShortVariant @ExperimentalUnsignedTypes constructor(value: UShort) :
		Variant(g_variant_new_uint16(value)!!)

	class IntVariant(value: Int) :
		Variant(g_variant_new_int32(value)!!)

	class UIntVariant @ExperimentalUnsignedTypes constructor(value: UInt) :
		Variant(g_variant_new_uint32(value)!!)

	class LongVariant(value: Long) :
		Variant(g_variant_new_int64(value)!!)

	class ULongVariant @ExperimentalUnsignedTypes constructor(value: ULong) :
		Variant(g_variant_new_uint64(value)!!)

	class HandleVariant(value: Int) :
		Variant(g_variant_new_handle(value)!!)

	class StringVariant(value: String) :
		Variant(g_variant_new_string(value)!!)

	class TakeStringVariant(value: String) :
		Variant(g_variant_new_string(value)!!)

	class ObjectPathVariant(value: String) :
		Variant(g_variant_new_object_path(value)!!)

	class SignatureVariant(value: String) :
		Variant(g_variant_new_signature(value)!!)

	class VariantVariant(variant: Variant) :
		Variant(g_variant_new_variant(variant.variantPointer)!!)

	class StrvVariant(strv: Array<String>) :
		Variant(g_variant_new_strv(memScoped {
			strv.toCStringArray(this)
		}, strv.size.toLong())!!)

	class ObjvVariant(strv: Array<String>) :
		Variant(g_variant_new_strv(memScoped {
			strv.toCStringArray(this)
		}, strv.size.toLong())!!)

	class ByteStringVariant(value: String) :
		Variant(g_variant_new_signature(value)!!)

	class ByteStringArrayVariant(strv: Array<String>) :
		Variant(g_variant_new_bytestring_array(memScoped {
			strv.toCStringArray(this)
		}, strv.size.toLong())!!)

	companion object {
		fun isObjectPath(string: String): Boolean =
			g_variant_is_object_path(string).bool

		val String.isObjectPath: Boolean
			get() = isObjectPath(this)

		fun isSignature(string: String): Boolean =
			g_variant_is_signature(string).bool

		val String.isSignature: Boolean
			get() = isSignature(this)
	}
}