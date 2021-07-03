package nativex.gio

import gtk.GAppInfo
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 04 / 2021
 *
 * TODO GAppInfo
 */
class AppInfo(
	 val pointer: CPointer<GAppInfo>
) {

	companion object {
		 inline fun CPointer<GAppInfo>?.wrap() =
			this?.let { AppInfo(it) }

		 inline fun CPointer<GAppInfo>.wrap() =
			AppInfo(this)
	}
}