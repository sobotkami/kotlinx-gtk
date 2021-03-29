package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.LinkButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
inline fun Container.linkButton(
	uri: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = add(LinkButton(uri).apply(buttonBuilder))


@GtkDsl
inline fun Container.linkButton(
	uri: String,
	label: String,
	buttonBuilder: LinkButton.() -> Unit = {}
) = add(LinkButton(uri, label).apply(buttonBuilder))


@GtkDsl
inline fun LinkButton.visited() {
	visited = true
}