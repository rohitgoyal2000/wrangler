package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ByteSize implements Token {
    private final long bytes;

    public ByteSize(String value) {
        this.bytes = parseBytes(value);
    }

    private long parseBytes(String value) {
        value = value.toLowerCase();
        if (value.endsWith("kb")) {
            return (long) (Double.parseDouble(value.replace("kb", "")) * 1024);
        } else if (value.endsWith("mb")) {
            return (long) (Double.parseDouble(value.replace("mb", "")) * 1024 * 1024);
        } else if (value.endsWith("gb")) {
            return (long) (Double.parseDouble(value.replace("gb", "")) * 1024 * 1024 * 1024);
        } else if (value.endsWith("b")) {
            return (long) Double.parseDouble(value.replace("b", ""));
        } else {
            throw new IllegalArgumentException("Invalid byte size: " + value);
        }
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public Object value() {
        return bytes;  // <--- Return the parsed bytes here
    }

    @Override
    public TokenType type() {
        return TokenType.BYTE_SIZE;  // <--- Return proper token type
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(bytes);  // <--- Convert bytes into JSON
    }
}
