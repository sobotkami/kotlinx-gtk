package com.github.doomsdayrs.lib.kotlinx.gtk.gtk

import com.github.doomsdayrs.lib.kotlinx.gtk.g.GMenuModel
import kotlinx.cinterop.CPointer
import kotlinx.coroutines.flow.Flow
import x11.Display
import x11.XOpenDisplay

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
actual class GtkApplication {


	actual val windows: MutableList<GtkWindow>
		get() = TODO("Not yet implemented")
	actual var menuBar: GMenuModel
		get() = TODO("Not yet implemented")
		set(value) {}
	actual val activeWindow: GtkWindow
		get() = TODO("Not yet implemented")
	actual var isRegisterSession: Boolean
		get() = TODO("Not yet implemented")
		set(value) {}
	actual var isScreenSaverActive: Boolean
		get() = TODO("Not yet implemented")
		set(value) {}

	actual fun getWindowById(): GtkWindow? {

		TODO("Not yet implemented")
	}

	actual fun getActiveWindow(): GtkWindow {
		TODO("Not yet implemented")
	}

	actual fun inhibit(): Int {
		TODO("Not yet implemented")
	}

	actual fun unInhibit() {
		TODO("Not yet implemented")
	}

	actual fun getMenuById() {
		TODO("Not yet implemented")
	}

	actual fun listActionDescriptions(): String {
		TODO("Not yet implemented")
	}

	actual fun getAccelsForAction(): String {
		TODO("Not yet implemented")
	}

	actual fun setAccelsForAction() {
		TODO("Not yet implemented")
	}

	actual fun getActionsForAccel(): String {
		TODO("Not yet implemented")
	}

	actual val queryEndSignal: Flow<*>
		get() = TODO("Not yet implemented")
	actual val windowAddedSignal: Flow<*>
		get() = TODO("Not yet implemented")
	actual val windowRemovedSignal: Flow<*>
		get() = TODO("Not yet implemented")

}