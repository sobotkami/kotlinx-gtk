package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.TreeModel
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Grid
import nativex.gtk.widgets.combobox.ComboBox
import nativex.gtk.widgets.combobox.ComboBoxText

@GtkDsl
inline fun Widget.comboBox(
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox().apply(buttonBuilder).also { add(it) }

@GtkDsl
inline fun Widget.comboBox(
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

@GtkDsl
inline fun Grid.comboBoxText(
	left: Int,
	right: Int,
	width: Int,
	height: Int,
	withEntry: Boolean = false,
	buttonBuilder: ComboBoxText.() -> Unit = {}
) = ComboBoxText(withEntry).apply(buttonBuilder).also { attach(it, left, right, width, height) }
