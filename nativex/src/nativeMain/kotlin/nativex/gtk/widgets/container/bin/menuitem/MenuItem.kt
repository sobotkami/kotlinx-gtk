package nativex.gtk.widgets.container.bin.menuitem

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class MenuItem internal constructor(
	internal val menuItemPointer: CPointer<GtkMenuItem>
) : Bin(menuItemPointer.reinterpret()) {

	constructor() : this(gtk_menu_item_new()!!.reinterpret())

	constructor(
		label: String,
		mnemonic: Boolean = false,
	) : this(
		(if (mnemonic)
			gtk_menu_item_new_with_mnemonic(label)
		else gtk_menu_item_new_with_label(label))!!.reinterpret()
	)

	var label: String?
		get() = gtk_menu_item_get_label(menuItemPointer)?.toKString()
		set(value) = gtk_menu_item_set_label(menuItemPointer, value)

	var useUnderLine: Boolean
		get() = gtk_menu_item_get_use_underline(menuItemPointer)
			.bool
		set(value) = gtk_menu_item_set_use_underline(
			menuItemPointer,
			value.gtk
		)

	var submenu: Widget?
		get() = gtk_menu_item_get_submenu(menuItemPointer)?.let { Widget(it) }
		set(value) = gtk_menu_item_set_submenu(
			menuItemPointer,
			value?.widgetPointer
		)

	var accelPath: String?
		get() = gtk_menu_item_get_accel_path(menuItemPointer)?.toKString()
		set(value) = gtk_menu_item_set_accel_path(menuItemPointer, value)

	fun select() {
		gtk_menu_item_select(menuItemPointer)
	}

	fun deselect() {
		gtk_menu_item_deselect(menuItemPointer)
	}

	fun activate() {
		gtk_menu_item_activate(menuItemPointer)
	}

	fun toggleSizeRequest() {
		TODO("gtk_menu_item_toggle_size_request")
	}

	fun toggleSizeAllocate() {
		TODO("gtk_menu_item_toggle_size_allocate")
	}

	var reserveIndicator: Boolean
		get() = gtk_menu_item_get_reserve_indicator(menuItemPointer).bool
		set(value) = gtk_menu_item_set_reserve_indicator(
			menuItemPointer,
			value.gtk
		)
}