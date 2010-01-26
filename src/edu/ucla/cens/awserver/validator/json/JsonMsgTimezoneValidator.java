package edu.ucla.cens.awserver.validator.json;

import org.json.JSONObject;

import edu.ucla.cens.awserver.datatransfer.AwRequest;
import edu.ucla.cens.awserver.util.DateUtils;
import edu.ucla.cens.awserver.util.JsonUtils;
import edu.ucla.cens.awserver.validator.AwRequestAnnotator;

/**
 * Validates the timezone element from an AW JSON message.
 * 
 * @author selsky
 */
public class JsonMsgTimezoneValidator extends AbstractAnnotatingJsonObjectValidator {
	private String _key = "timezone";
		
	/**
     * @throws IllegalArgumentException if the provded AwRequestAnnotator is null
	 */
	public JsonMsgTimezoneValidator(AwRequestAnnotator awRequestAnnotator) {
		super(awRequestAnnotator);
	}
	
	/**
	 * @return true if the value returned from the JSONObject for the key "timezone" exists and is a valid timezone.
	 * @return false otherwise
	 */
	public boolean validate(AwRequest request, JSONObject jsonObject) {		 
		String tz = JsonUtils.getStringFromJson(jsonObject, _key);
		
		if(null == tz) {
			getAnnotator().annotate(request, "timezone in message is null");
			return false;
		}
		
		if(! DateUtils.isValidTimezone(tz)) {
			getAnnotator().annotate(request, "invalid timezone: " + tz);
			return false;
		}
		
		return true;
	}
}