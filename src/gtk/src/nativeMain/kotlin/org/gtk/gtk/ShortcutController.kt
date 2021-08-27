package org.gtk.gtk

import gtk.GtkShortcutController
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.controller.EventController

/**
 * gtk-kt
 *
 * 25 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class ShortcutController(val shortcutControllerPointer: CPointer<GtkShortcutController>) :
	EventController(shortcutControllerPointer.reinterpret()) {

}