package org.gtk.gobject

import gobject.GInitiallyUnowned
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gobject/class.InitiallyUnowned.html">GInitiallyUnowned</a>
 */
class InitiallyUnowned(val initiallyUnownedPointer: CPointer<GInitiallyUnowned>) :
	KGObject(initiallyUnownedPointer.reinterpret())