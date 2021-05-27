package nativex.glib

import gtk.GValue
import kotlinx.cinterop.CPointer

class KGValue internal constructor(internal val pointer: CPointer<GValue>)