package nativex.cairo

import cairo.cairo_region_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class RegionT(
  val pointer: CPointer<cairo_region_t>
)