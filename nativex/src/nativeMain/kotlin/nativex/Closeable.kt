package nativex

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
interface Closeable {
	@Throws(ClosedException::class)
	fun close()
}

class ClosedException(message: String? = null, cause: Throwable? = null) :
	Exception(message, cause)