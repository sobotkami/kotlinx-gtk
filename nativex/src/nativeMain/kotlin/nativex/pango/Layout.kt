package nativex.pango

import gtk.PangoLayout
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Layout internal constructor(internal val pointer:CPointer<PangoLayout>)