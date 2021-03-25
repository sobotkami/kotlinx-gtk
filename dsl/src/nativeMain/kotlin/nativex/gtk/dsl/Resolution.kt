package nativex.gtk.dsl


typealias Resolution = Pair<Int, Int>

@GtkDsl
inline infix fun Int.x(vertical: Int): Pair<Int, Int> =
	this to vertical
