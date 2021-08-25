package org.gtk.gio

import gio.GActionGroup
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 05 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gio/iface.ActionGroup.html">GActionGroup</a>
 */
interface ActionGroup {
	val actionGroupPointer: CPointer<GActionGroup>
}