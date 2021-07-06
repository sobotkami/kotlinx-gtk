package nativex.gtk

import gtk.GtkWidget
import kotlinx.cinterop.CPointer
import nativex.gtk.widgets.Widget

typealias WidgetPointer = CPointer<GtkWidget>

inline fun WidgetPointer?.asWidgetOrNull() = this?.let { Widget(it) }

inline fun WidgetPointer?.asWidget() = Widget(this!!)


