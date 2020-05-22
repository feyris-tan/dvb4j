package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptors.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DescriptorDecoder
{
    private DescriptorDecoder()
    {
        attachDescriptorType(MaximumBitrateDescriptor.class);
        attachDescriptorType(SmoothingBufferDescriptor.class);
        attachDescriptorType(SystemClockDescriptor.class);
        attachDescriptorType(AvcVideoDescriptor.class);
        attachDescriptorType(DataStreamAlignmentDescriptor.class);
        attachDescriptorType(Iso639LanguageDescriptor.class);
        attachDescriptorType(Ac3Descriptor.class);
        attachDescriptorType(CaDescriptor.class);
        attachDescriptorType(ServiceDescriptor.class);
        attachDescriptorType(EnhancedAc3Descriptor.class);
        attachDescriptorType(Mpeg2AacAudioDescriptor.class);
        attachDescriptorType(PrivateDataSpecifierDescriptor.class);
        attachDescriptorType(LocalTimeOffsetDescriptor.class);
        attachDescriptorType(BouquetNameDescriptor.class);
        attachDescriptorType(LinkageDescriptor.class);
        attachDescriptorType(ServiceListDescriptor.class);
        attachDescriptorType(MultiplexBufferUtilizationDescriptor.class);
        attachDescriptorType(StreamIdentifierDescriptor.class);
        attachDescriptorType(VideoStreamDescriptor.class);
        attachDescriptorType(AudioStreamDescriptor.class);
        attachDescriptorType(DsmCcAssociationDescriptor.class);
        attachDescriptorType(RegistrationDescriptor.class);
        attachDescriptorType(SubtitlingDescriptor.class);
        attachDescriptorType(CountryAvailablityDescriptor.class);
        attachDescriptorType(NetworkNameDescriptor.class);
        attachDescriptorType(SatelliteDeliverySystemDescriptor.class);
    }
    private Descriptor[] descriptors;

    private static DescriptorDecoder instance;
    public static DescriptorDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new DescriptorDecoder();
        }
        return instance;
    }

    public static Descriptor autoDecode(int descriptorId, int tableId, byte[] data)
    {
        return getInstance().decode(descriptorId,tableId,data);
    }

    @Contract(pure = true)
    private void attachDescriptorType(@NotNull Class<? extends Descriptor> descriptorType)
    {
        if (descriptors == null) {
            descriptors = new Descriptor[255];
        }

        Descriptor descriptor = createInstance(descriptorType);
        if (descriptors[descriptor.getTag()] != null)
        {
            throw new DvbException("The Descriptor's ID is used more than once.");
        }
        descriptors[descriptor.getTag()] = descriptor;
    }

    @NotNull
    private Descriptor createInstance(@NotNull Class<? extends Descriptor> descriptorType) {
        try {
            Constructor<? extends Descriptor> constructor = descriptorType.getConstructor();
            Descriptor descriptor = constructor.newInstance();
            return descriptor;
        } catch (NoSuchMethodException e) {
            throw new DvbException("The Descriptor's constructor is not available.");
        } catch (IllegalAccessException e) {
            throw new DvbException("The Descriptor's constructor is private.");
        } catch (InstantiationException e) {
            throw new DvbException("Failed to creator descriptor instance.");
        } catch (InvocationTargetException e) {
            throw new DvbException("The Descriptor's constructor threw an exception");
        }
    }

    public Descriptor decode(int descriptorId, int tableId, byte[] data)
    {
        if (descriptorId > 0xff)
            throw new IllegalArgumentException("The highest possible descriptorId is 0xFF");

        if (descriptorId >= 0x80 && descriptorId <= 0xFE)
        {
            UserDefinedDescriptor userDefinedDescriptor = new UserDefinedDescriptor(descriptorId);
            userDefinedDescriptor.readFrom(data);
            return userDefinedDescriptor;
        }

        if (descriptors[descriptorId] != null)
        {
            Class<? extends Descriptor> descriptorClass = descriptors[descriptorId].getClass();
            Descriptor instance = createInstance(descriptorClass);
            instance.readFrom(data);
            return instance;
        }
        throw new DvbException(String.format("The descriptor %d (0x%02X) is not known.",descriptorId,descriptorId));
    }
}
