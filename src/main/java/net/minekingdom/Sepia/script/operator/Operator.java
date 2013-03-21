package net.minekingdom.Sepia.script.operator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandSource;

import net.minekingdom.Sepia.Sepia;
import net.minekingdom.Sepia.environment.Expression;
import net.minekingdom.Sepia.script.Token;
import net.minekingdom.Sepia.script.Variable;
import net.minekingdom.Sepia.script.operator.arithmetic.*;
import net.minekingdom.Sepia.script.operator.comparison.*;
import net.minekingdom.Sepia.script.operator.logical.*;
import net.minekingdom.Sepia.script.operator.bitwise.*;
import net.minekingdom.Sepia.script.value.Value;

public abstract class Operator extends Token {
    
    public static final List<Operator> OPERATORS = new ArrayList<Operator>(20);
    
    public static final Operator ADDITION       = new Addition();
    public static final Operator SUBSTRACTION   = new Subtraction();
    public static final Operator MULTIPLICATION = new Multiplication();
    public static final Operator DIVISION       = new Division();
    public static final Operator MODULO         = new Modulo();
    public static final Operator DOT            = new MemberAccess();
    public static final Operator EQUALS         = new Equals();
    public static final Operator GREATER_EQUALS = new GreaterEquals();
    public static final Operator LESS_EQUALS    = new LessEquals();
    public static final Operator GREATER        = new Greater();
    public static final Operator LESS           = new Less();
    public static final Operator NON_EQUALS     = new NonEquals();
    public static final Operator NOT            = new Not();
    public static final Operator AND            = new And();
    public static final Operator OR             = new Or();
    public static final Operator XOR            = new Xor();
    
    static {
        try {
            for (Field field : Operator.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Operator.class)) {
                    Operator o = (Operator) field.get(null);
                    if (o.toString().length() == 2) {
                        OPERATORS.add(0, o);
                    } else {
                        OPERATORS.add(o);
                    }
                }
            }
        } catch (Exception ex) {
            Sepia.log(Level.SEVERE, "Could not reflect properly on Operators. Unexpected behaviour may occur.");
        }
    }
    
    private int args;
    private int predecence;
    
    public Operator(String token, int args, int predecence)
    {
        super(token);
        this.args     = args;
        this.predecence = predecence;
    }

    public int getNumberOfArguments()
    {
        return args;
    }
    
    public int getPredecence()
    {
        return predecence;
    }
    
    public abstract Value invoke(CommandSource source, Token... tokens);
    
    public void process(Expression.ShuntingYard sy, int index) throws ParseException {
        sy.processOperator(this, index);
    }
    
    protected void evaluate(CommandSource source, Token... tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] instanceof Variable) {
                if (!source.hasPermission(Sepia.PERMISSION_PREFIX + "variable." + tokens[i])) {
                    source.sendMessage(ChatStyle.RED + "You do not have sufficient permissions to use the " + tokens[i] + " variable");
                    return;
                }
                tokens[i] = ((Variable) tokens[i]).getValue();
            }
        }
    }
}
