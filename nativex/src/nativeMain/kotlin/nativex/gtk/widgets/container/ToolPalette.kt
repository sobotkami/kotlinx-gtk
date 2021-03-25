package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.IconSize
import nativex.gtk.bool
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
class ToolPalette internal constructor(
	internal val toolPalettePointer: CPointer<GtkToolPalette>
) : Container(toolPalettePointer.reinterpret()) {
	constructor() : this(gtk_tool_palette_new()!!.reinterpret())


	fun getIsExclusive(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	fun setIsExclusive(group: ToolItemGroup, isExclusive: Boolean) {
		gtk_tool_palette_set_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer,
			isExclusive.gtk
		)
	}

	fun getExpand(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_expand(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	fun setExpand(group: ToolItemGroup, expand: Boolean) =
		gtk_tool_palette_set_expand(
			toolPalettePointer,
			group.toolItemGroupPointer,
			expand.gtk
		)

	fun getGroupPosition(group: ToolItemGroup): Int =
		gtk_tool_palette_get_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer
		)

	fun setGroupPosition(group: ToolItemGroup, position: Int) {
		gtk_tool_palette_set_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer,
			position
		)
	}

	fun unsetIconSize() {
		gtk_tool_palette_unset_icon_size(toolPalettePointer)
	}

	fun unsetStyle() {
		gtk_tool_palette_unset_style(toolPalettePointer)
	}

	var style: ToolbarStyle?
		get() = ToolbarStyle.valueOf(
			gtk_tool_palette_get_style(
				toolPalettePointer
			)
		)
		set(value) =
			if (value == null)
				unsetStyle()
			else
				gtk_tool_palette_set_style(toolPalettePointer, value!!.gtk)

	var iconSize: IconSize?
		get() = IconSize.valueOf(
			gtk_tool_palette_get_icon_size(toolPalettePointer)
		)
		set(value) =
			if (value == null)
				unsetIconSize()
			else
				gtk_tool_palette_set_icon_size(
					toolPalettePointer,
					value.gtk
				)

	fun addDragDest() {
		TODO("gtk_tool_palette_add_drag_dest")
	}

	fun getDragItem() {
		TODO("gtk_tool_palette_get_drag_item")
	}

	fun getDragTargetGroup() {
		TODO("gtk_tool_palette_get_drag_target_group")
	}

	fun getDragTargetItem() {
		TODO("gtk_tool_palette_get_drag_target_item")
	}

	fun paletteGetDropGroup() {
		TODO("gtk_tool_palette_get_drop_group")
	}

	fun getDropItem() {
		TODO("gtk_tool_palette_get_drop_item")
	}

	fun getDragSource() {
		TODO("gtk_tool_palette_set_drag_source")
	}

	enum class DragTargets(
		val key: Int,
		internal val gtk: GtkToolPaletteDragTargets
	) {
		ITEMS(0, GTK_TOOL_PALETTE_DRAG_ITEMS),
		GROUPS(1, GTK_TOOL_PALETTE_DRAG_GROUPS);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GtkToolPaletteDragTargets) =
				values().find { it.gtk == gtk }
		}
	}

}