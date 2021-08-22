package nativex.glib

/*
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 */

/**
 * Use the [KList], Then free it while returning the result
 */
inline fun <R> KList.use(use: (KList) -> R): R =
	use(this).also { free() }

/**
 * Check if this [KList] contains a value matching [data]
 */
inline operator fun KList.contains(data: VoidPointer): Boolean =
	index(data) != -1

/**
 * Get value associated with the [index]
 *
 * @return null if [index] is out of bounds
 */
inline operator fun KList.get(index: UInt): VoidPointer? =
	nthData(index)

/**
 * Remove operator function
 */
inline operator fun KList.minus(data: VoidPointer) = remove(data)

/**
 * Remove operator function, for another style
 */
inline operator fun KList.minusAssign(data: VoidPointer) {
	remove(data)
}

/**
 * Prepend operator function for KList
 */
inline operator fun VoidPointer.plus(list: KList) = list.prepend(this)

/**
 * Append operator function
 */
inline operator fun KList.plus(data: VoidPointer) = append(data)

/**
 * Append operator function, for another style
 */
inline operator fun KList.plusAssign(data: VoidPointer) {
	append(data)
}

/**
 * Concat operator function
 */
inline operator fun KList.plus(list: KList) = concat(list)

/**
 * Concat operator function, for another style
 */
inline operator fun KList.plusAssign(list: KList) {
	concat(list)
}

/**
 * Insert operator function
 */
inline operator fun KList.set(index: Int, value: VoidPointer) {
	insert(index, value)
}