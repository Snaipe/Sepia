package net.minekingdom.Sepia.script.operator;

import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.value.ArrayValue;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.IntegerValue;
import net.minekingdom.Sepia.script.value.structure.StructureValue;

public class MemberAccess extends Operator {
    
    public MemberAccess() {
        super(".", 2, 1);
    }

    @Override
    public Value invoke(CommandSource source, Token... tokens) {
        if (tokens.length != 2)
            throw new IllegalArgumentException("Operator must be invoked on 2 parameters.");
        
        if (tokens[0] instanceof StructureValue) {
            return ((StructureValue) tokens[0]).get(tokens[1].toString());
        } else {
            return ((ArrayValue) tokens[0]).get((int) ((IntegerValue) tokens[1]).getValue());
        }
    }
}
