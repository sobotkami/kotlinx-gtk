package org.gtk.glib

import glib.g_free
import glib.gpointer

/**
 * @see <a href="https://developer.gnome.org/glib/stable/glib-Memory-Allocation.html#g-free">g_free</a>
 */
inline fun gpointer.free() = g_free(this)