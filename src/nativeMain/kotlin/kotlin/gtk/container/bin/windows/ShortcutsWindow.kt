package kotlin.gtk.container.bin.windows

import gtk.GtkShortcutsWindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
class ShortcutsWindow internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val shortCutsWindowPointer: CPointer<GtkShortcutsWindow>
) : Window(shortCutsWindowPointer.reinterpret())