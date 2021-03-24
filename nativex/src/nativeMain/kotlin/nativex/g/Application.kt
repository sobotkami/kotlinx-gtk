package nativex.g

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import nativex.PointerHolder
import nativex.gio.ActionMap
import nativex.glib.OptionArg
import nativex.glib.OptionFlags

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
open class Application(
	private val gApplicationPointer: CPointer<GApplication>
) : KotlinGObject(gApplicationPointer.reinterpret()), ActionMap {
	fun run(argc: Int = 0): Int = memScoped {
		g_application_run(gApplicationPointer, argc, null)
	}

	fun addMainOption(
		longName: String,
		shortName: Char,
		description: String,
		flags: OptionFlags = OptionFlags.NONE,
		arg: OptionArg = OptionArg.NONE,
		argDescription: String? = null
	) {
		g_application_add_main_option(
			gApplicationPointer,
			longName,
			shortName.toByte(),
			flags.gtk,
			arg.gtk,
			description,
			argDescription
		)
	}

	enum class Flags constructor(
		val key: Int,
		internal val gtk: GApplicationFlags
	) {
		NONE(0, G_APPLICATION_FLAGS_NONE),
		IS_SERVICE(1, G_APPLICATION_IS_SERVICE),
		IS_LAUNCHER(2, G_APPLICATION_IS_LAUNCHER),
		HANDLES_OPEN(3, G_APPLICATION_HANDLES_OPEN),
		HANDLES_COMMAND_LINE(4, G_APPLICATION_HANDLES_COMMAND_LINE),
		SEND_ENVIRONMENT(5, G_APPLICATION_SEND_ENVIRONMENT),
		NON_UNIQUE(6, G_APPLICATION_NON_UNIQUE),
		CAN_OVERRIDE_APP_ID(7, G_APPLICATION_CAN_OVERRIDE_APP_ID),
		ALLOW_REPLACEMENT(8, G_APPLICATION_ALLOW_REPLACEMENT),
		REPLACE(9, G_APPLICATION_REPLACE);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GApplicationFlags) =
				values().find { it.gtk == gtk }
		}
	}

	override val actionMapPointer: PointerHolder<GActionMap>
		get() = PointerHolder(gApplicationPointer.reinterpret())
}