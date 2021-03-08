package kotlinx.gtk


@GtkDsl
inline infix fun Int.x(other: Int): Pair<Int, Int> =
	this to other