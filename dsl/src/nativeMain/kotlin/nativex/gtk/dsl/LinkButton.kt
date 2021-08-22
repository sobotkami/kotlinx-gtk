package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.button.LinkButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
inline fun Widget.linkButton(
	uri: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = add(LinkButton(uri).apply(buttonBuilder))


@GtkDsl
inline fun Widget.linkButton(
	uri: String,
	label: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = add(LinkButton(uri, label).apply(buttonBuilder))


@GtkDsl
inline fun LinkButton.visited() {
	visited = true
}