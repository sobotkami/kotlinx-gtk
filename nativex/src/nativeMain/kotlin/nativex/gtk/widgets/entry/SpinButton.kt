package nativex.gtk.widgets.entry

import gtk.GtkSpinButton
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class SpinButton internal constructor(
	internal val spinButtonPointer: CPointer<GtkSpinButton>
) : Entry(spinButtonPointer.reinterpret())