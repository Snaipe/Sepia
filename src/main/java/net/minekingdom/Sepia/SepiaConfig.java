package net.minekingdom.Sepia;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.environment.heap.ConstantBucket;
import net.minekingdom.Sepia.environment.heap.Heap;
import net.minekingdom.Sepia.script.value.Value;

public class SepiaConfig {
    
    private Configuration config;
    
    private Heap heap;
    
    public SepiaConfig(File file) {
        this.config = new YamlConfiguration(file);
    }
    
    public Heap getHeap() {
        return this.heap;
    }
    
    public void load() {
        try {
            this.config.load();
            
            this.heap = new Heap();
            
            for (Map.Entry<String, ConfigurationNode> node : this.config.getChild("variables").getChildren().entrySet()) {
                this.heap.set(node.getKey(), new ConstantBucket(getValue(node.getValue())));
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    private Object getValue(ConfigurationNode node) {
        String str = node.getString();
        Class<?> clazz = Value.fromString(str);
        if (clazz.equals(Long.class)) {
            return node.getLong();
        } else if (clazz.equals(Double.class)) {
            return node.getDouble();
        } else if (clazz.equals(Boolean.class)) {
            return node.getBoolean();
        } else if (clazz.equals(List.class)) {
            return node.getList();
        } else {
            return str;
        }
    }
    
    public void save() {
        try {
            for (Map.Entry<String, Bucket> entry : this.heap.getAll().entrySet()) {
                this.config.addChild(new ConfigurationNode(this.config, new String[] {"variables", entry.getKey()}, entry.getValue().getValue()));
            }
            
            this.config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

}
