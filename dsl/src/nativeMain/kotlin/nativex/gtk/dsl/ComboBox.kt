package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.TreeModel
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Grid
import nativex.gtk.widgets.container.bin.combobox.ComboBox

@GtkDsl
inline fun Container.comboBox(
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox().apply(buttonBuilder).also { add(it) }

@GtkDsl
inline fun Container.comboBox(
	treeModel: TreeModel,
	withEntry: Boolean = false,
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox(treeModel, withEntry).apply(buttonBuilder).also { add(it) }

@GtkDsl
inline fun Grid.comboBox(
	treeModel: TreeModel,
	left: Int,
	right: Int,
	width: Int,
	height: Int,
	withEntry: Boolean = false,
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox(treeModel, withEntry).apply(buttonBuilder).also { attach(it, left, right, width, height) }
