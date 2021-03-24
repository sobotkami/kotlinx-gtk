package nativex.gio

import gtk.*
import kotlinx.cinterop.toKString
import nativex.PointerHolder
import nativex.glib.Variant
import nativex.glib.VariantType
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
interface Action {
	val actionPointer: PointerHolder<GAction>


	val name: String
		get() =
			g_action_get_name(actionPointer.ptr)!!.toKString()

	val parameterType: VariantType?
		get() = g_action_get_parameter_type(actionPointer.ptr)?.let {
			VariantType(it)
		}

	val stateType: VariantType?
		get() = g_action_get_state_type(actionPointer.ptr)?.let {
			VariantType(it)
		}

	val stateHint: Variant?
		get() = g_action_get_state_hint(actionPointer.ptr)?.let {
			Variant(it)
		}

	val enabled: Boolean
		get() = g_action_get_enabled(actionPointer.ptr).bool

	val state: Variant?
		get() = g_action_get_state(actionPointer.ptr)?.let {
			Variant(it)
		}

	fun changeState(value: Variant) {
		@Suppress("UNCHECKED_CAST")
		g_action_change_state(actionPointer.ptr, value.variantPointer)
	}


	fun activate(parameter: Variant) {
		@Suppress("UNCHECKED_CAST")
		g_action_activate(actionPointer.ptr, parameter.variantPointer)
	}


	companion object {
		fun isNameValid(actionName: String): Boolean =
			g_action_name_is_valid(actionName).bool

		fun printDetailedName(
			actionName: String,
			variant: Variant? = null
		): String =
			g_action_print_detailed_name(
				actionName,
				variant?.variantPointer
			)!!.toKString()

		/**
		 * TODO GError
		 */
		fun parseDetailedName(
			detailedName: String,
			actionName: String,
			targetValue: Variant
		): Boolean {
			TODO("g_action_parse_detailed_name")
			//g_action_parse_detailed_name(detailedName,)
		}
	}


}
