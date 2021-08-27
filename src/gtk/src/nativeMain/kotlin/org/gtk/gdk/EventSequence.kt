package org.gtk.gdk

import gtk.GdkEventSequence
import kotlinx.cinterop.CPointer

/**
 * gtk-kt
 *
 * 26 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class EventSequence(val eventSequence: CPointer<GdkEventSequence>)