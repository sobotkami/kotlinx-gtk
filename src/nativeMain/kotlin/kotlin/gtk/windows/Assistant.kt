package kotlin.gtk.windows

import gtk.GtkAssistant
import gtk.GtkDialog
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class Assistant internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val assistantPointer: CPointer<GtkAssistant>
) : Window(assistantPointer.reinterpret())