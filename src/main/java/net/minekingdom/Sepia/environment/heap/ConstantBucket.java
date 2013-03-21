package net.minekingdom.Sepia.environment.heap;

public class ConstantBucket implements Bucket {
    private Object value;
    
    public ConstantBucket(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
