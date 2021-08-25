package org.gtk.gtk

import gtk.GtkBuildable
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.Buildable.html">GtkBuildable</a>
 */
interface Buildable {
	val buildablePointer: CPointer<GtkBuildable>
}