package nativex.gtk.widgets.windows

import gtk.GtkAssistant
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class Assistant(
	@Suppress("MemberVisibilityCanBePrivate")
	 val assistantPointer: CPointer<GtkAssistant>
) : Window(assistantPointer.reinterpret())