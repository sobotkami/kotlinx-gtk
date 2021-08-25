package org.gtk.glib

import kotlinx.cinterop.*

/*
 * kotlinx-gtk
 *
 * 20 / 08 / 2021
 */

/**
 * Kotlin typealias for a C Array
 */
typealias CArray<T> = CPointer<CPointerVar<T>>

/**
 * Turn a NULL terminated C Array into a Kotlin Array
 *
 * `set` operations on the returned array does not modify the original C Array.
 */
inline fun <reified T : CPointed> CArray<T>.toArray(): Array<CPointer<T>> {
	var size = 0

	// get max size
	run {
		while (get(size) != null) {
			size++
		}
	}

	return Array(size) { get(it)!! }
}

/**
 * Turn a C Array into a Kotlin Array with a known size
 *
 * `set` operations on the returned Array does not modify the original C Array.
 */
inline fun <reified T : CPointed> CArray<T>.toArray(size: Int): Array<CPointer<T>> =
	Array(size) { get(it)!! }

/**
 * Turn a NULL terminated C Array into a Kotlin List, with action to wrap each index
 *
 * `set` operations on the returned array does not modify the original C Array.
 */
inline fun <reified T : CPointed, O> CArray<T>.toWrappedList(wrap: (CPointer<T>) -> O): List<O> =
	toArray().map(wrap)

/**
 * Turn a C Array into a Kotlin List with a known size, with action to wrap each index
 *
 * `set` operations on the returned array does not modify the original C Array.
 */
inline fun <reified T : CPointed, O> CArray<T>.toWrappedList(size: Int, wrap: (CPointer<T>) -> O): List<O> =
	toArray(size).map(wrap)


inline fun CArray<UIntVar>.toList(): List<UInt> =
	toWrappedList { it.pointed.value }

inline fun CArray<UIntVar>.toList(size: Int): List<UInt> =
	toWrappedList(size) { it.pointed.value }

inline fun CStringList.toList(): List<String> =
	toWrappedList { it.toKString() }