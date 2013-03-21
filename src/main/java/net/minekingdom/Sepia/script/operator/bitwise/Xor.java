package net.minekingdom.Sepia.script.operator.bitwise;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.BooleanValue;

public class Xor extends Operator  {

    public Xor() {
        super("^", 2, 9);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        //TODO: bitwise xor on numbers
        return ((BooleanValue) tokens[0]).xor((BooleanValue) tokens[1]);
    }
}
