package net.minekingdom.Sepia.script.value.primitive;

import net.minekingdom.Sepia.script.value.Value;

public class BooleanValue extends Value {

    private boolean value;
    
    public BooleanValue(boolean value) {
        super(value);
        this.value = value;
    }
    
    public boolean getValue() {
        return this.value;
    }
    
    public BooleanValue and(BooleanValue v) {
        return new BooleanValue(value && v.value);
    }
    
    public BooleanValue or(BooleanValue v) {
        return new BooleanValue(value || v.value);
    }
    
    public BooleanValue xor(BooleanValue v) {
        return new BooleanValue(value ^ v.value);
    }
    
    public BooleanValue not() {
        return new BooleanValue(!value);
    }
}
