package net.minekingdom.Sepia.script;

import net.minekingdom.Sepia.script.value.Value;

public class Variable extends Token {
    
    private Value value;
    
    public Variable(String name) {
        super(name);
    }
    
    public void setValue(Value value) {
        this.value = value;
    }
    
    public Value getValue() {
        if (value == null) {
            throw new UnsupportedOperationException("Variable " + this + " has no value !");
        }
        return value;
    }
}
