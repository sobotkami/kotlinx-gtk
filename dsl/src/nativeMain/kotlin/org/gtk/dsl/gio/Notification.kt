package org.gtk.dsl.gio

import org.gtk.dsl.GtkDsl
import org.gtk.gio.Icon
import org.gtk.gio.Notification

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
@GtkDsl
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