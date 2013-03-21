package net.minekingdom.Sepia.script.value;

import java.util.List;

import org.spout.api.math.Vector3;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.value.primitive.BooleanValue;
import net.minekingdom.Sepia.script.value.primitive.DecimalValue;
import net.minekingdom.Sepia.script.value.primitive.IntegerValue;
import net.minekingdom.Sepia.script.value.primitive.StringValue;
import net.minekingdom.Sepia.script.value.structure.VectorValue;

public class Value extends Token {

    public Value(Object value) {
        super(value.toString());
    }
    
    public BooleanValue isEqual(Value v) {
        return new BooleanValue(this.equals(v));
    }
    
    public BooleanValue isNonEqual(Value v) {
        return new BooleanValue(!this.equals(v));
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Value && toString().equals(o.toString());
    }
    
    public static Value getValue(Object value) {
        if (value instanceof Long) { 
            return new IntegerValue((Long) value);
        } else if (value instanceof Integer) {
            return new IntegerValue((Integer) value);
        } else if (value instanceof Float) { 
            return new DecimalValue((Float) value);
        } else if (value instanceof Double) {
            return new DecimalValue((Double) value);
        } else if (value instanceof Boolean) { 
            return new BooleanValue((Boolean) value);
        } else if (value instanceof Vector3) {
            Vector3 v = (Vector3) value;
            return new VectorValue(v.getX(), v.getY(), v.getZ());
        } else {
            return new StringValue(value.toString());
        }
    }
    
    public static Class<?> fromString(String str) {
        if (str.matches("[0-9]+")) {
            return Long.class;
        } else if (str.matches("[0-9\\.]+")) {
            return Double.class;
        } else if (str.matches("\\[[^\\]]+\\]")) {
            return List.class;
        } else if (str.equals("true") || str.equals("false")) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }
}
