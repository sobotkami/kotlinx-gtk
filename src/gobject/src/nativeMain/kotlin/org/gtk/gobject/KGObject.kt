package org.gtk.gobject

import glib.GDestroyNotify
import gobject.*
import kotlinx.cinterop.*
import org.gtk.glib.reinterpretOrNull
import org.gtk.gobject.Binding.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 08 / 03 / 2021
 *
 * @see <a href="https://docs.gtk.org/gobject/class.Object.html">GObject</a>
 */
open class KGObject constructor(val pointer: CPointer<GObject>) {

	constructor(type: KGType, vararg args: Parameter) : this(
		memScoped {
			val names = allocArray<CPointerVar<GParameter>>(args.size)

			args.forEachIndexed { index, parameter ->
				names[index] = parameter.pointer
			}

			g_object_newv(
				type.glib,
				args.size.toUInt(),
				names.pointed.value
			)!!.reinterpret()
		}
	)

	//fun addToggleRef(notify: ToggleNotify, data: COpaquePointer) {
	//	g_object_add_toggle_ref(pointer, notify, data)
	//}

	fun addWeakPointer(weakPointerLocation: CValuesRef<COpaquePointerVar>) {
		g_object_add_weak_pointer(pointer, weakPointerLocation)
	}

	fun bindProperty(property: String, target: KGObject, targetProperty: String, flags: Binding.Flags): Binding =
		g_object_bind_property(pointer, property, target.pointer, targetProperty, flags.glib)!!.wrap()

	/**
	 * @see <a href=""></a>
	 */
	fun set(@Property propertyName: String, boolean: Boolean) {
		g_object_set_property(pointer, propertyName, KGValue(boolean).pointer.reinterpret())
	}

	open fun unref() {
		g_object_unref(pointer)
	}

	@Target(AnnotationTarget.VALUE_PARAMETER)
	annotation class Property

	companion object {
		val staticUnrefFunction: GDestroyNotify = staticCFunction { data ->
			g_object_unref(data)
			Unit
		}

		fun CPointer<GObject>?.wrap() =
			this?.wrap()

		fun CPointer<GObject>.wrap() =
			KGObject(this)
	}

	fun asKGBinding(): Binding? =
		pointer.reinterpretOrNull<GBinding>().wrap()
}