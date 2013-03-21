package net.minekingdom.Sepia.script.value.primitive;

import net.minekingdom.Sepia.script.value.Value;

public abstract class NumericValue extends Value {

    public NumericValue(Object value) {
        super(value);
    }
    
    public abstract NumericValue add(NumericValue v);
    public abstract NumericValue subtract(NumericValue v);
    public abstract NumericValue multiply(NumericValue v);
    public abstract NumericValue divide(NumericValue v);
    public abstract NumericValue mod(NumericValue v);
    public abstract NumericValue negative();
    
    public abstract BooleanValue greater(NumericValue v);
    public abstract BooleanValue greaterEquals(NumericValue v);
    public abstract BooleanValue less(NumericValue v);
    public abstract BooleanValue lessEquals(NumericValue v);
}
