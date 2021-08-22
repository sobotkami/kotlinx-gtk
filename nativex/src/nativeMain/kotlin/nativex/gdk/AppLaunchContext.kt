package nativex.gdk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Display.Companion.wrap
import nativex.gio.Icon

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gdk4/class.AppLaunchContext.html">
 *     GdkAppLaunchContext</a>
 */
class AppLaunchContext(val appLaunchContext: CPointer<GdkAppLaunchContext>) :
	nativex.gio.AppLaunchContext(appLaunchContext.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.AppLaunchContext.get_display.html">
	 *     gdk_app_launch_context_get_display</a>
	 */
	val display: Display
		get() = gdk_app_launch_context_get_display(appLaunchContext)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.AppLaunchContext.set_desktop.html">
	 *     gdk_app_launch_context_set_desktop</a>
	 */
	fun setDesktop(desktop: Int) {
		gdk_app_launch_context_set_desktop(appLaunchContext, desktop)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.AppLaunchContext.set_icon.html">
	 *     gdk_app_launch_context_set_icon</a>
	 */
	fun setIcon(icon: Icon) {
		gdk_app_launch_context_set_icon(appLaunchContext, icon.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.AppLaunchContext.set_icon_name.html">
	 *     gdk_app_launch_context_set_icon_name</a>
	 */
	fun setIconName(name: String) {
		gdk_app_launch_context_set_icon_name(appLaunchContext, name)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.AppLaunchContext.set_timestamp.html">
	 *     gdk_app_launch_context_set_timestamp</a>
	 */
	fun setTimestamp(timestamp: UInt) {
		gdk_app_launch_context_set_timestamp(appLaunchContext, timestamp)
	}

	companion object{
		inline fun CPointer<GdkAppLaunchContext>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkAppLaunchContext>.wrap() =
			AppLaunchContext(this)
	}
}