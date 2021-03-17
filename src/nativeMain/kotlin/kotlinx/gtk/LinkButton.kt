package kotlinx.gtk

import kotlin.gtk.container.Container
import kotlin.gtk.container.bin.button.LinkButton

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