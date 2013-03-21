package net.minekingdom.Sepia.script.operator.comparison;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.NumericValue;

public class LessEquals extends Operator  {

    public LessEquals() {
        super("<=", 2, 6);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        return ((NumericValue) tokens[0]).lessEquals((NumericValue) tokens[1]);
    }
}