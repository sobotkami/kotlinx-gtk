import kotlinx.cinterop.*
import nativex.glib.KList
import nativex.glib.WrappedKList
import nativex.glib.WrappedStringKList
import kotlin.test.Test

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href=""></a>
 */

@Test
fun WrappedKListStringTest() {
	memScoped {
		val stringList = WrappedKList(KList(), { reinterpret<ByteVarOf<Byte>>().toKString() }, { cstr.ptr })

		stringList.append("second")

		assert(stringList.length == 2u) { "Length did not match" }

		val firstIndexData = stringList.getData(1u)

		assert(
			firstIndexData == "second"
		) {
			"Index 1 string does not match expected, Received: $firstIndexData"
		}
	}
}

@Test
fun WrappedStringKListTest() {
	memScoped {
		val stringList = WrappedStringKList(this)

		stringList.append("second")

		assert(stringList.length == 2u) { "Length did not match" }

		val firstIndexData = stringList.getData(1u)

		assert(
			firstIndexData == "second"
		) {
			"Index 1 string does not match expected, Received: $firstIndexData"
		}
	}
}