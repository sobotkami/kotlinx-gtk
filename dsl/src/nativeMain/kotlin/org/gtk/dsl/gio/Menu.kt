package org.gtk.dsl.gio

import org.gtk.GtkDsl
import nativex.gio.Menu
import nativex.gio.MenuModel
import nativex.gtk.Application

/**
 * kotlinx-gtk
 * 28 / 03 / 2021
 */
@org.gtk.GtkDsl
fun Application.menuBar(menuBuilder: Menu.() -> Unit): Menu =
	Menu().apply(menuBuilder).also { menuBar = it }

@org.gtk.GtkDsl
fun menu(
	builder: Menu.() -> Unit
): Menu = Menu().apply(builder)

@org.gtk.GtkDsl
fun Menu.item(
	label: String,
	detailedAction: String? = null,
	builder: Menu.Item.() -> Unit = {}
): Menu.Item =
	Menu.Item(label, detailedAction).apply(builder).also { appendItem(it) }

@org.gtk.GtkDsl
fun Menu.submenu(
	label: String?,
	menu: MenuModel,
	builder: Menu.Item.Submenu.() -> Unit = {},
): Menu.Item.Submenu =
	Menu.Item.Submenu(label, menu).apply(builder).also { appendItem(it) }

@org.gtk.GtkDsl
fun Menu.section(
	label: String?,
	menu: MenuModel,
	builder: Menu.Item.Section.() -> Unit = {}
): Menu.Item.Section =
	Menu.Item.Section(label, menu).apply(builder).also { appendItem(it) }
