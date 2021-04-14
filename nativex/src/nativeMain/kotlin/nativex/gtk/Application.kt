package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.ClosableSequence
import nativex.asCloseableKSequence
import nativex.async.callbackSignalFlow
import nativex.gio.Application
import nativex.gio.Menu
import nativex.gio.Menu.Companion.wrap
import nativex.gio.MenuModel
import nativex.gio.MenuModel.Impl.Companion.wrap
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.Window.Companion.wrap

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html">GtkApplication</a>
 */
class Application internal constructor(
	val applicationPointer: CPointer<GtkApplication>
) : Application(applicationPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-windows">gtk_application_get_windows</a>
	 */
	val windows: Sequence<Window>
		get() = gtk_application_get_windows(applicationPointer)
			.asKSequence<GtkWindow, Window> { Window(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-app-menu">gtk_application_get_app_menu</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-set-app-menu">gtk_application_set_app_menu</a>
	 */
	var appMenu: MenuModel?
		get() = gtk_application_get_app_menu(applicationPointer).wrap()
		set(value) {
			gtk_application_set_app_menu(
				applicationPointer,
				value?.menuModelPointer
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-menubar">gtk_application_get_menubar</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-set-menubar">gtk_application_set_menubar</a>
	 */
	var menuBar: MenuModel?
		get() =
			gtk_application_get_menubar(applicationPointer).wrap()
		set(value) {
			gtk_application_set_menubar(
				applicationPointer,
				value?.menuModelPointer
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-active-window">gtk_application_get_active_window</a>
	 */
	val activeWindow: Window?
		get() = gtk_application_get_active_window(applicationPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-prefers-app-menu">gtk_application_prefers_app_menu</a>
	 */
	val prefersAppMenu: Boolean
		get() = gtk_application_prefers_app_menu(applicationPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-list-action-descriptions">gtk_application_list_action_descriptions</a>
	 */
	val actionDescriptions: ClosableSequence<String>
		get() = gtk_application_list_action_descriptions(
			applicationPointer
		)!!.asCloseableKSequence()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#GtkApplication-query-end">query-end</a>
	 */
	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val queryEndSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.QUERY_END)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#GtkApplication-window-added">window-added</a>
	 */
	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val windowAddedSignal: Flow<Window> by lazy {
		callbackSignalFlow(Signals.WINDOW_ADDED, staticWindowAddedCallback)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#GtkApplication-window-removed">window-removed</a>
	 */
	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val windowRemovedSignal: Flow<Window> by lazy {
		callbackSignalFlow(Signals.WINDOW_REMOVED, staticWindowRemovedCallback)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-new">gtk_application_new</a>
	 */
	constructor(
		applicationID: String,
		flags: Flags = Flags.NONE
	) : this(
		gtk_application_new(
			applicationID,
			flags.gtk
		)!!
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-window-by-id">gtk_application_get_window_by_id</a>
	 */
	@ExperimentalUnsignedTypes
	fun getWindowById(id: UInt): Window? =
		gtk_application_get_window_by_id(applicationPointer, id).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-inhibit">gtk_application_inhibit</a>
	 */
	@ExperimentalUnsignedTypes
	fun inhibit(window: Window, flags: InhibitFlags, reason: String): UInt =
		gtk_application_inhibit(
			applicationPointer,
			window.windowPointer,
			flags.gtk,
			reason
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-is-inhibited">gtk_application_is_inhibited</a>
	 */
	fun isInhibited(flags: InhibitFlags): Boolean =
		gtk_application_is_inhibited(applicationPointer, flags.gtk).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-uninhibit">gtk_application_uninhibit</a>
	 */
	@ExperimentalUnsignedTypes
	fun unInhibit(cookie: UInt) =
		gtk_application_uninhibit(applicationPointer, cookie)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-menu-by-id">gtk_application_get_menu_by_id</a>
	 */
	fun getMenuById(id: String): Menu =
		gtk_application_get_menu_by_id(applicationPointer, id)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-accels-for-action">gtk_application_get_accels_for_action</a>
	 */
	fun getAccelsForAction(detailedActionName: String): ClosableSequence<String> =
		gtk_application_get_accels_for_action(
			applicationPointer,
			detailedActionName
		)!!.asCloseableKSequence()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-set-accels-for-action">gtk_application_set_accels_for_action</a>
	 */
	fun setAccelsForAction(detailedActionName: String, accels: List<String>) =
		gtk_application_set_accels_for_action(
			applicationPointer,
			detailedActionName,
			accels.toNullTermCStringArray()
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-get-actions-for-accel">gtk_application_get_actions_for_accel</a>
	 */
	fun getActionsForAccel(accel: String): Sequence<String> =
		gtk_application_get_actions_for_accel(
			applicationPointer,
			accel
		).asKSequence()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-add-window">gtk_application_add_window</a>
	 */
	fun addWindow(window: Window) =
		gtk_application_add_window(applicationPointer, window.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#gtk-application-remove-window">gtk_application_remove_window</a>
	 */
	fun removeWindow(window: Window) =
		gtk_application_remove_window(applicationPointer, window.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkApplication.html#GtkApplicationInhibitFlags">GtkApplicationInhibitFlags</a>
	 */
	enum class InhibitFlags(val key: Int, val gtk: GtkApplicationInhibitFlags) {
		LOGOUT(0, GTK_APPLICATION_INHIBIT_LOGOUT),
		SWITCH(1, GTK_APPLICATION_INHIBIT_SWITCH),
		SUSPEND(2, GTK_APPLICATION_INHIBIT_SUSPEND),
		IDLE(3, GTK_APPLICATION_INHIBIT_IDLE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GtkApplicationInhibitFlags) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		internal val staticWindowAddedCallback: GCallback =
			staticCFunction { _: gpointer?, window: CPointer<GtkWindow>, data: gpointer? ->
				data?.asStableRef<(Window) -> Unit>()
					?.get()
					?.invoke(Window(window))
				Unit
			}.reinterpret()

		internal val staticWindowRemovedCallback: GCallback =
			staticCFunction { _: gpointer?, window: CPointer<GtkWindow>, data: gpointer? ->
				data?.asStableRef<(Window) -> Unit>()
					?.get()
					?.invoke(Window(window))
				Unit
			}.reinterpret()
	}
}