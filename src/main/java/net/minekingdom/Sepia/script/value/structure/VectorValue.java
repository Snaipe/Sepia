package net.minekingdom.Sepia.script.value.structure;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.spout.api.math.Vector3;

import net.minekingdom.Sepia.environment.heap.Bucket;
import net.minekingdom.Sepia.environment.heap.ConstantBucket;
import net.minekingdom.Sepia.script.value.Value;
import net.minekingdom.Sepia.script.value.primitive.DecimalValue;
import net.minekingdom.Sepia.script.value.primitive.IntegerValue;
import net.minekingdom.Sepia.script.value.primitive.NumericValue;

public class VectorValue extends StructureValue {
    
    private double x, y, z;
    
    public VectorValue(Vector3 v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    
    public VectorValue(double x, double y, double z) {
        super(getVectorMapping(x, y, z));
            this.x = x;
            this.y = y;
            this.z = z;
    }
    
    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
    }
    
    public VectorValue add(VectorValue value) {
        return new VectorValue(value.x + this.x, value.y + this.y, value.z + this.z);
    }
    
    public VectorValue substract(VectorValue value) {
        return new VectorValue(this.x - value.x, this.y - value.y, this.z - value.z);
    }
    
    public VectorValue multiply(NumericValue value) {
        if (value instanceof DecimalValue) {
            double f = ((DecimalValue) value).getValue();
            return new VectorValue(this.x * f, this.y * f, this.z * f);
        } else {
            long f = ((IntegerValue) value).getValue();
            return new VectorValue(this.x * f, this.y * f, this.z * f);
        }
    }
    
    public VectorValue divide(NumericValue value) {
        if (value instanceof DecimalValue) {
            double f = ((DecimalValue) value).getValue();
            return new VectorValue(this.x / f, this.y / f, this.z / f);
        } else {
            long f = ((IntegerValue) value).getValue();
            return new VectorValue(this.x / f, this.y / f, this.z / f);
        }
    }
    
    public Value negative() {
        return new VectorValue(-this.x, -this.y, -this.z);
    }
    
    private static Map<String, Bucket> getVectorMapping(double x, double y, double z) {
        Map<String, Bucket> map = new HashMap<String, Bucket>();
            map.put("x",   new ConstantBucket(x));
            map.put("y",   new ConstantBucket(y));
            map.put("z",   new ConstantBucket(z));
        
        return map;
    }

    public static Value parse(String str) throws ParseException {
        int firstComma  = str.indexOf(',');
        int secondComma = str.indexOf(',', firstComma + 1);
        
        if (firstComma == -1 || secondComma == -1) {
            throw new ParseException("Invalid vector value", 0);
        }
        
        try {
            double x = Double.parseDouble(str.substring(0, firstComma));
            double y = Double.parseDouble(str.substring(firstComma + 1, secondComma));
            double z = Double.parseDouble(str.substring(secondComma + 1));
            return new VectorValue(x, y, z);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new ParseException("Invalid vector value", 0);
        }
    }
}
