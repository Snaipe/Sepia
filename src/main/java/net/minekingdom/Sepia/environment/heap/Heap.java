package net.minekingdom.Sepia.environment.heap;

import java.util.HashMap;
import java.util.Map;

public class Heap {
    private Map<String, Bucket> variables;
    
    public Heap() {
        this.variables = new HashMap<String, Bucket>();
    }
    
    public Bucket get(String name) {
        return variables.get(name);
    }
    
    public void set(String name, Bucket value) {
        this.variables.put(name, value);
    }
    
    public Map<String, Bucket> getAll() {
        return new HashMap<String, Bucket>(variables);
    }
    
    /*private InternalNode root;
    
    public Heap() {
        this.root = new InternalNode("");
    }
    
    public Bucket get(String path) {
        return root.get(path.split("."));
    }
    
    public void set(String path, Bucket value) {
        root.add(value, path.split("."));
    }
    
    private static abstract class Node { 
        private String name;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private static class InternalNode extends Node {
        private Map<String, Node> subtrees;

        public InternalNode(String name) {
            super(name);
            this.subtrees = new HashMap<String, Node>();
        }

        private void addChild(Node node) {
            this.subtrees.put(node.getName(), node);
        }

        private Node getChild(String name) {
            return this.subtrees.get(name);
        }

        public void add(Bucket value, String... path) {
            Node node = this;
            int i = 0;
            while (i < path.length - 1) {
                Node n = ((InternalNode) node).getChild(path[i]);
                if (n == null) {
                    n = new InternalNode(path[i]);
                    ((InternalNode) node).addChild(n);
                } else if (n instanceof Leaf) {
                    throw new UnsupportedOperationException("A value was encountered on the path.");
                }
                node = n;
                ++i;
            }
            ((InternalNode) node).addChild(new Leaf(path[i], value));
        }

        public Bucket get(String[] path) {
            Node node = this;
            int i = 0;
            while (i < path.length && node instanceof InternalNode) {
                node = ((InternalNode) node).getChild(path[i++]);
            }
            return i == path.length && node instanceof Leaf ? ((Leaf)node).get() : null;
        }
    }

    private static class Leaf extends Node {
        private Bucket bucket;

        public Leaf(String name, Bucket bucket) {
            super(name);
            this.bucket = bucket;
        }

        public Bucket get() {
            return bucket;
        }
    }*/
}
