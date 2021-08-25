package org.gtk.cairo

import cairo.cairo_region_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class Region(
  val pointer: CPointer<cairo_region_t>
)