package nativex.gio.dsl

import nativex.gio.SimpleAction
import nativex.GtkDsl

@GtkDsl
fun SimpleAction.enable() {
	setEnabled(true)
}

@GtkDsl
fun SimpleAction.disable() {
	setEnabled(false)
}