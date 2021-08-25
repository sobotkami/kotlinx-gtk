package org.gtk.gtk

import gtk.GtkWidget
import kotlinx.cinterop.CPointer
import org.gtk.gtk.widgets.Widget

typealias WidgetPointer = CPointer<GtkWidget>

inline fun WidgetPointer?.asWidgetOrNull() = this?.let { Widget(it) }

inline fun WidgetPointer?.asWidget() = Widget(this!!)


