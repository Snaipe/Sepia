package net.minekingdom.Sepia.environment.heap;

import java.lang.reflect.Method;

public class DynamicMethodBucket implements Bucket {
    private Method method;
    private Object[] params;
    private Object instance;
    
    public DynamicMethodBucket(Method method, Object instance, Object... params) {
        this.method   = method;
        this.instance = instance;
        this.params   = params;
    }
    
    @Override
    public Object getValue() {
        try {
            return this.method.invoke(instance, params);
        } catch (Exception e) {
            return null;
        }
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
