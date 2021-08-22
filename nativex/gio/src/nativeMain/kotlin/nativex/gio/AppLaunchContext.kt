package nativex.gio

import gio.*
import glib.GVariant
import glib.gcharVar
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.*
import nativex.gio.AppInfo.Companion.wrap
import nativex.glib.Variant
import nativex.glib.Variant.Companion.wrap
import nativex.glib.asKSequence
import nativex.gobject.KGObject
import nativex.gobject.SignalManager
import nativex.gobject.Signals.LAUNCHED
import nativex.gobject.Signals.LAUNCH_FAILED
import nativex.gobject.addSignalCallback
import toGList

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gio/class.AppLaunchContext.html">GAppLaunchContext</a>
 */
open class AppLaunchContext(val gAppLaunchContext: CPointer<GAppLaunchContext>) :
	KGObject(gAppLaunchContext.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gio/ctor.AppLaunchContext.new.html">g_app_launch_context_new</a>
	 */
	constructor() : this(g_app_launch_context_new()!!)

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.get_display.html">
	 *     g_app_launch_context_get_display</a>
	 */
	fun getDisplay(info: AppInfo, files: List<File>): String? =
		memScoped {
			g_app_launch_context_get_display(
				gAppLaunchContext,
				info.pointer,
				files.toGList().listPointer
			)?.toKString()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.get_environment.html">
	 *     g_app_launch_context_get_environment</a>
	 */
	val environment: List<String>
		get() = g_app_launch_context_get_environment(gAppLaunchContext).asKSequence().toList()

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.get_startup_notify_id.html">
	 *     g_app_launch_context_get_startup_notify_id</a>
	 */
	fun getStartupNotifyId(info: AppInfo, files: List<File>): String? =
		memScoped {
			g_app_launch_context_get_startup_notify_id(
				gAppLaunchContext,
				info.pointer,
				files.toGList().listPointer
			)?.toKString()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.launch_failed.html">
	 *     g_app_launch_context_launch_failed</a>
	 */
	fun launchFailed(startupNotifyId: String) {
		g_app_launch_context_launch_failed(gAppLaunchContext, startupNotifyId)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.setenv.html">
	 *     g_app_launch_context_setenv</a>
	 */
	fun setenv(variable: String, value: String) {
		g_app_launch_context_setenv(gAppLaunchContext, variable, value)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gio/method.AppLaunchContext.unsetenv.html">
	 *     g_app_launch_context_unsetenv</a>
	 */
	fun unsetenv(variable: String) {
		g_app_launch_context_unsetenv(gAppLaunchContext, variable)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gio/signal.AppLaunchContext.launch-failed.html">launch-failed</a>
	 */
	fun addOnLaunchFailedCallback(action: LaunchFailedFunction): SignalManager =
		addSignalCallback(LAUNCH_FAILED, action, staticLaunchFailedFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gio/signal.AppLaunchContext.launched.html">launched</a>
	 */
	fun addOnLaunchedCallback(action: LaunchFunction): SignalManager =
		addSignalCallback(LAUNCHED, action, staticLaunchedFunction)

	companion object {
		private val staticLaunchFailedFunction: GCallback =
			staticCFunction { _: gpointer?, startupNotifyId: CPointer<gcharVar>, data: gpointer? ->
				data?.asStableRef<LaunchFailedFunction>()?.get()?.invoke(startupNotifyId.toKString())
				Unit
			}.reinterpret()

		private val staticLaunchedFunction: GCallback =
			staticCFunction { _: gpointer?, info: CPointer<GAppInfo>, platformData: CPointer<GVariant>, data: gpointer? ->
				data?.asStableRef<LaunchFunction>()?.get()?.invoke(info.wrap(), platformData.wrap())
				Unit
			}.reinterpret()

	}

}

typealias LaunchFailedFunction = (@ParameterName("startupNotifyId") String) -> Unit

typealias LaunchFunction = (@ParameterName("info") AppInfo, @ParameterName("platformData") Variant) -> Unit