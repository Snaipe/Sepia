package net.minekingdom.Sepia.environment.heap;

import java.lang.reflect.Field;

public class DynamicFieldBucket implements Bucket {
    private Field field;
    private Object instance;
    
    public DynamicFieldBucket(Field field, Object instance) {
        this.field    = field;
        this.instance = instance;
    }
    
    @Override
    public Object getValue() {
        try {
            return this.field.get(this.instance);
        } catch (Exception e) {
            return null;
        }
    }
}
