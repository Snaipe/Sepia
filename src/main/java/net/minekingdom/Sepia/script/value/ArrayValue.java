package net.minekingdom.Sepia.script.value;

public class ArrayValue extends Value {

    private Value[] value;
    
    public ArrayValue(Value[] value) {
        super(value);
            this.value = value;
    }
    
    @Override
    public String toString() {
        String output = "";
        for (Value v : value) {
            output += ", " + v;
        }
        return "[" + output.substring(2) + "]";
    }
    
    public Value get(int i) {
        return value[i];
    }
}
