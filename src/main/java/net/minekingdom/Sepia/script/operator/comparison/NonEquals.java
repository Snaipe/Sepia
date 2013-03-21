package net.minekingdom.Sepia.script.operator.comparison;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;

public class NonEquals extends Operator  {

    public NonEquals() {
        super("!=", 2, 7);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        return ((Value) tokens[0]).isNonEqual((Value) tokens[1]);
    }
}