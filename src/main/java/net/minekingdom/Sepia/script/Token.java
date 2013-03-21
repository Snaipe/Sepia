package net.minekingdom.Sepia.script;

public abstract class Token {
    
    private String token;
    
    public Token(String token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return this.token;
    }
}
