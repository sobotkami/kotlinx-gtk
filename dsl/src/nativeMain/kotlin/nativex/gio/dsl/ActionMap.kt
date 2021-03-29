package nativex.gio.dsl

import nativex.gio.ActionMap
import nativex.gio.SimpleAction
import nativex.gio.SimpleAction.Companion.safeCast
import nativex.GtkDsl

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@GtkDsl
inline fun ActionMap.lookUpSimpleAction(
	actionName: String,
	editor: SimpleAction.() -> Unit
) = lookupAction(actionName)?.safeCast()?.apply(editor)