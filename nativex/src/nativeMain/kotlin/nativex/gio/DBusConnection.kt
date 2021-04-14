package nativex.gio

import gtk.GDBusConnection
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
class DBusConnection internal constructor(
	internal val dBussConnectionPointer: CPointer<GDBusConnection>
) : KObject(dBussConnectionPointer.reinterpret()) {

	companion object {
		internal inline fun CPointer<GDBusConnection>.wrap() =
			DBusConnection(this)

		internal inline fun CPointer<GDBusConnection>?.wrap() =
			this?.let { DBusConnection(it) }
	}
}