package net.minekingdom.Sepia.script.value.structure;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.minekingdom.Sepia.Sepia;
import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.environment.heap.ConstantBucket;
import net.minekingdom.Sepia.environment.heap.DynamicMethodBucket;
import net.minekingdom.Sepia.script.value.Value;

import org.spout.api.component.impl.SceneComponent;
import org.spout.api.entity.Player;

public class PlayerValue extends StructureValue {
    
    private String name;
    
    public PlayerValue(String name) {
        super(getPlayerMapping(name));
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "@" + name;
    }
    
    @Override
    public Value get(String key) {
        if (!mapping.containsKey(key)) {
            throw new UnsupportedOperationException("Structure does not have such field.");
        }
        
        DynamicMethodBucket bucket = (DynamicMethodBucket) mapping.get(key);
        Player player = Sepia.getInstance().getEngine().getPlayer(this.name, true);
        if (player != null) {
            if (key.equals("pos")) {
                bucket.setInstance(player.getScene());
            } else {
                bucket.setInstance(player);
            }
        }
        
        Object result = bucket.getValue();
        if (result == null) {
            throw new RuntimeException("Player " + this.name + " is not online");
        } else {
            return Value.getValue(result);
        }
    }

    private static Map<String, Bucket> getPlayerMapping(String name) {
        Map<String, Bucket> map = new HashMap<String, Bucket>();
        
        map.put("name", new ConstantBucket(name));
        try {
            Method position = SceneComponent.class.getMethod("getPosition");
            Method ip       = Player.class.getMethod("getAddress");
            
            map.put("ip",   new DynamicMethodBucket(ip, null));
            map.put("pos",  new DynamicMethodBucket(position, null));
        } catch (Exception ex) {}
        
        return map;
    }

}
