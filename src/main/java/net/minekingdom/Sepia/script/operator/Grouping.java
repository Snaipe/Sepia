package net.minekingdom.Sepia.script.operator;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.value.Value;

public class Grouping extends Operator {
    
    public static final Grouping LEFT_PARENTHESIS  = new LeftParenthesis();
    public static final Grouping RIGHT_PARENTHESIS = new RightParenthesis();
    
    private Grouping(String token) {
        super(token, 0, 1);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        throw new UnsupportedOperationException("Grouping operators cannot be invoked.");
    }
    
    public static class LeftParenthesis extends Grouping {
        private LeftParenthesis() {
            super("(");
        }
    }
    
    public static class RightParenthesis extends Grouping {
        private RightParenthesis() {
            super(")");
        }
    }

}
