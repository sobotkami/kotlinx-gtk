package nativex.gio

import gtk.GAppInfo
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 04 / 2021
 *
 * TODO GAppInfo
 */
class AppInfo internal constructor(
	internal val pointer: CPointer<GAppInfo>
) {

	companion object {
		internal inline fun CPointer<GAppInfo>?.wrap() =
			this?.let { AppInfo(it) }

		internal inline fun CPointer<GAppInfo>.wrap() =
			AppInfo(this)
	}
}