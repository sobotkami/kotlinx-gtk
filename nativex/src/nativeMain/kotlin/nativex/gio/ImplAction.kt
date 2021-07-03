package nativex.gio

import gtk.GAction
import kotlinx.cinterop.CPointer

internal class ImplAction(override val actionPointer: CPointer<GAction>) : Action {
}