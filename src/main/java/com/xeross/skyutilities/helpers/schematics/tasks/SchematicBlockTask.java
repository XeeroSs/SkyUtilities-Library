package com.xeross.skyutilities.helpers.schematics.tasks;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.schematics.SchematicFile;
import com.xeross.skyutilities.helpers.schematics.api.NMSAbstraction;
import com.xeross.skyutilities.helpers.schematics.blocks.ArenaBlock;
import com.xeross.skyutilities.helpers.schematics.models.types.GenerationType;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SchematicBlockTask extends BukkitRunnable {
    
    private SkyUtilities main;
    private GenerationType generationType;
    private List<ArenaBlock> blocks;
    private Location location;
    private NMSAbstraction nmsAbstraction;
    private SchematicFile schematicFile;
    private boolean isLast;
    
    private int i = 0;
    private int max;
    private int repeat;
    private boolean isBridge;
    
    public SchematicBlockTask(SkyUtilities main, GenerationType generationType, List<ArenaBlock> blocks, Location location, NMSAbstraction nmsAbstraction,
                              SchematicFile schematicFile, boolean isLast) {
        this.main = main;
        this.generationType = generationType;
        this.blocks = blocks;
        this.location = location;
        this.nmsAbstraction = nmsAbstraction;
        this.schematicFile = schematicFile;
        this.isLast = isLast;
        
        this.i = 0;
        this.max = blocks.size();
        this.repeat = generationType.getBlockByTicks();
    }
    
    public GenerationType getGenerationType() {
        return generationType;
    }
    
    @Override
    public void run() {
        System.out.println("generate..");
        for (int j = 0; j < repeat; j++) {
            if (i >= max) {
                System.out.println("generated.");
                schematicFile.pasteEntity(location);
                // TODO("Listener when schematic loaded")
                cancel();
                return;
            }
            blocks.get(i).paste(nmsAbstraction, location, false, isBridge);
            i++;
        }
    }
}
