package net.minekingdom.Sepia.script.operator.logical;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.BooleanValue;

public class And extends Operator  {

    public And() {
        super("&&", 2, 11);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        return ((BooleanValue) tokens[0]).and((BooleanValue) tokens[1]);
    }
}
