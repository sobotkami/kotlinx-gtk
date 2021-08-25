package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.TreeModel
import org.gtk.gtk.widgets.Grid
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.combobox.ComboBox
import org.gtk.gtk.widgets.combobox.ComboBoxText

@GtkDsl
inline fun Box.comboBox(
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox().apply(buttonBuilder).also { append(it) }

@GtkDsl
inline fun Box.comboBox(
	treeModel: TreeModel,
	withEntry: Boolean = false,
	buttonBuilder: ComboBox.() -> Unit = {}
) = ComboBox(treeModel, withEntry).apply(buttonBuilder).also { append(it) }

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
