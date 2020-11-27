package org.dieschnittstelle.ess.wsv.interpreter.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.logging.log4j.Logger;

public class JSONObjectSerialiser {

	protected static final Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(JSONObjectSerialiser.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	private static final JsonFactory jsonfactory = new JsonFactory(mapper);

	public Object readObject(InputStream is, Type type)
			throws ObjectMappingException {
		try {
			JsonNode data = null;

			// we only make a very rough distinction between maprimitives, arrays (possibly parameterised) and objects
			// parameterised types here
			
			if (type == Boolean.TYPE) {
				data = mapper.readValue(is, BooleanNode.class);
			}
			else if (type == Integer.TYPE || type == Long.TYPE || type == Double.TYPE) {
				data = mapper.readValue(is, NumericNode.class);
			}
			else if (type == String.class) {
				data = mapper.readValue(is, TextNode.class);
			}
			else if (type instanceof ParameterizedType) {
				if (Collection.class
						.isAssignableFrom((Class) ((ParameterizedType) type)
								.getRawType())) {
					data = mapper.readValue(is, ArrayNode.class);
				}
				else {
					data = mapper.readValue(is, ObjectNode.class);
				}
			} else {
				if (Collection.class
						.isAssignableFrom((Class) type)) {
					data = mapper.readValue(is, ArrayNode.class);
				}
				else {
					data = mapper.readValue(is, ObjectNode.class);
				}
			}
			logger.info("read data: " + data);

			return JSONObjectMapper.getInstance().fromjson(data, type);
		} catch (Exception e) {
			throw new ObjectMappingException(e);
		}
	}

	public void writeObject(Object obj, OutputStream os)
			throws ObjectMappingException {
		try {
			JsonGenerator generator = jsonfactory.createGenerator(os,
					JsonEncoding.UTF8);

			generator.writeObject(JSONObjectMapper.getInstance().tojson(obj));
		} catch (Exception e) {
			throw new ObjectMappingException(e);
		}
	}

}
