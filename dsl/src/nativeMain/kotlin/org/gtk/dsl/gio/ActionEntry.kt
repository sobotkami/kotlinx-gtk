package org.gtk.dsl.gio

import org.gtk.gio.ActionMap
import org.gtk.gio.ActionMapEntryFunction


fun actionEntry(
	name: String,
	onActivate: ActionMapEntryFunction = null,
	parameterType: String? = null,
	state: String? = null,
	changeState: ActionMapEntryFunction = null
) = ActionMap.Entry(
	name = name,
	onActivate = onActivate,
	parameterType = parameterType,
	state = state,
	changeState = changeState
)


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
