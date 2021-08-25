package org.gtk.gio

import gio.GFile
import gio.g_file_load_contents
import glib.GError
import glib.g_free
import kotlinx.cinterop.*
import org.gtk.glib.CStringPointer
import org.gtk.glib.KGError
import org.gtk.glib.bool
import org.gtk.glib.unwrap
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File(
	val filePointer: CPointer<GFile>
) : KGObject(filePointer.reinterpret()) {

	@Throws(KGError::class)
	fun loadContents(cancellable: KGCancellable? = null): String? {
		memScoped {
			val contents: CValue<CPointerVarOf<CStringPointer>> = cValue()
			val cLength = cValue<ULongVar>()
			val err = allocPointerTo<GError>().ptr

			val r = g_file_load_contents(
				filePointer,
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
				allocArrayOf(this@toCArray.map { it.filePointer })
			}
	}
}