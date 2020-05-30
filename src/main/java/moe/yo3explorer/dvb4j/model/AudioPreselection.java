package moe.yo3explorer.dvb4j.model;

public class AudioPreselection
{
    private final boolean audioDescription;
    private final boolean spokenSubtitles;
    private final boolean dialogueEnhancement;
    private final boolean interactivityEnabled;
    private final boolean languageCodePresent;
    private final boolean textLabelPresent;
    private final boolean multiStreamInfoPresent;
    private final boolean futureExtension;
    private final String iso639languageCode;
    private final Integer messageId;
    private final byte[] auxComponents;
    private final byte[] futureExtensionData;

    public AudioPreselection(boolean audioDescription, boolean spokenSubtitles, boolean dialogueEnhancement, boolean interactivityEnabled, boolean languageCodePresent, boolean textLabelPresent, boolean multiStreamInfoPresent, boolean futureExtension, String iso639languageCode, Integer messageId, byte[] auxComponents, byte[] futureExtensionData) {

        this.audioDescription = audioDescription;
        this.spokenSubtitles = spokenSubtitles;
        this.dialogueEnhancement = dialogueEnhancement;
        this.interactivityEnabled = interactivityEnabled;
        this.languageCodePresent = languageCodePresent;
        this.textLabelPresent = textLabelPresent;
        this.multiStreamInfoPresent = multiStreamInfoPresent;
        this.futureExtension = futureExtension;
        this.iso639languageCode = iso639languageCode;
        this.messageId = messageId;
        this.auxComponents = auxComponents;
        this.futureExtensionData = futureExtensionData;
    }

    public boolean isAudioDescription() {
        return audioDescription;
    }

    public boolean isSpokenSubtitles() {
        return spokenSubtitles;
    }

    public boolean isDialogueEnhancement() {
        return dialogueEnhancement;
    }

    public boolean isInteractivityEnabled() {
        return interactivityEnabled;
    }

    public boolean isLanguageCodePresent() {
        return languageCodePresent;
    }

    public boolean isTextLabelPresent() {
        return textLabelPresent;
    }

    public boolean isMultiStreamInfoPresent() {
        return multiStreamInfoPresent;
    }

    public boolean isFutureExtension() {
        return futureExtension;
    }

    @Override
    public String toString() {
        return "AudioPreselection{" +
                "audioDescription=" + audioDescription +
                ", spokenSubtitles=" + spokenSubtitles +
                ", dialogueEnhancement=" + dialogueEnhancement +
                ", interactivityEnabled=" + interactivityEnabled +
                ", languageCodePresent=" + languageCodePresent +
                ", textLabelPresent=" + textLabelPresent +
                ", multiStreamInfoPresent=" + multiStreamInfoPresent +
                ", futureExtension=" + futureExtension +
                '}';
    }
}
