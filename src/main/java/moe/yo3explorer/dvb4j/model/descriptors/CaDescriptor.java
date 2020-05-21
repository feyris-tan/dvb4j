package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.Objects;

public class CaDescriptor implements Descriptor {
    private int caSystemId;
    private int caPid;

    @Override
    public int getTag() {
        return 9;
    }

    /*
    I couldn't find this in the standards.
    This information was gathered from: https://books.google.de/books?id=vwUrAwAAQBAJ&pg=PT516&lpg=PT516&dq=%22CA+System+ID%22+%2B+%22CA+PID%22&source=bl&ots=fI79EzWHOQ&sig=ACfU3U1-XB2G637HELRZ0y6Zk7zXEARDkA&hl=de&sa=X&ved=2ahUKEwjqopX3lsPpAhVvBWMBHbH2BIIQ6AEwAHoECEUQAQ#v=onepage&q=%22CA%20System%20ID%22%20%2B%20%22CA%20PID%22&f=false
     */
    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        caSystemId = wrap.getShort();
        caPid = wrap.getShort() & 0x1FFF;
    }

    public int getCaSystemId() {
        return caSystemId;
    }

    public int getCaPid() {
        return caPid;
    }

    // https://en.wikipedia.org/wiki/Conditional_access#Digital_systems
    private String getHumanReadableCaSystemId()
    {
        if (caSystemId >= 0x1703 && caSystemId <= 0x1721)
            return "VCAS";
        if (caSystemId >= 0x1723 && caSystemId <= 0x1761)
            return "VCAS";
        if (caSystemId >= 0x1763 && caSystemId <= 0x17ff)
            return "VCAS";

        switch (caSystemId)
        {
            case 0x4AEB:
                return "Abel Quintic";
            case 0x4AF0:
            case 0x4AF2:
            case 0x4B4B:
                return "ABV CAS";
            case 0x4AFC:
                return "Panaccess";
            case 0x4B19:
                return "RCAS";
            case 0x4B30:
            case 0x4B31:
                return "ViCAS";
            case 0x4800:
                return "Accessgate";
            case 0x4A20:
                return "AlphaCrypt";
            case 0x5601:
            case 0x5602:
            case 0x5603:
            case 0x5604:
            case 0x1700:
            case 0x1701:
                return "VCAS";
            case 0x2600:
                return "BISS";
            case 0x4900:
                return "China Crypt";
            case 0x22F0:
                return "Codicrypt";
            case 0x4AEA:
                return "Cryptoguard";
            case 0x0B00:
                return "Conax CAS 7.5";
            case 0x0B01:
            case 0x0B02:
            case 0x0B03:
            case 0x0B04:
            case 0x0B05:
            case 0x0B06:
            case 0x0B07:
                return "Conax CAS 3";
            case 0x4AE4:
                return "CoreCrypt";
            case 0x4347:
                return "CryptOn";
            case 0x0D00:
            case 0x0D02:
            case 0x0D03:
            case 0x0D05:
            case 0x0D07:
            case 0x0D20:
                return "Cryptoworks";
            case 0x4ABF:
                return "CTI-CAS";
            case 0x0700:
                return "DigiCipher 2";
            case 0x4A70:
                return "DreamCrypt";
            case 0x4A10:
                return "EasyCas";
            case 0x2719:
            case 0xEAD0:
                return "InCrypt";
            case 0x0464:
                return "EuroDec";
            case 0x5548:
                return "Gospell VisionCrypt";
            case 0x5501:
                return "Griffin";
            case 0x5581:
                return "Bulcrypt";
            case 0x0602:
            case 0x0604:
            case 0x0606:
            case 0x0608:
            case 0x0622:
            case 0x0626:
            case 0x0664:
            case 0x0614:
                return "Irdeto 2";
            case 0x0692:
                return "Irdeto 3";
            case 0x4AA1:
                return "KeyFly";
            case 0x0100:
                return "Mediaguard";
            case 0x1800:
            case 0x1810:
            case 0x1830:
                return "Nagravision";
            case 0x1702:
            case 0x1722:
            case 0x1762:
                return "Nagravision Aladin";
            case 0x1801:
                return "Nagravision ELK/Merlin/Aladin/Carmageddon";
            case 0x4A02:
                return "Tongfang";
            case 0x4AD4:
                return "OmniCrypt";
            case 0x0E00:
                return "PowerVu";
            case 0x1000:
                return "RAS";
            case 0x4AC1:
                return "Latens";
            case 0xA101:
                return "RosCrypt-M";
            case 0x4A60:
            case 0x4A61:
            case 0x4A63:
                return "SkyCrypt";
            case 0x4A80:
                return "ThalesCrypt";
            case 0x0500:
                return "Viaccess";
            case 0x0930:
            case 0x0942:
                return "NDS Videoguard";
            case 0x0911:
            case 0x0960:
                return "NDS VideoGuard 2";
            case 0x0919:
            case 0x0961:
            case 0x9AC:
                return "NDS VideoGuard 3";
            case 0x0927:
            case 0x0963:
            case 0x093b:
            case 0x09CD:
                return "NDS VideoGuard 4";
            case 0x4AD0:
            case 0x4AD1:
                return "X-Crypt";
            case 0x4AE0:
            case 0x4AE1:
            case 0x7BE1:
                return "Dre-Crypt";
            case 0x092B:
                return "News Datacom (???)";
            case 0x0656:
            case 0x06f8:
                return "Irdeto";
            default:
                return String.format("Unknown CA System (%04X)",caSystemId);
        }
    }

    @Override
    public String toString() {
        return "CaDescriptor{" +
                "caSystemId=" + getHumanReadableCaSystemId() +
                ", caPid=" + caPid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaDescriptor)) return false;
        CaDescriptor that = (CaDescriptor) o;
        return getCaSystemId() == that.getCaSystemId() &&
                getCaPid() == that.getCaPid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaSystemId(), getCaPid());
    }
}
