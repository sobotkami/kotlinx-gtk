package org.gtk.glib

import glib.g_strfreev
import kotlinx.cinterop.get
import kotlinx.cinterop.toKString

/**
 * kotlinx-gtk
 *
 * 01 / 08 / 2021
 *
 * @see <a href=""></a>
 */

/**
 * Create a list of strings from a [CStringList], then free it with [g_strfreev]
 */
inline fun stringListFromNullTerminatedCStringListAndFree(cList: CStringList?): List<String> {
	cList ?: return emptyList()
	val arrayList = ArrayList<String>()

	var index = 0

	// Loop through the c list, adding values to arrayList until a null is found
	while (true) {
		val value = cList[index]?.toKString()
		if (value == null) {
			break
		} else {
			arrayList.add(value)
			index++
		}
	}

	// free the list
	g_strfreev(cList)

	return arrayList
}