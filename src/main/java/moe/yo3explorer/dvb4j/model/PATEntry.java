package moe.yo3explorer.dvb4j.model;

import java.util.Objects;

public class PATEntry
{
    public PATEntry(int programNumber, int pid) {
        this.programNumber = programNumber;
        this.pid = pid;
    }

    private int programNumber, pid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PATEntry)) return false;
        PATEntry pat = (PATEntry) o;
        return programNumber == pat.programNumber &&
                pid == pat.pid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programNumber, pid);
    }

    public int getProgramNumber() {
        return programNumber;
    }

    public int getPid() {
        return pid;
    }
}
