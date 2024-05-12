package me.afatcookie.cookcaptcha.config;
import me.afatcookie.cookcaptcha.CookCaptcha;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;

/**
 * Hierarchy class for all configs
 */
public class AFCConfig {

    //ArrayList for all configs being made in subclass
    private final ArrayList<AFCConfig> configs = new ArrayList<>();

    //File
        private  File file;

        private FileConfiguration fileConfiguration;

        private final CookCaptcha instance;

        //name of file (ex. design) and then it would be followed by .yml
        private final String filePath;

        //main path in config that will be used to create the gui and items
        private final String mainPath;

        public AFCConfig(CookCaptcha instance, String filePath, String mainPath) {
            this.instance = instance;
            this.filePath = filePath;
            this.mainPath = mainPath;
            setup(filePath);
            establishDefaults();
            configs.add(this);
        }

    public AFCConfig(CookCaptcha instance, String filePath, String mainPath, Map<String, Object> startUpPaths) {
        this.instance = instance;
        this.filePath = filePath;
        this.mainPath = mainPath;
        setup(filePath);
        if (startUpPaths != null){
                getConfig().addDefaults(startUpPaths);
        }
        establishDefaults();
        configs.add(this);
    }

    public void establishDefaults(){
            getConfig().options().copyDefaults(true);
            save();
    }

        private void setup(String path){
            file = new File(this.instance.getDataFolder(), path + ".yml");

            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Something went wrong in " + path);
                }
            }
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }

        public FileConfiguration getConfig(){
            return fileConfiguration;
        }

        public void save(){
            try {
                fileConfiguration.save(file);
            } catch (IOException e) {
                Bukkit.getLogger().log(Level.SEVERE, "Failed to save data to " + filePath + "  Config", e);
            }
        }

        public void reload(){
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }

    public ArrayList<AFCConfig> getConfigs() {
        return configs;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMainPath() {
        return mainPath;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public CookCaptcha getInstance() {
        return instance;
    }
}

