package nativex.gio

import gio.GAction
import kotlinx.cinterop.CPointer

class ImplAction(override val actionPointer: CPointer<GAction>) : Action