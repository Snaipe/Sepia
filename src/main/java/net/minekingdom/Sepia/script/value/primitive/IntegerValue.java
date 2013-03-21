package net.minekingdom.Sepia.script.value.primitive;


public class IntegerValue extends NumericValue {
    
    private long value;

    public IntegerValue(long value) {
        super(value);
        this.value = value;
    }
    
    public long getValue() {
        return this.value;
    }

    @Override
    public NumericValue add(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new IntegerValue(((IntegerValue) v).getValue() + value);
        } else {
            return new DecimalValue(((DecimalValue) v).getValue() + value);
        }
    }

    @Override
    public NumericValue subtract(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new IntegerValue(value - ((IntegerValue) v).getValue());
        } else {
            return new DecimalValue(value - ((DecimalValue) v).getValue());
        }
    }

    @Override
    public NumericValue multiply(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new IntegerValue(((IntegerValue) v).getValue() * value);
        } else {
            return new DecimalValue(((DecimalValue) v).getValue() * value);
        }
    }

    @Override
    public NumericValue divide(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new IntegerValue(value / ((IntegerValue) v).getValue());
        } else {
            return new DecimalValue(value / ((DecimalValue) v).getValue());
        }
    }

    @Override
    public NumericValue mod(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new IntegerValue(value % ((IntegerValue) v).getValue());
        } else {
            return new DecimalValue(value % ((DecimalValue) v).getValue());
        }
    }
    
    @Override
    public NumericValue negative() {
        return new IntegerValue(-value);
    }

    @Override
    public BooleanValue greater(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new BooleanValue(value > ((IntegerValue) v).getValue());
        } else {
            return new BooleanValue(value > ((DecimalValue) v).getValue());
        }
    }

    @Override
    public BooleanValue greaterEquals(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new BooleanValue(value >= ((IntegerValue) v).getValue());
        } else {
            return new BooleanValue(value >= ((DecimalValue) v).getValue());
        }
    }

    @Override
    public BooleanValue less(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new BooleanValue(value < ((IntegerValue) v).getValue());
        } else {
            return new BooleanValue(value < ((DecimalValue) v).getValue());
        }
    }

    @Override
    public BooleanValue lessEquals(NumericValue v) {
        if (v instanceof IntegerValue) {
            return new BooleanValue(value <= ((IntegerValue) v).getValue());
        } else {
            return new BooleanValue(value <= ((DecimalValue) v).getValue());
        }
    }
}
