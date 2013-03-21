package net.minekingdom.Sepia.script.operator.arithmetic;

import java.text.ParseException;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.environment.Expression;
import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.operator.Grouping;
import net.minekingdom.Sepia.script.operator.Operator;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.NumericValue;
import net.minekingdom.Sepia.script.value.primitive.StringValue;
import net.minekingdom.Sepia.script.value.structure.VectorValue;

public class Addition extends Operator {

    public static final Operator UNARY = new Unary();

    public Addition() {
        super("+", 2, 4);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        evaluate(source, tokens);
        
        if (tokens[0] instanceof VectorValue) {
            return ((VectorValue) tokens[0]).add((VectorValue) tokens[1]);
        } else if (tokens[0] instanceof StringValue) {
            return ((StringValue) tokens[0]).concatenate((Value) tokens[1]);
        } else if (tokens[1] instanceof StringValue) {
            return ((StringValue) tokens[1]).preConcatenate((Value) tokens[0]);
        } else {
            return ((NumericValue) tokens[0]).add((NumericValue) tokens[1]);
        }
    }
    
    @Override
    public void process(Expression.ShuntingYard sy, int index) throws ParseException {
        if (sy.getLastToken() == null || sy.getLastToken() instanceof Operator || sy.getLastToken() instanceof Grouping.LeftParenthesis) {
            sy.processOperator(UNARY, index);
        } else {
            sy.processOperator(this, index);
        }
    }
    
    public static class Unary extends Operator {

        public Unary() {
            super("+_", 1, 2);
        }
        
        @Override
        public Value invoke(CommandSource source, Token... tokens) {
            if (tokens.length != 1)
                throw new IllegalArgumentException("Operator must be invoked on 1 parameter.");
            
            evaluate(source, tokens);
            
            return (Value) tokens[0];
        }
    }
}
