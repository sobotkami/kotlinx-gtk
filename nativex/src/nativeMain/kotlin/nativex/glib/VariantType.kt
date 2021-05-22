package nativex.glib

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
sealed class VariantType(
	internal val variantTypePointer: CPointer<GVariantType>
) {
	class OpenVariant internal constructor(variantTypePointer: CPointer<GVariantType>) :
		VariantType(variantTypePointer) {
		constructor(type: String) : this(g_variant_type_new(type)!!)

	}

	object BooleanType : VariantType(G_VARIANT_TYPE_BOOLEAN!!)
	object ByteType : VariantType(G_VARIANT_TYPE_BYTE!!)
	object ShortType : VariantType(G_VARIANT_TYPE_INT16!!)
	object UShortType : VariantType(G_VARIANT_TYPE_UINT16!!)
	object IntType : VariantType(G_VARIANT_TYPE_INT32!!)
	object UIntType : VariantType(G_VARIANT_TYPE_UINT32!!)
	object LongType : VariantType(G_VARIANT_TYPE_INT64!!)
	object ULongType : VariantType(G_VARIANT_TYPE_UINT64!!)
	object HandleType : VariantType(G_VARIANT_TYPE_HANDLE!!)
	object DoubleType : VariantType(G_VARIANT_TYPE_DOUBLE!!)
	object StringType : VariantType(G_VARIANT_TYPE_STRING!!)
	object ObjectPathType : VariantType(G_VARIANT_TYPE_OBJECT_PATH!!)
	object SignatureType : VariantType(G_VARIANT_TYPE_SIGNATURE!!)
	object BoxedVariantType : VariantType(G_VARIANT_TYPE_VARIANT!!)
	object AnyType : VariantType(G_VARIANT_TYPE_ANY!!)
	object BasicType : VariantType(G_VARIANT_TYPE_BASIC!!)
	object MaybeType : VariantType(G_VARIANT_TYPE_MAYBE!!)
	object ArrayType : VariantType(G_VARIANT_TYPE_ARRAY!!)
	object TupleType : VariantType(G_VARIANT_TYPE_TUPLE!!)
	object UnitType : VariantType(G_VARIANT_TYPE_UNIT!!)
	object DictEntryType : VariantType(G_VARIANT_TYPE_DICT_ENTRY!!)
	object DictionaryType : VariantType(G_VARIANT_TYPE_DICTIONARY!!)

	object StringArrayType : VariantType(G_VARIANT_TYPE_STRING_ARRAY!!)
	object ObjectPathArrayType : VariantType(G_VARIANT_TYPE_OBJECT_PATH_ARRAY!!)
	object ByteStringType : VariantType(G_VARIANT_TYPE_BYTESTRING!!)
	object ByteStringArrayType : VariantType(G_VARIANT_TYPE_BYTESTRING_ARRAY!!)
	object VardictType : VariantType(G_VARIANT_TYPE_VARDICT!!)

	
	val stringLength: ULong
		get() = g_variant_type_get_string_length(variantTypePointer)

	// todo g_variant_type_peek_string

	val string: String
		get() = g_variant_type_dup_string(variantTypePointer)!!.toKString()

	val isDefinite: Boolean
		get() = g_variant_type_is_definite(variantTypePointer).bool

	val isContainer: Boolean
		get() = g_variant_type_is_container(variantTypePointer).bool

	val isBasic: Boolean
		get() = g_variant_type_is_basic(variantTypePointer).bool

	val isMaybe: Boolean
		get() = g_variant_type_is_maybe(variantTypePointer).bool

	val isArray: Boolean
		get() = g_variant_type_is_array(variantTypePointer).bool

	val isTuple: Boolean
		get() = g_variant_type_is_tuple(variantTypePointer).bool

	val isDictEntry: Boolean
		get() = g_variant_type_is_dict_entry(variantTypePointer).bool

	val isVariant: Boolean
		get() = g_variant_type_is_variant(variantTypePointer).bool

	
	override fun hashCode(): Int =
		g_variant_type_hash(variantTypePointer).toInt()

	override fun equals(other: Any?): Boolean {
		if (other == null) return false
		if (other is VariantType)
			return g_variant_type_equal(
				variantTypePointer,
				other.variantTypePointer
			).bool

		return super.equals(other)
	}

	fun isSubtypeOf(other: VariantType) =
		g_variant_type_is_subtype_of(
			variantTypePointer,
			other.variantTypePointer
		)


	companion object {
		fun isValid(string: String): Boolean =
			g_variant_type_string_is_valid(string).bool

		val String.isValidVariantType: Boolean
			get() = isValid(this)

		fun scanString(string: String, limit: String? = null): Boolean =
			g_variant_type_string_scan(string, limit, null).bool

		internal inline fun CPointer<GVariantType>?.wrap(): VariantType? =
			this?.let { OpenVariant(it) }

		internal inline fun CPointer<GVariantType>.wrap(): VariantType =
			OpenVariant(this)
	}

}