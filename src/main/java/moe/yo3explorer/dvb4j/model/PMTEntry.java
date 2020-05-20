package moe.yo3explorer.dvb4j.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PMTEntry
{
    public PMTEntry(int type, int pid) {
        this.type = type;
        this.pid = pid;
        this.tags = new ArrayList<>();
    }

    private int type;
    private int pid;
    private ArrayList<Descriptor> tags;

    public void addDescriptor(Descriptor pmtTag) {
        tags.add(pmtTag);
    }

    public List<Descriptor> getTags()
    {
        return Collections.unmodifiableList(tags);
    }

    public int getType() {
        return type;
    }

    public int getPid() {
        return pid;
    }
}
