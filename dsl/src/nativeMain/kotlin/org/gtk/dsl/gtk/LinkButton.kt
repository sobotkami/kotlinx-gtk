package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.button.LinkButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
inline fun Box.linkButton(
	uri: String,
	append: Boolean = true,
	buttonBuilder: LinkButton.() -> Unit = {}
) = LinkButton(uri).apply(buttonBuilder).also { if (append) append(it) else prepend(it) }

@GtkDsl
inline fun LinkButton.visited() {
	visited = true
}