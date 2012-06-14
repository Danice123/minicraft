package com.mojang.ld22.nbt;
import java.io.*;

public class NBTInt extends NBTBase
{
    /** The integer value for the tag. */
    public int data;

    public NBTInt(String par1Str)
    {
        super(par1Str);
    }

    public NBTInt(String par1Str, int par2)
    {
        super(par1Str);
        data = par2;
    }

    /**
     * Write the actual data contents of the tag, implemented in NBT extension classes
     */
    void write(DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeInt(data);
    }

    /**
     * Read the actual data contents of the tag, implemented in NBT extension classes
     */
    void load(DataInput par1DataInput) throws IOException
    {
        data = par1DataInput.readInt();
    }

    /**
     * Gets the type byte for the tag.
     */
    public byte getId()
    {
        return 3;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(data).toString();
    }

    /**
     * Creates a clone of the tag.
     */
    public NBTBase copy()
    {
        return new NBTInt(getName(), data);
    }

    public boolean equals(Object par1Obj)
    {
        if (super.equals(par1Obj))
        {
            NBTInt nbttagint = (NBTInt)par1Obj;
            return data == nbttagint.data;
        }
        else
        {
            return false;
        }
    }
}
