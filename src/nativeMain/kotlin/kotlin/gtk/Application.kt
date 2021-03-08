package kotlin.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.flow.Flow
import kotlin.g.Application
import kotlin.gtk.g.MenuModel

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Application internal constructor(
	val pointer: CPointer<GtkApplication>
) : Application(pointer.reinterpret()) {
	constructor(
		applicationID: String,
		flags: GApplicationFlags = G_APPLICATION_FLAGS_NONE
	) : this(
		gtk_application_new(
			applicationID,
			flags
		)!!
	)

	val windows: List<Window>
		get() = gtk_application_get_windows(pointer)
			.asSequence<GtkWindow>()
			.toList().map {
				Window(it.reinterpret())
			}


	var menuBar: MenuModel? = null
		get() =
			gtk_application_get_app_menu(pointer)?.let {
				MenuModel(it)
			}
		set(value) {
			gtk_application_set_menubar(pointer, value?.cPointer)
			field = value
		}

	val activeWindow: Window
		get() = Window(gtk_application_get_active_window(pointer)!!.reinterpret())

	var isRegisterSession: Boolean
		get() = TODO("Not yet implemented")
		set(value) {}

	var isScreenSaverActive: Boolean
		get() = TODO("Not yet implemented")
		set(value) {}

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

	fun onActivate(onActive: () -> Unit) {
		pointer.connectSignal(
			Signals.ACTIVATE,
			handler = staticCallback,
			callbackWrapper = StableRef.create {
				onActive()
			}.asCPointer()
		)
	}

	val queryEndSignal: Flow<*>
		get() = TODO("Not yet implemented")

	val windowAddedSignal: Flow<*>
		get() = TODO("Not yet implemented")


	val windowRemovedSignal: Flow<*>
		get() = TODO("Not yet implemented")

}