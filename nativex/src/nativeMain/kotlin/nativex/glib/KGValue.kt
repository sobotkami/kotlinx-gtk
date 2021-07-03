package nativex.glib

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import nativex.gtk.gtk

class KGValue( val pointer: CPointer<GValue>) {
	constructor(type: KGType) : this(memScoped { g_value_init(alloc<GValue>().ptr, type.glib)!! })

	constructor(value: Int) : this(
		memScoped {
			val gValue = alloc<GValue>()
			g_value_init(gValue.ptr, KGType.INT.glib)
			g_value_set_int(gValue.ptr, value)
			gValue.ptr
		}
	)

	constructor(value: String) : this(
		memScoped {
			val gValue = alloc<GValue>()
			g_value_init(gValue.ptr, KGType.STRING.glib)
			g_value_set_string(gValue.ptr, value)
			gValue.ptr
		}
	)

	constructor(value: Boolean) : this(
		memScoped {
			val gValue = alloc<GValue>()
			g_value_init(gValue.ptr, KGType.BOOLEAN.glib)
			g_value_set_boolean(gValue.ptr, value.gtk)
			gValue.ptr
		}
	)


	fun set(value: Int) {
		g_value_set_int(pointer, value)
	}

	fun set(value: String) {
		g_value_set_string(pointer, value)
	}

	fun unset() {
		g_value_unset(pointer)
	}
}