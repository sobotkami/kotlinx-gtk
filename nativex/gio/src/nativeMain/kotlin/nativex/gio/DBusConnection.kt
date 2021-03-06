package nativex.gio

import gio.GDBusConnection
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
class DBusConnection(
	 val dBussConnectionPointer: CPointer<GDBusConnection>
) : KObject(dBussConnectionPointer.reinterpret()) {

	companion object {
		 inline fun CPointer<GDBusConnection>.wrap() =
			DBusConnection(this)

		 inline fun CPointer<GDBusConnection>?.wrap() =
			this?.let { DBusConnection(it) }
	}
}