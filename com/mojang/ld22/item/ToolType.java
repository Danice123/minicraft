package com.mojang.ld22.item;

public class ToolType {
	public static ToolType[] list = new ToolType[64];
	public static ToolType shovel = new ToolType(0, "Shvl", 0);
	public static ToolType hoe = new ToolType(1, "Hoe", 1);
	public static ToolType sword = new ToolType(2, "Swrd", 2);
	public static ToolType pickaxe = new ToolType(3, "Pick", 3);
	public static ToolType axe = new ToolType(4, "Axe", 4);

	public final String name;
	public final int sprite;

	private ToolType(int id, String name, int sprite) {
		this.name = name;
		this.sprite = sprite;
		list[id] =  this;
	}
}
