package nativex.pango

import gtk.PangoContext
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class Context(internal val pointer: CPointer<PangoContext>) {
}