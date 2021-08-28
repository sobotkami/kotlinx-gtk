package org.gtk.gtk

import org.gtk.gtk.widgets.windows.Window

/*
 * gtk-kt
 *
 * 27 / 08 / 2021
 */


operator fun WindowGroup.plus(window: Window) = addWindow(window)

operator fun WindowGroup.plusAssign(window: Window) = addWindow(window)