package net.minekingdom.Sepia.script.value.structure;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.minekingdom.Sepia.Sepia;
import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.environment.heap.ConstantBucket;
import net.minekingdom.Sepia.environment.heap.DynamicMethodBucket;
import net.minekingdom.Sepia.script.value.Value;

import org.spout.api.geo.World;

public class WorldValue extends StructureValue {
    
    private String name;
    
    public WorldValue(String name) {
        super(getWorldMapping(name));
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "#" + name;
    }
    
    @Override
    public Value get(String key) {
        if (!mapping.containsKey(key)) {
            throw new UnsupportedOperationException("Structure does not have such field.");
        }
        
        DynamicMethodBucket bucket = (DynamicMethodBucket) mapping.get(key);
        World world = Sepia.getInstance().getEngine().getWorld(this.name, true);
        bucket.setInstance(world);
        
        Object result = bucket.getValue();
        if (result == null) {
            throw new RuntimeException("World " + this.name + " does not exist");
        } else {
            return Value.getValue(result);
        }
    }

    private static Map<String, Bucket> getWorldMapping(String name) {
        Map<String, Bucket> map = new HashMap<String, Bucket>();
        
        map.put("name", new ConstantBucket(name));
        try {
            Method spawn = World.class.getMethod("getSpawnPoint");
            Method seed  = World.class.getMethod("getSeed");
            
            map.put("spawn", new DynamicMethodBucket(spawn, null));
            map.put("seed",  new DynamicMethodBucket(seed, null));
        } catch (Exception ex) {}
        
        return map;
    }

}
