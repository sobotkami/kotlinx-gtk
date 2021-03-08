package kotlin.gtk.windows

import gtk.GtkShortcutsWindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.windows.Window

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class ShortcutsWindow internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val shortCutsWindowPointer: CPointer<GtkShortcutsWindow>
) : Window(shortCutsWindowPointer.reinterpret())