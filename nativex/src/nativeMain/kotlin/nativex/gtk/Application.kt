package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.g.MenuModel
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Application internal constructor(
	val applicationPointer: CPointer<GtkApplication>
) : nativex.g.Application(applicationPointer.reinterpret()) {
	constructor(
		applicationID: String,
		flags: Flags = Flags.NONE
	) : this(
		gtk_application_new(
			applicationID,
			flags.gtk
		)!!
	)

	val windows: Sequence<Window>
		get() = gtk_application_get_windows(applicationPointer)
			.asKSequence<GtkWindow, Window> { Window(it) }

	var menuBar: MenuModel? = null
		get() =
			gtk_application_get_app_menu(applicationPointer)?.let {
				MenuModel(it)
			}
		set(value) {
			gtk_application_set_menubar(applicationPointer, value?.cPointer)
			field = value
		}

	val activeWindow: Window
		get() = Window(gtk_application_get_active_window(applicationPointer)!!.reinterpret())

	var isRegisterSession: Boolean
		get() = TODO("Not yet implemented")
		set(_) {}

	var isScreenSaverActive: Boolean
		get() = TODO("Not yet implemented")
		set(_) {}

	fun getWindowById(): Window? {
		TODO("Not yet implemented")
	}

	fun getActiveWindow(): Window {
		TODO("Not yet implemented")
	}

	fun inhibit(): Int {
		TODO("Not yet implemented")
	}

	fun unInhibit() {
		TODO("Not yet implemented")
	}

	fun getMenuById() {
		TODO("Not yet implemented")
	}

	fun listActionDescriptions(): String {
		TODO("Not yet implemented")
	}

	fun getAccelsForAction(): String {
		TODO("Not yet implemented")
	}

	fun setAccelsForAction() {
		TODO("Not yet implemented")
	}

	fun getActionsForAccel(): String {
		TODO("Not yet implemented")
	}

	fun addWindow(window: Window) {
		gtk_application_add_window(applicationPointer,window.windowPointer)
	}

	fun removeWindow(window: Window){
		gtk_application_remove_window(applicationPointer,window.windowPointer)
	}

	@ExperimentalUnsignedTypes
	fun onActivate(onActive: () -> Unit) {
		// Has to be a direct event, to prevent application from shutting down
		applicationPointer.connectSignal(
			Signals.ACTIVATE,
			handler = staticNoArgGCallback,
			callbackWrapper = StableRef.create {
				onActive()
			}.asCPointer()
		)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val queryEndSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.QUERY_END)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val windowAddedSignal: Flow<Window> by lazy {
		callbackSignalFlow(Signals.WINDOW_ADDED, staticWindowAddedCallback)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val windowRemovedSignal: Flow<Window> by lazy {
		callbackSignalFlow(Signals.WINDOW_REMOVED, staticWindowRemovedCallback)
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