package nativex.gio

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.flow.Flow
import nativex.glib.Variant
import nativex.glib.VariantType
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
class SimpleAction(val simpleActionPointer: CPointer<GSimpleAction>) : KObject(simpleActionPointer.reinterpret()),
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

	val activate: Flow<Variant>
		get() = TODO("Create staticCallback")

	val changeState: Flow<Variant>
		get() = TODO("Create staticCallback")

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