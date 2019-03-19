package disttx.generic.utils;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TimeStampDeserializer extends JsonDeserializer<Timestamp>{

	@Override
	public Timestamp deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if(p != null && !StringUtils.isNotEmpty(p.getText())) {
			Long time = Long.parseLong(p.getText());
			Timestamp ts = new Timestamp(time);
			return ts;
		}
		return null;
	}
	
}