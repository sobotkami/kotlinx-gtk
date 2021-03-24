package nativex.gio.dsl

import nativex.gio.ActionMap
import nativex.gio.ActionMapEntryFunction


fun actionEntry(
	name: String,
	onActivate: ActionMapEntryFunction = null,
	parameterType: String? = null,
	state: String? = null,
	changeState: ActionMapEntryFunction = null
) = ActionMap.Entry(name, onActivate, parameterType, state, changeState)


fun stringActionEntry(
	name: String,
	onActivate: ActionMapEntryFunction = null,
	state: String? = null,
	changeState: ActionMapEntryFunction = null
) = actionEntry(name, onActivate, "s", state?.let { "'$it'" }, changeState)


fun booleanActionEntry(
	name: String,
	onActivate: ActionMapEntryFunction = null,
	state: Boolean? = null,
	changeState: ActionMapEntryFunction = null
) = actionEntry(name, onActivate, null, state?.let { "'$it'" }, changeState)
