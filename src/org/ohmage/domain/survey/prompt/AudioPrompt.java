package org.ohmage.domain.survey.prompt;

import org.ohmage.domain.exception.InvalidArgumentException;
import org.ohmage.domain.survey.condition.Condition;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * A prompt for the user to submit an audio file.
 * </p>
 *
 * @author John Jenkins
 */
public class AudioPrompt extends MediaPrompt {
    /**
     * The string type of this survey item.
     */
    public static final String SURVEY_ITEM_TYPE = "audio_prompt";

    /**
     * The JSON key for the maximum duration.
     */
    public static final String JSON_KEY_MAX_DURATION = "max_duration";

    /**
     * The maximum allowed duration of an audio file in milliseconds.
     */
    @JsonProperty(JSON_KEY_MAX_DURATION)
    private final Long maxDuration;

    /**
     * Creates a new audio prompt.
     *
     * @param id
     *        The survey-unique identifier for this prompt.
     *
     * @param condition
     *        The condition on whether or not to show this prompt.
     *
     * @param text
     *        The text to display to the user.
     *
     * @param displayLabel
     *        The text to use as a short name in visualizations.
     *
     * @param skippable
     *        Whether or not this prompt may be skipped.
     *
     * @param defaultResponse
     *        The default response for this prompt or null if a default is not
     *        allowed.
     *
     * @param maxDuration
     *        The maximum allowed duration in milliseconds for an audio file.
     *
     * @throws InvalidArgumentException
     *         A parameter was invalid.
     */
    @JsonCreator
    public AudioPrompt(
        @JsonProperty(JSON_KEY_SURVEY_ITEM_ID) final String surveyItemId,
        @JsonProperty(JSON_KEY_CONDITION) final Condition condition,
        @JsonProperty(JSON_KEY_TEXT) final String text,
        @JsonProperty(JSON_KEY_DISPLAY_LABEL) final String displayLabel,
        @JsonProperty(JSON_KEY_SKIPPABLE) final boolean skippable,
        @JsonProperty(JSON_KEY_DEFAULT_RESPONSE) final String defaultResponse,
        @JsonProperty(JSON_KEY_MAX_DURATION) final Long maxDuration)
        throws InvalidArgumentException {

        super(
            surveyItemId,
            condition,
            text,
            displayLabel,
            skippable,
            defaultResponse);

        this.maxDuration = maxDuration;
    }

    /*
     * (non-Javadoc)
     * @see org.ohmage.domain.survey.prompt.MediaPrompt#validateResponse(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public void validateResponse(final MultipartFile response)
        throws InvalidArgumentException {

        // For now, we are not placing any audio-specific limitations on their
        // responses.

//        // The InputStream needs to be able to reset itself, so wrap it in an
//        // InputStream that is capable of doing that.
//        InputStream input;
//        try {
//            input = new BufferedInputStream(response.getInputStream());
//        }
//        catch(IOException e) {
//            throw new IllegalStateException("The media could not be read.", e);
//        }
//
//        // Validate the audio.
//        AudioInputStream audio;
//        try {
//            audio = AudioSystem.getAudioInputStream(input);
//        }
//        catch(IOException e) {
//            throw new IllegalStateException("The media could not be read.", e);
//        }
//        catch(UnsupportedAudioFileException e) {
//            throw
//                new InvalidArgumentException(
//                    "The audio file was not a valid audio file: " +
//                        getSurveyItemId(),
//                    e);
//        }
//
//        // Validate that the audio file is not longer than allowed.
//        if(maxDuration != null) {
//            // Get the duration in seconds.
//            double durationSeconds =
//                ((double) audio.getFrameLength()) /
//                    audio.getFormat().getFrameRate();
//
//            if((durationSeconds * 1000) > maxDuration) {
//                throw
//                    new InvalidArgumentException(
//                        "The audio file is longer than the maximum allowed " +
//                            "duration of '" +
//                            maxDuration +
//                            "' milliseconds: " +
//                            getSurveyItemId());
//            }
//        }
    }
}