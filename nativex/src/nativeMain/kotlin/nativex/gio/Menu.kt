package nativex.gio

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.Variant
import nativex.glib.VariantType
import nativex.reinterpretOrNull

/**
 * kotlinx-gtk
 * 28 / 03 / 2021
 */
class Menu internal constructor(
	internal val menuPointer: CPointer<GMenu>
) : MenuModel(menuPointer.reinterpret()) {
	constructor() : this(g_menu_new()!!.reinterpret())

	fun freeze() {
		g_menu_freeze(menuPointer)
	}

	fun insert(position: Int, label: String?, detailedAction: String?) {
		g_menu_insert(menuPointer, position, label, detailedAction)
	}

	fun prepend(label: String?, detailedAction: String?) {
		g_menu_prepend(menuPointer, label, detailedAction)
	}

	fun append(label: String?, detailedAction: String?) {
		g_menu_append(menuPointer, label, detailedAction)
	}

	fun insertItem(position: Int, menuItem: Item) {
		g_menu_insert_item(menuPointer, position, menuItem.menuItemPointer)
	}

	fun appendItem(menuItem: Item) {
		g_menu_append_item(menuPointer, menuItem.menuItemPointer)
	}

	fun prependItem(menuItem: Item) {
		g_menu_prepend_item(menuPointer, menuItem.menuItemPointer)
	}

	fun insertSection(
		position: Int,
		label: String?,
		section: MenuModel
	) {
		g_menu_insert_section(
			menuPointer,
			position,
			label,
			section.menuModelPointer
		)
	}

	fun prependSection(
		label: String?,
		section: MenuModel
	) {
		g_menu_prepend_section(menuPointer, label, section.menuModelPointer)
	}

	fun appendSection(
		label: String?,
		section: MenuModel
	) {
		g_menu_append_section(menuPointer, label, section.menuModelPointer)
	}

	fun appendSubmenu(
		label: String?,
		section: MenuModel
	) {
		g_menu_append_submenu(menuPointer, label, section.menuModelPointer)
	}

	fun insertSubmenu(
		position: Int,
		label: String?,
		submenu: MenuModel
	) {
		g_menu_insert_submenu(
			menuPointer,
			position,
			label,
			submenu.menuModelPointer
		)
	}

	fun prependSubmenu(
		label: String?,
		submenu: MenuModel
	) {
		g_menu_prepend_submenu(menuPointer, label, submenu.menuModelPointer)
	}

	fun remove(position: Int) {
		g_menu_remove(menuPointer, position)
	}

	fun removeAll() {
		g_menu_remove_all(menuPointer)
	}


	open class Item internal constructor(
		internal val menuItemPointer: CPointer<GMenuItem>
	) : KObject(menuItemPointer.reinterpret()) {
		constructor(label: String? = null, detailedAction: String? = null) : this(
			g_menu_item_new(label, detailedAction)!!.reinterpret()
		)

		constructor(model: MenuModel, index: Int) : this(
			g_menu_item_new_from_model(
				model.menuModelPointer,
				index
			)!!.reinterpret()
		)

		fun setLabel(label: String?) {
			g_menu_item_set_label(menuItemPointer, label)
		}

		fun setIcon(icon: Icon) {
			g_menu_item_set_icon(menuItemPointer, icon.pointer.ptr)
		}

		fun setActionAndTargetValue(action: String?, variant: Variant?) {
			g_menu_item_set_action_and_target_value(
				menuItemPointer,
				action,
				variant?.variantPointer
			)
		}

		fun setDetailedAction(detailedAction: String?) {
			g_menu_item_set_detailed_action(menuItemPointer, detailedAction)
		}

		fun setSection(section: MenuModel?) {
			g_menu_item_set_section(menuItemPointer, section?.menuModelPointer)
		}

		fun setSubmenu(submenu: MenuModel?) {
			g_menu_item_set_submenu(menuItemPointer, submenu?.menuModelPointer)
		}

		fun getAttributeValue(
			attribute: String,
			expectedType: VariantType
		): Variant? =
			g_menu_item_get_attribute_value(
				menuItemPointer,
				attribute,
				expectedType.variantTypePointer
			)?.let { Variant(it) }

		// TODO g_menu_item_get_attribute

		fun getLink(link: String?): MenuModel? =
			g_menu_item_get_link(menuItemPointer, link)?.let { Impl(it) }

		fun setAttributeValue(attribute: String, variant: Variant?) {
			g_menu_item_set_attribute_value(
				menuItemPointer,
				attribute,
				variant?.variantPointer
			)
		}

		fun setLink(link: String, menuModel: MenuModel?) {
			g_menu_item_set_link(
				menuItemPointer,
				link,
				menuModel?.menuModelPointer
			)
		}

		class Section internal constructor(p: CPointer<GMenuItem>) : Item(p) {
			constructor(label: String?, section: MenuModel) : this(
				g_menu_item_new_section(
					label,
					section.menuModelPointer
				)!!.reinterpret()
			)
		}

		class Submenu internal constructor(p: CPointer<GMenuItem>) : Item(p) {
			constructor(label: String?, section: MenuModel) : this(
				g_menu_item_new_submenu(
					label,
					section.menuModelPointer
				)!!.reinterpret()
			)
		}

	}

	companion object {
		fun from(menuModel: MenuModel): Menu? =
			menuModel.menuModelPointer.reinterpretOrNull<GMenu>()?.let(::Menu)

		fun MenuModel.asMenu(): Menu? =
			from(this)

		internal inline fun CPointer<GMenu>?.wrap() =
			this?.let { Menu(it) }

		internal inline fun CPointer<GMenu>.wrap() =
			Menu(this)
	}
}