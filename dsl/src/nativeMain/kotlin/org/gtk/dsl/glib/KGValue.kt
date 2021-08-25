package org.gtk.dsl.glib

import org.gtk.gobject.KGType
import org.gtk.gobject.KGValue

fun kGValue(value: Int) = KGValue(KGType.INT).apply { set(value) }

fun kGValue(value: String) = KGValue(KGType.STRING).apply { set(value) }