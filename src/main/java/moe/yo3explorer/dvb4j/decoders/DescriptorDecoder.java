package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptors.*;
import moe.yo3explorer.dvb4j.model.extendedDescriptors.Ac4Descriptor;
import moe.yo3explorer.dvb4j.model.extendedDescriptors.AudioPreselectionDescriptor;
import moe.yo3explorer.dvb4j.model.extendedDescriptors.SupplementaryAudioDescriptor;
import moe.yo3explorer.dvb4j.model.extendedDescriptors.UriLinkageDescriptor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

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
        attachDescriptorType(ApplicationSignalingDescriptor.class);
        attachDescriptorType(TeletextDescriptor.class);
        attachDescriptorType(S2SatelliteSystemDescriptor.class);
        attachDescriptorType(DsmCcCarouseldentifierDescriptor.class);
        attachDescriptorType(DataBroadcastIdDescriptor.class);
        attachDescriptorType(ExtensionDescriptor.class);
        attachExtensionDescriptorType(SupplementaryAudioDescriptor.class);
        attachDescriptorType(ExternalEsDescriptor.class);
        attachDescriptorType(ShortEventDescriptor.class);
        attachDescriptorType(ExtendedEventDescriptor.class);
        attachDescriptorType(ComponentDescriptor.class);
        attachDescriptorType(ContentDescriptor.class);
        attachDescriptorType(PdcDescriptor.class);
        attachDescriptorType(ParentalRatingDescriptor.class);
        attachDescriptorType(CaIdentifierDescriptor.class);
        attachDescriptorType(PrivateDataIndicatorDescriptor.class);
        attachDescriptorType(TimeShiftedServiceDescriptor.class);
        attachDescriptorType(NvodReferenceDescriptor.class);
        attachDescriptorType(HevcVideoDescriptor.class);
        attachExtensionDescriptorType(Ac4Descriptor.class);
        attachExtensionDescriptorType(AudioPreselectionDescriptor.class);
        attachDescriptorType(VbiDataDescriptor.class);
        attachDescriptorType(AvcTimingAndHrdDescriptor.class);
        attachDescriptorType(DataBroadcastDescriptor.class);
        attachDescriptorType(ServiceMoveDescriptor.class);
        attachDescriptorType(ScramblingDescriptor.class);
        attachDescriptorType(StdDescriptor.class);
        attachDescriptorType(AncillaryDataDescriptor.class);
        attachExtensionDescriptorType(UriLinkageDescriptor.class);
        attachDescriptorType(AacDescriptor.class);
        attachDescriptorType(IbpDescriptor.class);
        attachDescriptorType(ShortSmoothingBufferDescriptor.class);
        attachDescriptorType(Mpeg4AudioDescriptor.class);
        attachDescriptorType(StuffingDescriptor.class);
        attachDescriptorType(ReservedForFutureUse.class);
    }

    private Descriptor[] descriptors;
    private Descriptor[] extensionDescriptors;

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

    public static int getTag(Class<? extends Descriptor> descriptorClass)
    {
        Descriptor descriptor = getInstance().createInstance(descriptorClass);
        return descriptor.getTag();
    }

    public static <T extends Descriptor> T getDescriptorFromList(@NotNull List<Descriptor> list, Class<T> descriptorType)
    {
        int tag = getInstance().createInstance(descriptorType).getTag();
        Optional<T> first = list.stream().filter(x -> x.getTag() == tag).map(x -> (T)x).findFirst();
        return first.orElse(null);
    }

    @Contract(pure = true)
    private void attachDescriptorType(@NotNull Class<? extends Descriptor> descriptorType)
    {
        if (descriptors == null) {
            descriptors = new Descriptor[256];
        }

        Descriptor descriptor = createInstance(descriptorType);
        if (descriptors[descriptor.getTag()] != null)
        {
            throw new DvbException("The Descriptor's ID is used more than once.");
        }
        descriptors[descriptor.getTag()] = descriptor;
    }

    @Contract(pure = true)
    private void attachExtensionDescriptorType(@NotNull Class<? extends Descriptor> descriptorType)
    {
        if (extensionDescriptors == null) {
            extensionDescriptors = new Descriptor[256];
        }

        Descriptor descriptor = createInstance(descriptorType);
        if (extensionDescriptors[descriptor.getTag()] != null)
        {
            throw new DvbException("The Descriptor's ID is used more than once.");
        }
        extensionDescriptors[descriptor.getTag()] = descriptor;
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

            if (descriptorId == 0x7F)
            {
                ExtensionDescriptor extensionDescriptor = (ExtensionDescriptor)instance;
                int tagExtension = extensionDescriptor.getTagExtension();
                if (tagExtension >= 0x80 && tagExtension <= 0xff)
                {
                    UserDefinedDescriptor userDefinedDescriptor = new UserDefinedDescriptor(tagExtension);
                    userDefinedDescriptor.readFrom(extensionDescriptor.getSelectorBytes());
                    return userDefinedDescriptor;
                }
                Descriptor extensionDescriptorBlueprint = extensionDescriptors[tagExtension];
                if (extensionDescriptorBlueprint == null)
                    throw new DvbException(String.format("The extension descriptor %d (0x%02X) is not known.", tagExtension, tagExtension));
                else
                    descriptorClass = extensionDescriptorBlueprint.getClass();
                instance = createInstance(descriptorClass);
                instance.readFrom(extensionDescriptor.getSelectorBytes());
            }

            return instance;
        }
        throw new DvbException(String.format("The descriptor %d (0x%02X) is not known.",descriptorId,descriptorId));
    }
}
