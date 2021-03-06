package com.armadialogcreator.gui.uicanvas;

import org.jetbrains.annotations.NotNull;

/**
 @see ControlList#set(int, CanvasControl)
 @author Kayler
 @since 08/12/2016
 */
public class ControlSet<C extends CanvasControl> {
	private final C oldControl;
	private final C newControl;
	private final int index;
	
	public ControlSet(C oldControl, C newControl, int index) {
		this.oldControl = oldControl;
		this.newControl = newControl;
		this.index = index;
	}
	
	@NotNull
	public C getOldControl() {
		return oldControl;
	}
	
	@NotNull
	public C getNewControl() {
		return newControl;
	}
	
	public int getIndex() {
		return index;
	}
}
