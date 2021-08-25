package org.gtk.dsl.gio

import org.gtk.dsl.GtkDsl
import org.gtk.gio.ActionMap
import org.gtk.gio.SimpleAction
import org.gtk.gio.SimpleAction.Companion.safeCast

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@GtkDsl
inline fun ActionMap.lookUpSimpleAction(
	actionName: String,
	editor: SimpleAction.() -> Unit
) = lookupAction(actionName)?.safeCast()?.apply(editor)