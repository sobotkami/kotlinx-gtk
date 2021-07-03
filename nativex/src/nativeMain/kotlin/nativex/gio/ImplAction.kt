package nativex.gio

import gtk.GAction
import kotlinx.cinterop.CPointer

 class ImplAction(override val actionPointer: CPointer<GAction>) : Action