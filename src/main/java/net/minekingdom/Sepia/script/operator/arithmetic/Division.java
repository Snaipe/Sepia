package net.minekingdom.Sepia.script.operator.arithmetic;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.NumericValue;
import net.minekingdom.Sepia.script.value.structure.VectorValue;

public class Division extends Operator {

    public Division() {
        super("/", 2, 3);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        if (tokens[0] instanceof VectorValue) {
            return ((VectorValue) tokens[0]).divide((NumericValue) tokens[1]);
        } else {
            return ((NumericValue) tokens[0]).divide((NumericValue) tokens[1]);
        }
    }
}
