import kotlinx.cinterop.*
import nativex.glib.KList
import nativex.glib.get
import nativex.glib.plus
import kotlin.test.Test

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href=""></a>
 */

@Test
fun KListAppendTest() {
	memScoped {
		val stringList = KList()

		val string = "second"
		val stringPtr = string.cstr.ptr
		stringList + stringPtr

		assert(stringList.length == 2u) { "Length did not match" }

		assert(
			stringList[1u]?.reinterpret<ByteVarOf<Byte>>()?.toKString() ==
					"second"
		) {
			"Index 1 string matches expected"
		}

		assert(
			stringList[1u] == stringPtr
		) {
			"Pointers did not match"
		}
	}
}