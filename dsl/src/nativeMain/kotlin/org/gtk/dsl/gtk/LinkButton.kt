package org.gtk.dsl.gtk

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.box.Box
import nativex.gtk.widgets.button.LinkButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
inline fun Box.appendLinkButton(
	uri: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = append(LinkButton(uri).apply(buttonBuilder))


@GtkDsl
inline fun Box.appendLinkButton(
	uri: String,
	label: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = append(LinkButton(uri, label).apply(buttonBuilder))


@GtkDsl
inline fun LinkButton.visited() {
	visited = true
}