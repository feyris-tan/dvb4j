package moe.yo3explorer.dvb4j.model;

public interface Descriptor
{
    int getTag();
    void readFrom(byte[] buffer);
}
