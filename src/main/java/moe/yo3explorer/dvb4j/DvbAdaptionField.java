package moe.yo3explorer.dvb4j;

class DvbAdaptionField
{

    public int length;
    public long pcr;
    public int discontinuity;
    public int randomAccess;
    public int priorityIndic;
    public int pcrPresent;
    public int opcrPresent;
    public int splicingPointPresent;
    public int transportPrivateDataPresent;
    public int adaptionFieldExtensionPresent;
    public long opcr;
    public byte splicingPoint;
    public int transportPrivateDataLength;
    public byte[] transportPrivateData;
}
