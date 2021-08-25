package org.gtk.dsl.gio

import nativex.gio.ActionMap
import nativex.gio.SimpleAction
import nativex.gio.SimpleAction.Companion.safeCast
import org.gtk.GtkDsl

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@org.gtk.GtkDsl
inline fun ActionMap.lookUpSimpleAction(
	actionName: String,
	editor: SimpleAction.() -> Unit
) = lookupAction(actionName)?.safeCast()?.apply(editor)