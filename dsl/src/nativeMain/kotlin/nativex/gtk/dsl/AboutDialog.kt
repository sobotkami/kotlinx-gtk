package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.bin.windows.dialog.AboutDialog

@GtkDsl
fun aboutDialog(builder: AboutDialog.() -> Unit) = AboutDialog().apply(builder)
