package org.gtk.glib

import kotlinx.cinterop.*

/*
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 */

/**
 * Easy way to define a string list
 */
fun WrappedStringKList(memScope: AutofreeScope, kList: KList = KList()) =
	WrappedKList(
		kList = kList,
		wrapPointer = { reinterpret<ByteVarOf<Byte>>().toKString() },
		{ cstr.getPointer(memScope) }
	)