package nativex.glib

import nativex.gobject.KGType
import nativex.gobject.KGValue

fun kGValue(value: Int) = KGValue(KGType.INT).apply { set(value) }

fun kGValue(value: String) = KGValue(KGType.STRING).apply { set(value) }