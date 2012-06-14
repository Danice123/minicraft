package com.mojang.ld22.nbt;
import java.io.*;

public class NBTFloat extends NBTBase
{
    /** The float value for the tag. */
    public float data;

    public NBTFloat(String par1Str)
    {
        super(par1Str);
    }

    public NBTFloat(String par1Str, float par2)
    {
        super(par1Str);
        data = par2;
    }

    /**
     * Write the actual data contents of the tag, implemented in NBT extension classes
     */
    void write(DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeFloat(data);
    }

    /**
     * Read the actual data contents of the tag, implemented in NBT extension classes
     */
    void load(DataInput par1DataInput) throws IOException
    {
        data = par1DataInput.readFloat();
    }

    /**
     * Gets the type byte for the tag.
     */
    public byte getId()
    {
        return 5;
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
        return new NBTFloat(getName(), data);
    }

    public boolean equals(Object par1Obj)
    {
        if (super.equals(par1Obj))
        {
            NBTFloat nbttagfloat = (NBTFloat)par1Obj;
            return data == nbttagfloat.data;
        }
        else
        {
            return false;
        }
    }
}
