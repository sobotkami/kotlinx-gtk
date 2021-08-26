package org.gtk.gtk

import gtk.GtkShortcutManager
import kotlinx.cinterop.CPointer

/**
 * gtk-kt
 *
 * 25 / 08 / 2021
 *
 * @see <a href=""></a>
 */
interface ShortcutManager {
	val shortcutManagerPointer: CPointer<GtkShortcutManager>
}