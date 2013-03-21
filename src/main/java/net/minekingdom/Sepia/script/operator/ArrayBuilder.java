package net.minekingdom.Sepia.script.operator;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.value.ArrayValue;
import net.minekingdom.Sepia.script.value.Value;

import org.spout.api.command.CommandSource;

public class ArrayBuilder extends Operator {

    public ArrayBuilder(int size) {
        super("[" + size + "]", size, 1);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != getNumberOfArguments())
            throw new IllegalArgumentException("Operator must be invoked on " + getNumberOfArguments() + " parameters.");
        
        evaluate(source, tokens);
        
        Value[] array = new Value[getNumberOfArguments()];
        System.arraycopy(tokens, 0, array, 0, array.length);
        
        return new ArrayValue(array);
    }

}
