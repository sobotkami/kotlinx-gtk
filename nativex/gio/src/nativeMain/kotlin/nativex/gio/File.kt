package nativex.gio

import gio.GFile
import gio.g_file_load_contents
import glib.GError
import glib.g_free
import kotlinx.cinterop.*
import nativex.glib.CString
import nativex.glib.KGError
import nativex.glib.bool
import nativex.glib.unwrap

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File(
	val pointer: CPointer<GFile>
) {

	@Throws(KGError::class)
	fun loadContents(cancellable: KCancellable? = null): String? {
		memScoped {
			val contents: CValue<CPointerVarOf<CString>> = cValue()
			val cLength = cValue<ULongVar>()
			val err = allocPointerTo<GError>().ptr

			val r = g_file_load_contents(
				pointer,
				cancellable?.cancellablePointer,
				contents,
				cLength,
				null,
				err
			).bool
			err.unwrap()
			return if (r) {
				val builder = StringBuilder()
				for (index in 0 until cLength.ptr.pointed.value.toInt())
					builder.append(contents.ptr[index]?.toKString()).append("\n")
				g_free(contents.ptr)
				builder.toString()
			} else null
		}
	}

	companion object {

		fun Array<File>.toCArray(): CPointer<CPointerVar<GFile>> =
			memScoped {
				allocArrayOf(this@toCArray.map { it.pointer })
			}
	}
}