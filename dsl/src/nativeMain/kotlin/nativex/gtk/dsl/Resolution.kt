package nativex.gtk.dsl

import nativex.GtkDsl


typealias Resolution = Pair<Int, Int>

@GtkDsl
inline infix fun Int.x(vertical: Int): Pair<Int, Int> =
	this to vertical
