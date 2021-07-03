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
class PrintBackend(
	 val printBackendPointer: CPointer<GtkPrintBackend>
) : KObject(printBackendPointer.reinterpret()) {

	companion object{
		 inline fun CPointer<GtkPrintBackend>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkPrintBackend>.wrap() =
			PrintBackend(this)
	}
}

 */