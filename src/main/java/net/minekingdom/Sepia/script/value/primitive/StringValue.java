package net.minekingdom.Sepia.script.value.primitive;

import net.minekingdom.Sepia.script.value.Value;

public class StringValue extends Value {

    private String value;
    
    public StringValue(String value) {
        super(value);
        this.value = value;
    }
    
    public StringValue concatenate(Value v) {
        return new StringValue(this.value + v);
    }

    public Value preConcatenate(Value v) {
        return new StringValue(v + this.value);
    }
}
