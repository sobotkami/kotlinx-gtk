package nativex.glib

import glib.*
import kotlinx.cinterop.*
import platform.posix.va_list

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
open class Variant(
	val variantPointer: CPointer<GVariant>
) : Comparable<Variant> {

	val type: VariantType
		get() = VariantType.OpenVariant(g_variant_get_type(variantPointer)!!)

	val typeString: String
		get() = g_variant_get_type_string(variantPointer)!!.toKString()

	override fun compareTo(other: Variant): Int =
		g_variant_compare(variantPointer, other.variantPointer)

	override fun equals(other: Any?): Boolean {
		if (other is VariantType)
			g_variant_is_of_type(variantPointer, other.variantTypePointer).bool

		return false
	}

	val isContainer: Boolean
		get() = g_variant_is_container(variantPointer).bool


	fun classify(): UInt =
		g_variant_classify(variantPointer)

	val string: String
		get() = memScoped {
			val cLength = cValue<ULongVar>()
			val cString: CString = g_variant_get_string(variantPointer, cLength)!!
			val length = cLength.ptr.pointed.value.toInt()
			val stringBuilder = StringBuilder()
			for (index in 0 until length) {
				stringBuilder.append(cString[index])
			}
			stringBuilder.toString()
		}

	/**
	 * @see <a href="https://developer.gnome.org/glib/stable/glib-GVariant.html#g-variant-get-va">
	 *     g_variant_get_va</a>
	 */
	fun get(format: String, vararg args: String) {
		val v: va_list = args.toList().toNullTermCStringArray()
			.reinterpret()
		g_variant_get_va(
			variantPointer,
			format,
			null,
			null, //TODO Solve va_list issue
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/stable/glib-GVariant.html#g-variant-new-va">
	 *     g_variant_new_va</a>
	 */
	constructor(format: String, vararg args: Any) : this(
		g_variant_new_va(
			format,
			null,
			null
		)!!
	) {
		TODO("g_variant_new_va")
	}

	class BooleanVariant(value: Boolean) :
		Variant(g_variant_new_boolean(value.gtk)!!)

	class ByteVariant constructor(value: UByte) :
		Variant(g_variant_new_byte(value)!!)

	class ShortVariant(value: Short) :
		Variant(g_variant_new_int16(value)!!)

	class UShortVariant constructor(value: UShort) :
		Variant(g_variant_new_uint16(value)!!)

	class IntVariant(value: Int) :
		Variant(g_variant_new_int32(value)!!)

	class UIntVariant constructor(value: UInt) :
		Variant(g_variant_new_uint32(value)!!)

	class LongVariant(value: Long) :
		Variant(g_variant_new_int64(value)!!)

	class ULongVariant constructor(value: ULong) :
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

	interface Dict {
		val variantDictPointer: CPointer<GVariantDict>

		class Impl(override val variantDictPointer: CPointer<GVariantDict>) : Dict
	}

	companion object {
		fun isObjectPath(string: String): Boolean =
			g_variant_is_object_path(string).bool

		val String.isObjectPath: Boolean
			get() = isObjectPath(this)

		fun isSignature(string: String): Boolean =
			g_variant_is_signature(string).bool

		val String.isSignature: Boolean
			get() = isSignature(this)

		inline fun CPointer<GVariant>?.wrap() =
			this?.let { Variant(it) }

		inline fun CPointer<GVariant>.wrap() =
			Variant(this)
	}

}