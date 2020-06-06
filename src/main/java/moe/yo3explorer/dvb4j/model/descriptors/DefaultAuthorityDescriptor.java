package moe.yo3explorer.dvb4j.model.descriptors;

import org.jetbrains.annotations.NotNull;

public class DefaultAuthorityDescriptor extends UserDefinedDescriptor {
    public DefaultAuthorityDescriptor() {
        super(0x73);
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        super.readFrom(buffer);
    }
}
