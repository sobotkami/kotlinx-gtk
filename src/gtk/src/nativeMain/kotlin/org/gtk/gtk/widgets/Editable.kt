package org.gtk.gtk.widgets

import gtk.GtkEditable
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 24 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.Editable.html"></a>
 */
open class Editable(val editablePointer: CPointer<GtkEditable>) : Widget(editablePointer.reinterpret())