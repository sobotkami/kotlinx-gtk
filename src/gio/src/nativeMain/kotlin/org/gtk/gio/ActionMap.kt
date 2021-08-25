package org.gtk.gio

import gio.*
import glib.GVariant
import glib.gpointer
import kotlinx.cinterop.*
import org.gtk.glib.Variant

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
interface ActionMap {
	val actionMapPointer: CPointer<GActionMap>

	fun lookupAction(actionName: String): Action? =
		g_action_map_lookup_action(actionMapPointer, actionName)?.let {
			ImplAction(it)
		}

	fun addActionEntries(vararg entries: Entry){
		addActionEntries(entries.toList())
	}

	fun addActionEntries(
		entries: List<Entry>,
		userData: Any? = null
	) {
		g_action_map_add_action_entries(
			actionMapPointer,
			memScoped<CValuesRef<GActionEntry>?> {
				allocArrayOf(entries.map { it.actionEntryPointer }).pointed.value
			},
			entries.size,
			@Suppress("RemoveExplicitTypeArguments")
			StableRef.create<NativeActionMapEntryFunction> { actionPointer: CPointer<GSimpleAction>?,
			                                                 parameterPointer: CPointer<GVariant>?,
			                                                 type: Int ->
				entries.find { it.actionEntryPointer == actionPointer }
					?.let { entry ->
						when (type) {
							0 -> entry.activate?.invoke(
								SimpleAction(actionPointer!!),
								Variant(parameterPointer!!),
								userData
							)
							else -> entry.changeState?.invoke(
								SimpleAction(actionPointer!!),
								Variant(parameterPointer!!),
								userData
							)
						}
					}
				Unit
			}.asCPointer()

		)
	}

	@Deprecated(
		"To properly wrap the action, have to use a single static callback",
		level = DeprecationLevel.HIDDEN
	)
	fun addAction(action: Action) {
		g_action_map_add_action(actionMapPointer, action.actionPointer)
	}

	fun removeAction(actionName: String) {
		g_action_map_remove_action(actionMapPointer, actionName)
	}

	/**
	 * While coding this class, Had issues with creating proper callbacks.
	 *
	 * Instead of actually wrapping the C function,
	 * the C function calls the K function
	 */
	class Entry(
		 val actionEntryPointer: CPointer<GActionEntry>
	) {
		var name: String
			get() = actionEntryPointer.pointed.name!!.toKString()
			set(value) {
				actionEntryPointer.pointed.name = memScoped {
					value.cstr.ptr
				}
			}
		var state: String?
			get() = actionEntryPointer.pointed.state?.toKString()
			set(value) {
				actionEntryPointer.pointed.state = memScoped {
					value?.cstr?.ptr
				}
			}
		var parameterType: String?
			get() = actionEntryPointer.pointed.parameter_type?.toKString()
			set(value) {
				actionEntryPointer.pointed.parameter_type = memScoped {
					value?.cstr?.ptr
				}
			}

		var activate: ActionMapEntryFunction = null

		var changeState: ActionMapEntryFunction = null

		init {
			actionEntryPointer.pointed.activate = activateCallback
			actionEntryPointer.pointed.change_state = changeStateCallback
		}

		constructor(
			name: String,
			onActivate: ActionMapEntryFunction = null,
			parameterType: String? = null,
			state: String? = null,
			changeState: ActionMapEntryFunction = null
		) : this(memScoped { cValue<GActionEntry>().ptr }) {
			this.name = name
			this.state = state
			this.parameterType = parameterType
			this.activate = onActivate
			this.changeState = changeState
		}

		companion object {
			 val activateCallback =
				staticCFunction { action: CPointer<GSimpleAction>?, variant: CPointer<GVariant>?, userData: gpointer? ->
					userData?.asStableRef<NativeActionMapEntryFunction>()
						?.get()
						?.invoke(action, variant, 0)
					Unit
				}


			 val changeStateCallback =
				staticCFunction { action: CPointer<GSimpleAction>?, variant: CPointer<GVariant>?, userData: gpointer? ->
					userData?.asStableRef<NativeActionMapEntryFunction>()
						?.get()
						?.invoke(action, variant, 1)
					Unit
				}

		}
	}
}

 typealias NativeActionMapEntryFunction = (
	@ParameterName("actionPointer") CPointer<GSimpleAction>?,
	@ParameterName("parameterPointer") CPointer<GVariant>?,
	/**
	 * 0 if its an activate,
	 * 1 if it is a change-state
	 */
	@ParameterName("type") Int,
) -> Unit


typealias ActionMapEntryFunction = ((SimpleAction, Variant, Any?) -> Unit)?