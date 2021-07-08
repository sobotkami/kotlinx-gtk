package nativex.gio

import gio.GFile
import kotlinx.cinterop.*
import nativex.glib.CString

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File(
	val pointer: CPointer<GFile>
) {

	fun loadContents(cancellable: KCancellable?) {
		memScoped {
			val contents = cValue<CPointerVarOf<CString>>()
			val cLength = cValue<ULongVar>()
			// TODO finish
//			g_file_load_contents(pointer, cancellable?.cancellablePointer,contents,cLength,)
		}
	}

	companion object {

		fun Array<File>.toCArray(): CPointer<CPointerVar<GFile>> =
			memScoped {
				allocArrayOf(this@toCArray.map { it.pointer })
			}
	}
}