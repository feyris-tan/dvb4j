package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AvcVideoDescriptor implements Descriptor
{
    private int profile_idc;
    private boolean constraint_set0_flag;
    private boolean constraint_set1_flag;
    private boolean constraint_set2_flag;
    private boolean constraint_set3_flag;
    private int avc_compatible_flags;
    private int level_idc;
    private boolean avc_still_present;
    private boolean avc_24hour;

    @Override
    public int getTag() {
        return 40;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        profile_idc = (int)buffer[0] & 0xff;
        constraint_set0_flag = (((int)buffer[1]) & 0x80) != 0;
        constraint_set1_flag = (((int)buffer[1]) & 0x40) != 0;
        constraint_set2_flag = (((int)buffer[1]) & 0x20) != 0;
        constraint_set3_flag = (((int)buffer[1]) & 0x10) != 0;
        avc_compatible_flags = (int)buffer[1] & 0x0f;
        level_idc = (int)buffer[2] & 0xff;
        avc_still_present = ((int)buffer[3] & 0x80) != 0;
        avc_24hour = ((int)buffer[3] & 0x40) != 0;
    }

    public int getProfile_idc() {
        return profile_idc;
    }

    public boolean isConstraint_set0_flag() {
        return constraint_set0_flag;
    }

    public boolean isConstraint_set1_flag() {
        return constraint_set1_flag;
    }

    public boolean isConstraint_set2_flag() {
        return constraint_set2_flag;
    }

    public boolean isConstraint_set3_flag() {
        return constraint_set3_flag;
    }

    public int getAvc_compatible_flags() {
        return avc_compatible_flags;
    }

    public int getLevel_idc() {
        return level_idc;
    }

    public boolean isAvc_still_present() {
        return avc_still_present;
    }

    public boolean isAvc_24hour() {
        return avc_24hour;
    }

    @Override
    public String toString() {
        return "UnknownDescriptor40{" +
                "profile_idc=" + profile_idc +
                ", avc_compatible_flags=" + avc_compatible_flags +
                ", level_idc=" + level_idc +
                '}';
    }
}
