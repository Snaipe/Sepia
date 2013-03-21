package net.minekingdom.Sepia.script.operator.logical;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.BooleanValue;

public class Not extends Operator  {

    public Not() {
        super("!", 1, 2);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 1)
            throw new IllegalArgumentException("Operator must be invoked on 1 parameter.");
        
        evaluate(source, tokens);
        
        return ((BooleanValue) tokens[0]).not();
    }
}