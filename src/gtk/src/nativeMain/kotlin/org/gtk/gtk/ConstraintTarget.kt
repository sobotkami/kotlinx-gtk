package org.gtk.gtk

import gtk.GtkConstraintTarget
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.ConstraintTarget.html"></a>
 */
interface ConstraintTarget {
	val constraintTargetPointer: CPointer<GtkConstraintTarget>
}