package org.gtk.dsl.gio

import org.gtk.dsl.GtkDsl
import org.gtk.gio.SimpleAction

@GtkDsl
fun SimpleAction.enable() {
	setEnabled(true)
}

@GtkDsl
fun SimpleAction.disable() {
	setEnabled(false)
}