package net.minekingdom.Sepia;

import java.io.File;
import java.util.logging.Level;

import org.spout.api.plugin.CommonPlugin;

public class Sepia extends CommonPlugin {

    public static final String PERMISSION_PREFIX = "environment.";

    private static Sepia instance;
    
    private SepiaConfig config;

    @Override
    public void onDisable() {
        this.config.save();
    }

    @Override
    public void onEnable() {
        instance = this;
        
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        
        this.config = new SepiaConfig(new File(getDataFolder() + "config.yml"));
        this.config.load();

        this.getEngine().getEventManager().registerEvents(new SepiaListener(), this);
        log("Version " + getDescription().getVersion() + " enabled ! Have a nice day :)");
    }
    
    @Override
    public void onReload() {
        this.config.load();
    }

    public static Sepia getInstance() {
        return instance;
    }

    public static void log(String message) {
        log(Level.INFO, message);
    }

    public static void log(Level level, String message) {
        instance.getLogger().log(level, message);
    }
}
