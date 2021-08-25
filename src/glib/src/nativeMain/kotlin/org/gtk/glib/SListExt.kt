package org.gtk.glib

/*
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 */

/**
 * Check if this [SList] contains a value matching [data]
 */
inline operator fun SList.contains(data: VoidPointer): Boolean =
	index(data) != -1

/**
 * Get value associated with the [index]
 *
 * @return null if [index] is out of bounds
 */
inline operator fun SList.get(index: UInt): VoidPointer? =
	nthData(index)

/**
 * Remove operator function
 */
inline operator fun SList.minus(data: VoidPointer) = remove(data)

/**
 * Prepend operator function for KList
 */
inline operator fun VoidPointer.plus(list: SList) = list.prepend(this)

/**
 * Append operator function
 */
inline operator fun SList.plus(data: VoidPointer) = append(data)

/**
 * Concat operator function
 */
inline operator fun SList.plus(list: SList) = concat(list)

/**
 * Insert operator function
 */
inline operator fun SList.set(index: Int, value: VoidPointer) {
	insert(index, value)
}