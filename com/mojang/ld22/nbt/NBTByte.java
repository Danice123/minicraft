package com.mojang.ld22.nbt;
import java.io.*;

public class NBTByte extends NBTBase
{
    /** The byte value for the tag. */
    public byte data;

    public NBTByte(String par1Str)
    {
        super(par1Str);
    }

    public NBTByte(String par1Str, byte par2)
    {
        super(par1Str);
        data = par2;
    }

    /**
     * Write the actual data contents of the tag, implemented in NBT extension classes
     */
    void write(DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeByte(data);
    }

    /**
     * Read the actual data contents of the tag, implemented in NBT extension classes
     */
    void load(DataInput par1DataInput) throws IOException
    {
        data = par1DataInput.readByte();
    }

    /**
     * Gets the type byte for the tag.
     */
    public byte getId()
    {
        return 1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(data).toString();
    }

    public boolean equals(Object par1Obj)
    {
        if (super.equals(par1Obj))
        {
            NBTByte nbttagbyte = (NBTByte)par1Obj;
            return data == nbttagbyte.data;
        }
        else
        {
            return false;
        }
    }

    /**
     * Creates a clone of the tag.
     */
    public NBTBase copy()
    {
        return new NBTByte(getName(), data);
    }
}
