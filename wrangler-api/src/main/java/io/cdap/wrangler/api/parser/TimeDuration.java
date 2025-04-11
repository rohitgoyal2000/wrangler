package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class TimeDuration implements Token {
    private final long millis;

    public TimeDuration(String value) {
        this.millis = parseMillis(value);
    }

    private long parseMillis(String value) {
        value = value.toLowerCase();
        if (value.endsWith("ms")) {
            return (long) Double.parseDouble(value.replace("ms", ""));
        } else if (value.endsWith("s")) {
            return (long) (Double.parseDouble(value.replace("s", "")) * 1000);
        } else if (value.endsWith("m")) {
            return (long) (Double.parseDouble(value.replace("m", "")) * 60 * 1000);
        } else if (value.endsWith("h")) {
            return (long) (Double.parseDouble(value.replace("h", "")) * 60 * 60 * 1000);
        } else {
            throw new IllegalArgumentException("Invalid time duration: " + value);
        }
    }

    public long getMillis() {
        return millis;
    }

    @Override
    public Object value() {
        return millis;
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(millis);
    }
}
