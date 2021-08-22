package nativex.gio

import gio.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.Variant
import nativex.glib.VariantType
import nativex.glib.gtk
import nativex.gobject.KGObject
import nativex.gobject.SignalManager

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
class SimpleAction(val simpleActionPointer: CPointer<GSimpleAction>) : KGObject(simpleActionPointer.reinterpret()),
	Action {
	companion object {
		/**
		 * Convert an [Action] safely as an
		 */
		fun Action.safeCast(): SimpleAction? =
			interpretCPointer<GSimpleAction>(actionPointer.rawValue)?.let {
				SimpleAction(
					it
				)
			}
	}

	fun addOnActivateCallback(action: (Variant) -> Unit): SignalManager = TODO("Create staticCallback")

	fun addOnChangeStateCallback(action: (Variant) -> Unit): SignalManager = TODO("Create staticCallback")

	constructor(name: String, parameterType: VariantType? = null) : this(
		g_simple_action_new(name, parameterType?.variantTypePointer)!!
	)

	constructor(
		name: String,
		state: Variant,
		parameterType: VariantType? = null,
	) : this(
		g_simple_action_new_stateful(
			name,
			parameterType?.variantTypePointer,
			state.variantPointer
		)!!
	)

	fun setEnabled(enabled: Boolean) {
		g_simple_action_set_enabled(simpleActionPointer, enabled.gtk)
	}

	fun setState(value: Variant) {
		g_simple_action_set_state(simpleActionPointer, value.variantPointer)
	}

	fun setStateHint(stateHint: Variant) {
		g_simple_action_set_state_hint(
			simpleActionPointer,
			stateHint.variantPointer
		)
	}

	override val actionPointer: CPointer<GAction>
		get() = simpleActionPointer.reinterpret()
}