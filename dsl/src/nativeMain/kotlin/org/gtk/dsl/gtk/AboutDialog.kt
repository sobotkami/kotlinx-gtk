package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.windows.dialog.AboutDialog

@GtkDsl
inline fun aboutDialog(builder: AboutDialog.() -> Unit) = AboutDialog().apply(builder)

@GtkDsl
inline fun AboutDialog.authors(vararg names: String) {
	authors = names.toList()
}

@GtkDsl
inline fun AboutDialog.artists(vararg names: String) {
	artists = names.toList()
}

@GtkDsl
inline fun AboutDialog.documenters(vararg names: String) {
	documenters = names.toList()
}
