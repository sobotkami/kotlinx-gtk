package nativex.gio

import gio.GApplicationCommandLine
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 06 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gio/stable/GApplicationCommandLine.html">GApplicationCommandLine</a>
 */
class ApplicationCommandLine(val applicationCommandLinePointer: CPointer<GApplicationCommandLine>) :
	KGObject(applicationCommandLinePointer.reinterpret()) {

	companion object {
		inline fun CPointer<GApplicationCommandLine>?.wrap() =
			this?.wrap()

		inline fun CPointer<GApplicationCommandLine>.wrap() =
			ApplicationCommandLine(this)
	}
}