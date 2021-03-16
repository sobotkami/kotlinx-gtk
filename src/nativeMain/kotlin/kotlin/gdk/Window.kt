package kotlin.gdk

import gtk.GdkWindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.g.KotlinGObject

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Window internal constructor(
	internal val windowPointer: CPointer<GdkWindow>
) : KotlinGObject(windowPointer.reinterpret())