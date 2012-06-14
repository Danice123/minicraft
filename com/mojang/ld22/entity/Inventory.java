package com.mojang.ld22.entity;

import java.util.ArrayList;
import java.util.List;

import com.mojang.ld22.item.FurnitureItem;
import com.mojang.ld22.item.Item;
import com.mojang.ld22.item.PowerGloveItem;
import com.mojang.ld22.item.ResourceItem;
import com.mojang.ld22.item.ToolItem;
import com.mojang.ld22.item.ToolType;
import com.mojang.ld22.item.resource.Resource;
import com.mojang.ld22.nbt.NBTCompound;
import com.mojang.ld22.nbt.NBTList;

public class Inventory {
	public List<Item> items = new ArrayList<Item>();

	public void add(Item item) {
		add(items.size(), item);
	}

	public void add(int slot, Item item) {
		if (item instanceof ResourceItem) {
			ResourceItem toTake = (ResourceItem) item;
			ResourceItem has = findResource(toTake.resource);
			if (has == null) {
				items.add(slot, toTake);
			} else {
				has.count += toTake.count;
			}
		} else {
			items.add(slot, item);
		}
	}

	private ResourceItem findResource(Resource resource) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof ResourceItem) {
				ResourceItem has = (ResourceItem) items.get(i);
				if (has.resource == resource) return has;
			}
		}
		return null;
	}

	public boolean hasResources(Resource r, int count) {
		ResourceItem ri = findResource(r);
		if (ri == null) return false;
		return ri.count >= count;
	}

	public boolean removeResource(Resource r, int count) {
		ResourceItem ri = findResource(r);
		if (ri == null) return false;
		if (ri.count < count) return false;
		ri.count -= count;
		if (ri.count <= 0) items.remove(ri);
		return true;
	}

	public int count(Item item) {
		if (item instanceof ResourceItem) {
			ResourceItem ri = findResource(((ResourceItem)item).resource);
			if (ri!=null) return ri.count;
		} else {
			int count = 0;
			for (int i=0; i<items.size(); i++) {
				if (items.get(i).matches(item)) count++;
			}
			return count;
		}
		return 0;
	}
	
	public NBTList write() {
		NBTList out = new NBTList();
		for (int i = 0; i < items.size(); i++) {
			out.appendTag(items.get(i).write());
		}
		return out;
	}
	
	public void read(NBTList list) {
		for (int i = 0; i < list.tagCount(); i++) {
			add(getItem((NBTCompound) list.tagAt(i)));
		}
	}
	
	private Item getItem(NBTCompound tag) {
		String name = tag.getString("name");
		//furnature
		if (name.equals("Anvil")) {
			return new FurnitureItem(new Anvil());
		}
		if (name.equals("Chest")) {
			return new FurnitureItem(new Chest());
		}
		if (name.equals("Furnace")) {
			return new FurnitureItem(new Furnace());
		}
		if (name.equals("Lanturn")) {
			return new FurnitureItem(new Lantern());
		}
		if (name.equals("Oven")) {
			return new FurnitureItem(new Oven());
		}
		if (name.equals("Workbench")) {
			return new FurnitureItem(new Workbench());
		}
		//power glove
		if (name.equals("Pow glove")) {
			return new PowerGloveItem();
		}
		//Resource
		for (int i = 0; Resource.list[i] != null && i < 255; i++) {
			if (Resource.list[i].name.equals(name)) {
				ResourceItem ret = new ResourceItem(Resource.list[i]);
				ret.count = tag.getInteger("amount");
				return ret;
			}
		}
		//Tool
		for (int i = 0; ToolType.list[i] != null && i < 64; i++) {
			if (ToolType.list[i].name.equals(name)) {
				return new ToolItem(ToolType.list[i], tag.getInteger("lvl"));
			}
		}
		return null;
	}
}
