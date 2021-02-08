package com.github.doomsdayrs.lib.kotlinx.gtk.gtk

import com.github.doomsdayrs.lib.kotlinx.gtk.g.GMenuModel
import kotlinx.coroutines.flow.Flow

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
expect class GtkApplication {
	val windows: MutableList<GtkWindow>
	var menuBar: GMenuModel
	val activeWindow: GtkWindow
	var isRegisterSession: Boolean
	var isScreenSaverActive: Boolean


	fun getWindowById(): GtkWindow?
	fun getActiveWindow(): GtkWindow

	fun inhibit(): Int
	fun unInhibit()

	fun getMenuById()

	fun listActionDescriptions(): String
	fun getAccelsForAction(): String
	fun setAccelsForAction()
	fun getActionsForAccel(): String

	val queryEndSignal: Flow<*>
	val windowAddedSignal: Flow<*>
	val windowRemovedSignal: Flow<*>
}