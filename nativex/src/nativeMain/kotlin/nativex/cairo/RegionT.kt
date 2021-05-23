package nativex.cairo

import gtk.cairo_region_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class RegionT internal constructor(
 internal val pointer: CPointer<cairo_region_t>
) {
}