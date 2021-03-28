package nativex.gtk

import gtk.GtkRecentManager
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KotlinGObject

/**
 * kotlinx-gtk
 * 27 / 03 / 2021
 */
class RecentManager internal constructor(
	internal val recentManagerPointer: CPointer<GtkRecentManager>
) : KotlinGObject(recentManagerPointer.reinterpret()) {
}