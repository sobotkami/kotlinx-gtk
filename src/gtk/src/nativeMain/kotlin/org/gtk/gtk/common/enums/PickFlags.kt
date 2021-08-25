package org.gtk.gtk.common.enums

import gtk.GTK_PICK_DEFAULT
import gtk.GTK_PICK_INSENSITIVE
import gtk.GTK_PICK_NON_TARGETABLE

/**
 * @see <a href="https://docs.gtk.org/gtk4/flags.PickFlags.html"></a>
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
annotation class PickFlags

@PickFlags
const val PICK_DEFAULT: UInt = GTK_PICK_DEFAULT

@PickFlags
const val INSENSITIVE: UInt = GTK_PICK_INSENSITIVE

@PickFlags
const val NON_TARGETABLE: UInt = GTK_PICK_NON_TARGETABLE
