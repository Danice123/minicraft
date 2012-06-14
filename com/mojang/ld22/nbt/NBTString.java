package com.mojang.ld22.nbt;
import java.io.*;

public class NBTString extends NBTBase
{
    /** The string value for the tag (cannot be empty). */
    public String data;

    public NBTString(String par1Str)
    {
        super(par1Str);
    }

    public NBTString(String par1Str, String par2Str)
    {
        super(par1Str);
        data = par2Str;

        if (par2Str == null)
        {
            throw new IllegalArgumentException("Empty string not allowed");
        }
        else
        {
            return;
        }
    }

    /**
     * Write the actual data contents of the tag, implemented in NBT extension classes
     */
    void write(DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeUTF(data);
    }

    /**
     * Read the actual data contents of the tag, implemented in NBT extension classes
     */
    void load(DataInput par1DataInput) throws IOException
    {
        data = par1DataInput.readUTF();
    }

    /**
     * Gets the type byte for the tag.
     */
    public byte getId()
    {
        return 8;
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
        return new NBTString(getName(), data);
    }

    public boolean equals(Object par1Obj)
    {
        if (super.equals(par1Obj))
        {
            NBTString nbttagstring = (NBTString)par1Obj;
            return data == null && nbttagstring.data == null || data != null && data.equals(nbttagstring.data);
        }
        else
        {
            return false;
        }
    }
}
