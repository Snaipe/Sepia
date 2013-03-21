package net.minekingdom.Sepia.script.value.structure;

import java.util.HashMap;
import java.util.Map;

import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.script.value.Value;

public class StructureValue extends Value {
    
    protected Map<String, Bucket> mapping;
    
    public StructureValue() {
        this(new HashMap<String, Bucket>());
    }

    public StructureValue(Map<String, Bucket> mapping) {
        super(mapping);
        this.mapping = mapping;
    }
    
    public Value get(String key) {
        if (!mapping.containsKey(key)) {
            throw new UnsupportedOperationException("Structure does not have such field.");
        }
        return Value.getValue(mapping.get(key).getValue());
    }

}
