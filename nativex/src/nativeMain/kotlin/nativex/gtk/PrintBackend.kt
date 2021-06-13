package nativex.gtk
/*
import gtk.GtkPrintBackend
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 *
 * @see <a href=""></a>
 */
class PrintBackend internal constructor(
	internal val printBackendPointer: CPointer<GtkPrintBackend>
) : KObject(printBackendPointer.reinterpret()) {

	companion object{
		internal inline fun CPointer<GtkPrintBackend>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkPrintBackend>.wrap() =
			PrintBackend(this)
	}
}

 */