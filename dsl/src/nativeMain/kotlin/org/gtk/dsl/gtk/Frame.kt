package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.frame.Frame

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Box.frame(
	label: String? = null,
	append: Boolean = true,
	frameBuilder: Frame.() -> Unit = {}
) = Frame(label).apply(frameBuilder).also { if (append) append(it) else prepend(it) }