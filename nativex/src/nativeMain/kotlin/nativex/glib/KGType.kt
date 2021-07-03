package nativex.glib

import gtk.*
import kotlinx.cinterop.*

enum class KGType constructor(
	 val glib: GType
) {
	INVALID(G_TYPE_INVALID),
	INT(G_TYPE_INT),
	STRING(G_TYPE_STRING),
	BOOLEAN(G_TYPE_BOOLEAN);

	companion object {
		fun Array<out KGType>.toCArray(memScope: MemScope): CPointer<GTypeVar> =
			with(memScope) {
				G_TYPE_NONE
				allocArray<GTypeVar>(this@toCArray.size).apply {
					this@toCArray.forEachIndexed { index, kgType ->
						this[index] = kgType.glib
					}
				}
			}

		 fun valueOf(glib: GType) = values().find { it.glib == glib } ?: INVALID
	}
}