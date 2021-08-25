package nativex.pango

import glib.gucharVar
import kotlinx.cinterop.*
import nativex.gobject.KGObject
import pango.*

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/Pango/class.Coverage.html">PangoCoverage</a>
 */
class Coverage(val coveragePointer: CPointer<PangoCoverage>) : KGObject(coveragePointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/Pango/ctor.Coverage.new.html">pango_coverage_new</a>
	 */
	constructor() : this(pango_coverage_new()!!)

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.copy.html">pango_coverage_copy</a>
	 */
	fun copy(): Coverage = pango_coverage_copy(coveragePointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.get.html">pango_coverage_get</a>
	 */
	operator fun get(index: Int): Level =
		Level.valueOf(pango_coverage_get(coveragePointer, index))!!

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.max.html">pango_coverage_max</a>
	 */
	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Since 1.44, This function does nothing.")
	fun max(other: Coverage) {
		pango_coverage_max(coveragePointer, other.coveragePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.ref.html">pango_coverage_ref</a>
	 */
	fun ref() {
		pango_coverage_ref(coveragePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.set.html">pango_coverage_set</a>
	 */
	operator fun set(index: Int, level: Level) {
		pango_coverage_set(coveragePointer, index, level.pango)
	}

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.to_bytes.html">pango_coverage_to_bytes</a>
	 */
	@ExperimentalUnsignedTypes
	@Suppress("DeprecatedCallableAddReplaceWith")
	@Deprecated("Since 1.44, This function does nothing.")
	fun toBytes() =
		memScoped {
			val cByteArray = cValue<CPointerVar<gucharVar>>()
			val nBytes = cValue<IntVar>()

			pango_coverage_to_bytes(coveragePointer, cByteArray, nBytes)

			val size = nBytes.ptr.pointed.value

			Array(size) { index ->
				cByteArray.ptr[index]!!.pointed.value
			}
		}

	/**
	 * @see <a href="https://docs.gtk.org/Pango/method.Coverage.unref.html">pango_coverage_unref</a>
	 */
	override fun unref() {
		pango_coverage_unref(coveragePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/Pango/enum.CoverageLevel.html">PangoCoverageLevel</a>
	 */
	enum class Level(val pango: PangoCoverageLevel) {
		/**
		 * The character is not representable with the font.
		 */
		NONE(PangoCoverageLevel.PANGO_COVERAGE_NONE),

		/**
		 * The character is represented in a way that may be comprehensible but is not the correct graphical form.
		 *
		 * For instance,
		 * a Hangul character represented as a a sequence of Jamos,
		 * or a Latin transliteration of a Cyrillic word.
		 */
		FALLBACK(PangoCoverageLevel.PANGO_COVERAGE_FALLBACK),

		/**
		 * The character is represented as basically the correct graphical form,
		 * but with a stylistic variant inappropriate for the current script.
		 */
		APPROXIMATE(PangoCoverageLevel.PANGO_COVERAGE_APPROXIMATE),

		/**
		 * The character is represented as the correct graphical form.
		 */
		EXACT(PangoCoverageLevel.PANGO_COVERAGE_EXACT);

		companion object {
			inline fun valueOf(pango: PangoCoverageLevel) = values().find { it.pango == pango }
		}
	}

	companion object {

		/**
		 * @see <a href="https://docs.gtk.org/Pango/type_func.Coverage.from_bytes.html">pango_coverage_from_bytes</a>
		 */
		@ExperimentalUnsignedTypes
		@Suppress("DeprecatedCallableAddReplaceWith")
		@Deprecated("Since 1.44, This returns null.")
		fun fromBytes(bytes: Array<UByte>): Coverage? =
			memScoped {
				pango_coverage_from_bytes(
					allocArray(bytes.size) { index ->
						value = bytes[index]
					}, bytes.size
				).wrap()
			}


		inline fun CPointer<PangoCoverage>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoCoverage>.wrap() =
			Coverage(this)
	}
}