package org.gtk.gtk

/*
 * gtk-kt
 *
 * 27 / 08 / 2021
 */


operator fun StringList.plus(string: String) = append(string)

operator fun StringList.plusAssign(string: String) = append(string)

operator fun StringList.get(position: UInt) = get(position)