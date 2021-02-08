package com.github.doomsdayrs.lib.kotlinx.gtk.gtk

import kotlinx.coroutines.flow.Flow

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
interface GtkWindow : GtkWidget {
	var child: Any
	var title: String
	var isResizable: Boolean
	var setModal: Any
	var defaultSize: Int
	var hideOnClose: Boolean
	var destroyWithParent: Boolean
	var application: GtkApplication
	var titleBar: GtkWidget
	var isFocusVisible: Boolean
	var focus: GtkWidget
	var defaultWidget: GtkWidget
	var decorated: Boolean
	var deletable: Boolean
	var defaultIconName: String
	val transientFor: GtkWindow
	val hasGroup: Boolean
	var iconName: String
	val areMnemonicsVisible: Boolean

	fun destroy()
	fun setTransientFor()
	fun setDisplay()
	fun isActive(): Boolean
	fun isMaximized(): Boolean
	fun isFullScreen(): Boolean
	fun getTopLevels()
	fun listTopLevels()
	fun present()
	fun presentWithTime()
	fun close()
	fun minimize()
	fun unMinimize()
	fun maximize()
	fun unMaximize()
	fun fullScreen()
	fun fullScreenOnMonitor()
	fun unFullScreen()
	fun setStartupId()
	fun getModal(): Boolean
	fun getGroup(): GtkWindowGroup
	fun setAutoStartupNotification()
	fun setInteractiveDebugging()


	val activeDefaultSignal: Flow<*>
	val activeFocusSignal: Flow<*>
	val closeRequestSignal: Flow<Boolean>
	val enableDebuggingSignal: Flow<Boolean>
	val keysChanged: Flow<*>
}