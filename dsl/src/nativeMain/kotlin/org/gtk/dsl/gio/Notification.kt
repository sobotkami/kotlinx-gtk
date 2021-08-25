package org.gtk.dsl.gio

import org.gtk.GtkDsl
import nativex.gio.Icon
import nativex.gio.Notification

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
@org.gtk.GtkDsl
inline fun notification(
	title: String,
	body: String = "",
	icon: Icon? = null,
	priority: Notification.Priority = Notification.Priority.NORMAL,
	builder: Notification.() -> Unit = {}
) = Notification(title).apply {
	setBody(body)
	icon?.let { setIcon(it) }
	setPriority(priority)
	builder()
}