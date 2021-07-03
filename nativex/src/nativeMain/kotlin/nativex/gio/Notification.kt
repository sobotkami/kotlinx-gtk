package nativex.gio

import gtk.*
import gtk.GNotificationPriority.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.Variant

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 */
class Notification(
	 val notificationPointer: CPointer<GNotification>
) : KObject(notificationPointer.reinterpret()) {
	constructor(title: String) : this(g_notification_new(title)!!.reinterpret())

	fun setTitle(title: String) {
		g_notification_set_title(notificationPointer, title)
	}

	fun setBody(title: String) {
		g_notification_set_body(notificationPointer, title)
	}

	fun setIcon(icon: Icon) {
		g_notification_set_icon(notificationPointer, icon.pointer)
	}

	fun setPriority(priority: Priority) {
		g_notification_set_priority(notificationPointer, priority.gio)
	}

	fun setDefaultAction(detailedAction: String) {
		g_notification_set_default_action(notificationPointer, detailedAction)
	}

	fun setDefaultActionAndTargetValue(action: String, target: Variant) {
		g_notification_set_default_action_and_target_value(
			notificationPointer,
			action,
			target.variantPointer
		)
	}

	fun addButton(label: String, detailedAction: String) {
		g_notification_add_button(notificationPointer, label, detailedAction)
	}

	fun addButtonWithTargetValue(
		label: String,
		action: String,
		target: Variant
	) {
		g_notification_add_button_with_target_value(
			notificationPointer,
			label,
			action,
			target.variantPointer
		)
	}


	enum class Priority(val key: Int,  val gio: GNotificationPriority) {
		NORMAL(0, G_NOTIFICATION_PRIORITY_NORMAL),
		LOW(1, G_NOTIFICATION_PRIORITY_LOW),
		HIGH(2, G_NOTIFICATION_PRIORITY_HIGH),
		URGENT(3, G_NOTIFICATION_PRIORITY_URGENT);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }!!
			 fun valueOf(gio: GNotificationPriority) =
				values().find { it.gio == gio }
		}
	}
}