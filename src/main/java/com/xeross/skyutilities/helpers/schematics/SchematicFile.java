package com.xeross.skyutilities.helpers.schematics;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.schematics.api.NMSAbstraction;
import com.xeross.skyutilities.helpers.schematics.blocks.ArenaBlock;
import com.xeross.skyutilities.helpers.schematics.blocks.EntityObject;
import com.xeross.skyutilities.helpers.schematics.models.types.GenerationType;
import com.xeross.skyutilities.helpers.schematics.tasks.SchematicBlockTask;
import com.xeross.skyutilities.helpers.schematics.utils.UtilSchematic;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import org.jnbt.Tag;
import org.jnbt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class SchematicFile {
    
    private short width;
    private short length;
    private short height;
    private final Map<BlockVector, Map<String, Tag>> tileEntitiesMap = new HashMap<>();
    private final List<EntityObject> entitiesList = new ArrayList<>();
    private final File file;
    private short[] blocks;
    private byte[] data;
    private final boolean usePhysics;
    private boolean pasteEntities;
    private Vector chest;
    private NMSAbstraction nms;
    private final Set<Integer> attachable = new HashSet<>();
    private final Map<String, Art> paintingList = new HashMap<>();
    private final Map<Byte, BlockFace> facingList = new HashMap<>();
    private final Map<Byte, Rotation> rotationList = new HashMap<>();
    private List<ArenaBlock> arenaBlocks;
    
    public SchematicFile() {
        usePhysics = false;
        file = null;
        chest = null;
    }
    
    public Boolean isFileNameByName(String name) {
        try {
            final String after = file.getName().split("_")[1];
            final String nameFile = after.split("\\.")[0];
            return nameFile.equalsIgnoreCase(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getNameMap() {
        final String after = file.getName().split("_")[1];
        return after.split("\\.")[0];
    }
    
    @SuppressWarnings("deprecation")
    public SchematicFile(SkyUtilities main, File file) throws IOException {
        //  short[] blocks;
        //byte[] data;
        usePhysics = false;
        pasteEntities = false;
        chest = null;
        
        attachable.add(Material.STONE_BUTTON.getId());
        attachable.add(Material.WOOD_BUTTON.getId());
        attachable.add(Material.COCOA.getId());
        attachable.add(Material.LADDER.getId());
        attachable.add(Material.LEVER.getId());
        attachable.add(Material.PISTON_EXTENSION.getId());
        attachable.add(Material.REDSTONE_TORCH_OFF.getId());
        attachable.add(Material.REDSTONE_TORCH_ON.getId());
        attachable.add(Material.WALL_SIGN.getId());
        attachable.add(Material.TORCH.getId());
        attachable.add(Material.TRAP_DOOR.getId());
        attachable.add(Material.TRIPWIRE_HOOK.getId());
        attachable.add(Material.VINE.getId());
        attachable.add(Material.WOODEN_DOOR.getId());
        attachable.add(Material.IRON_DOOR.getId());
        attachable.add(Material.RED_MUSHROOM.getId());
        attachable.add(Material.BROWN_MUSHROOM.getId());
        attachable.add(Material.PORTAL.getId());
        
        // Painting list, useful to check if painting exsits or nor
        paintingList.put("Kebab", Art.KEBAB);
        paintingList.put("Aztec", Art.AZTEC);
        paintingList.put("Alban", Art.ALBAN);
        paintingList.put("Aztec2", Art.AZTEC2);
        paintingList.put("Bomb", Art.BOMB);
        paintingList.put("Plant", Art.PLANT);
        paintingList.put("Wasteland", Art.WASTELAND);
        paintingList.put("Wanderer", Art.WANDERER);
        paintingList.put("Graham", Art.GRAHAM);
        paintingList.put("Pool", Art.POOL);
        paintingList.put("Courbet", Art.COURBET);
        paintingList.put("Sunset", Art.SUNSET);
        paintingList.put("Sea", Art.SEA);
        paintingList.put("Creebet", Art.CREEBET);
        paintingList.put("Match", Art.MATCH);
        paintingList.put("Bust", Art.BUST);
        paintingList.put("Stage", Art.STAGE);
        paintingList.put("Void", Art.VOID);
        paintingList.put("SkullAndRoses", Art.SKULL_AND_ROSES);
        paintingList.put("Wither", Art.WITHER);
        paintingList.put("Fighters", Art.FIGHTERS);
        paintingList.put("Skeleton", Art.SKELETON);
        paintingList.put("DonkeyKong", Art.DONKEYKONG);
        paintingList.put("Pointer", Art.POINTER);
        paintingList.put("Pigscene", Art.PIGSCENE);
        paintingList.put("BurningSkull", Art.BURNINGSKULL);
        
        facingList.put((byte) 0, BlockFace.SOUTH);
        facingList.put((byte) 1, BlockFace.WEST);
        facingList.put((byte) 2, BlockFace.NORTH);
        facingList.put((byte) 3, BlockFace.EAST);
        
        rotationList.put((byte) 0, Rotation.NONE);
        rotationList.put((byte) 2, Rotation.CLOCKWISE);
        rotationList.put((byte) 4, Rotation.FLIPPED);
        rotationList.put((byte) 6, Rotation.COUNTER_CLOCKWISE);
        
        if (!Bukkit.getServer().getVersion().contains("(MC: 1.7")) {
            rotationList.put((byte) 1, Rotation.CLOCKWISE_45);
            rotationList.put((byte) 3, Rotation.CLOCKWISE_135);
            rotationList.put((byte) 5, Rotation.FLIPPED_45);
            rotationList.put((byte) 7, Rotation.COUNTER_CLOCKWISE_45);
        }
        
        try {
            nms = UtilSchematic.checkVersion(main);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Establish the World Edit to Material look up
        // V1.8 items
        if (!Bukkit.getServer().getVersion().contains("(MC: 1.7")) {
            attachable.add(Material.IRON_TRAPDOOR.getId());
            attachable.add(Material.WALL_BANNER.getId());
            attachable.add(Material.ACACIA_DOOR.getId());
            attachable.add(Material.BIRCH_DOOR.getId());
            attachable.add(Material.SPRUCE_DOOR.getId());
            attachable.add(Material.DARK_OAK_DOOR.getId());
            attachable.add(Material.JUNGLE_DOOR.getId());
        }
        
        // Entities
        /*
	WEtoME.put("LAVASLIME", EntityType.MAGMA_CUBE);
	WEtoME.put("ENTITYHORSE", EntityType.HORSE);
	WEtoME.put("OZELOT", EntityType.OCELOT);
	WEtoME.put("MUSHROOMCOW", EntityType.MUSHROOM_COW);
	WEtoME.put("PIGZOMBIE", EntityType.PIG_ZOMBIE);
         */
        this.file = file;
        // Try to load the file
        try {
            FileInputStream stream = new FileInputStream(file);
            // InputStream is = new DataInputStream(new
            // GZIPInputStream(stream));
            NBTInputStream nbtStream = new NBTInputStream(stream);
            
            CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
            nbtStream.close();
            stream.close();
            if (!schematicTag.getName().equals("Schematic")) {
                throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
            }
            
            Map<String, Tag> schematic = schematicTag.getValue();
            
            Vector origin = new Vector(0, 0, 0);
            try {
                int originX = getChildTag(schematic, "WEOriginX", IntTag.class).getValue();
                int originY = getChildTag(schematic, "WEOriginY", IntTag.class).getValue();
                int originZ = getChildTag(schematic, "WEOriginZ", IntTag.class).getValue();
                Vector min = new Vector(originX, originY, originZ);
                
                //int offsetX = getChildTag(schematic, "WEOffsetX", IntTag.class).getValue();
                //int offsetY = getChildTag(schematic, "WEOffsetY", IntTag.class).getValue();
                //int offsetZ = getChildTag(schematic, "WEOffsetZ", IntTag.class).getValue();
                //Vector offset = new Vector(offsetX, offsetY, offsetZ);
                
                //origin = min.subtract(offset);
                origin = min.clone();
            } catch (Exception ignored) {
            }
            //Bukkit.getLogger().info("Origin = " + origin);
            
            
            if (!schematic.containsKey("Blocks")) {
                throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
            }
            
            width = getChildTag(schematic, "Width", ShortTag.class).getValue();
            length = getChildTag(schematic, "Length", ShortTag.class).getValue();
            height = getChildTag(schematic, "Height", ShortTag.class).getValue();
            
            String materials = getChildTag(schematic, "Materials", StringTag.class).getValue();
            if (!materials.equals("Alpha")) {
                throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
            }
            
            byte[] blockId = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
            data = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
            byte[] addId = new byte[0];
            blocks = new short[blockId.length]; // Have to later combine IDs
            // We support 4096 block IDs using the same method as vanilla
            // Minecraft, where
            // the highest 4 bits are stored in a separate byte array.
            if (schematic.containsKey("AddBlocks")) {
                addId = getChildTag(schematic, "AddBlocks", ByteArrayTag.class).getValue();
            }
            
            // Combine the AddBlocks data with the first 8-bit block ID
            for (int index = 0; index < blockId.length; index++) {
                if ((index >> 1) >= addId.length) { // No corresponding
                    // AddBlocks index
                    blocks[index] = (short) (blockId[index] & 0xFF);
                } else {
                    if ((index & 1) == 0) {
                        blocks[index] = (short) (((addId[index >> 1] & 0x0F) << 8) + (blockId[index] & 0xFF));
                    } else {
                        blocks[index] = (short) (((addId[index >> 1] & 0xF0) << 4) + (blockId[index] & 0xFF));
                    }
                }
            }
            // Entities
            List<Tag> entities = getChildTag(schematic, "Entities", ListTag.class).getValue();
            for (Tag tag : entities) {
                if (!(tag instanceof CompoundTag))
                    continue;
                
                CompoundTag t = (CompoundTag) tag;
                //Bukkit.getLogger().info("**************************************");
                EntityObject ent = new EntityObject();
                for (Map.Entry<String, Tag> entry : t.getValue().entrySet()) {
                    //Bukkit.getLogger().info("DEBUG " + entry.getKey() + ">>>>" + entry.getValue());
                    //Bukkit.getLogger().info("++++++++++++++++++++++++++++++++++++++++++++++++++");
                    if (entry.getKey().equals("id")) {
                        String id = ((StringTag) entry.getValue()).getValue().toUpperCase();
                        //Bukkit.getLogger().info("DEBUG: ID is '" + id + "'");
                        // The mob type might be prefixed with "Minecraft:"
                        if (id.startsWith("MINECRAFT:")) {
                            id = id.substring(10);
                        }
                        if (ArenaBlock.WEtoME.containsKey(id)) {
                            //Bukkit.getLogger().info("DEBUG: id found");
                            ent.setType(ArenaBlock.WEtoME.get(id));
                        } else if (!id.equalsIgnoreCase("ITEM")) {
                            for (EntityType type : EntityType.values()) {
                                if (type.toString().equals(id)) {
                                    ent.setType(type);
                                    break;
                                }
                            }
                        }
                    }
                    
                    switch (entry.getKey()) {
                        case "Pos":
                            if (entry.getValue() instanceof ListTag) {
                                List<Tag> pos;
                                pos = ((ListTag) entry.getValue()).getValue();
                                if (pos.size() == 3) {
                                    double x = (double) pos.get(0).getValue() - origin.getX();
                                    double y = (double) pos.get(1).getValue() - origin.getY();
                                    double z = (double) pos.get(2).getValue() - origin.getZ();
                                    ent.setLocation(new BlockVector(x, y, z));
                                } else {
                                    ent.setLocation(new BlockVector(0, 0, 0));
                                }
                            }
                            break;
                        case "Motion":
                            if (entry.getValue() instanceof ListTag) {
                                List<Tag> pos;
                                pos = ((ListTag) entry.getValue()).getValue();
                                if (pos.size() == 3) {
                                    ent.setMotion(new Vector((double) pos.get(0).getValue(), (double) pos.get(1).getValue()
                                            , (double) pos.get(2).getValue()));
                                } else {
                                    ent.setMotion(new Vector(0, 0, 0));
                                }
                            }
                            break;
                        case "Rotation":
                            if (entry.getValue() instanceof ListTag) {
                                List<Tag> pos;
                                pos = ((ListTag) entry.getValue()).getValue();
                                if (pos.size() == 2) {
                                    ent.setYaw((float) pos.get(0).getValue());
                                    ent.setPitch((float) pos.get(1).getValue());
                                } else {
                                    ent.setYaw(0F);
                                    ent.setPitch(0F);
                                }
                            }
                            break;
                        case "Color":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setColor(((ByteTag) entry.getValue()).getValue());
                            }
                            break;
                        case "Sheared":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setSheared(((ByteTag) entry.getValue()).getValue() != (byte) 0);
                            }
                            break;
                        case "RabbitType":
                            if (entry.getValue() instanceof IntTag) {
                                ent.setRabbitType(((IntTag) entry.getValue()).getValue());
                            }
                            break;
                        case "Profession":
                            if (entry.getValue() instanceof IntTag) {
                                ent.setProfession(((IntTag) entry.getValue()).getValue());
                            }
                            break;
                        case "CarryingChest":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setCarryingChest(((ByteTag) entry.getValue()).getValue());
                            }
                            break;
                        case "OwnerUUID":
                            ent.setOwned(true);
                            break;
                        case "CollarColor":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setCollarColor(((ByteTag) entry.getValue()).getValue());
                            }
                            break;
                        case "Facing":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setFacing(((ByteTag) entry.getValue()).getValue());
                            }
                            break;
                        case "Motive":
                            if (entry.getValue() instanceof StringTag) {
                                ent.setMotive(((StringTag) entry.getValue()).getValue());
                            }
                            break;
                        case "ItemDropChance":
                            if (entry.getValue() instanceof FloatTag) {
                                ent.setItemDropChance(((FloatTag) entry.getValue()).getValue());
                            }
                            break;
                        case "ItemRotation":
                            if (entry.getValue() instanceof ByteTag) {
                                ent.setItemRotation(((ByteTag) entry.getValue()).getValue());
                            }
                            break;
                        case "Item":
                            if (entry.getValue() instanceof CompoundTag) {
                                CompoundTag itemTag = (CompoundTag) entry.getValue();
                                for (Map.Entry<String, Tag> itemEntry : itemTag.getValue().entrySet()) {
                                    switch (itemEntry.getKey()) {
                                        case "Count":
                                            if (itemEntry.getValue() instanceof ByteTag) {
                                                ent.setCount(((ByteTag) itemEntry.getValue()).getValue());
                                            }
                                            break;
                                        case "Damage":
                                            if (itemEntry.getValue() instanceof ShortTag) {
                                                ent.setDamage(((ShortTag) itemEntry.getValue()).getValue());
                                            }
                                            break;
                                        case "id":
                                            if (itemEntry.getValue() instanceof StringTag) {
                                                ent.setId(((StringTag) itemEntry.getValue()).getValue());
                                            }
                                            break;
                                    }
                                }
                            }
                            break;
                        case "TileX":
                            if (entry.getValue() instanceof IntTag) {
                                ent.setTileX((double) ((IntTag) entry.getValue()).getValue() - origin.getX());
                            }
                            break;
                        case "TileY":
                            if (entry.getValue() instanceof IntTag) {
                                ent.setTileY((double) ((IntTag) entry.getValue()).getValue() - origin.getY());
                            }
                            break;
                        case "TileZ":
                            if (entry.getValue() instanceof IntTag) {
                                ent.setTileZ((double) ((IntTag) entry.getValue()).getValue() - origin.getZ());
                            }
                            break;
                    }
                }
                
                if (ent.getType() != null) {
                    //Bukkit.getLogger().info("DEBUG: adding " + ent.getType().toString() + " at " + ent.getLocation().toString());
                    //entitiesMap.put(new BlockVector(x,y,z), mobType);
                    entitiesList.add(ent);
                }
            }
            //Bukkit.getLogger().info("DEBUG: size of entities = " + entities.size());
            // Tile entities
            List<Tag> tileEntities = getChildTag(schematic, "TileEntities", ListTag.class).getValue();
            // Map<BlockVector, Map<String, Tag>> tileEntitiesMap = new
            // HashMap<BlockVector, Map<String, Tag>>();
            
            for (Tag tag : tileEntities) {
                if (!(tag instanceof CompoundTag))
                    continue;
                CompoundTag t = (CompoundTag) tag;
                
                int x = 0;
                int y = 0;
                int z = 0;
                
                Map<String, Tag> values = new HashMap<>();
                
                for (Map.Entry<String, Tag> entry : t.getValue().entrySet()) {
                    switch (entry.getKey()) {
                        case "x":
                            if (entry.getValue() instanceof IntTag) {
                                x = ((IntTag) entry.getValue()).getValue();
                            }
                            break;
                        case "y":
                            if (entry.getValue() instanceof IntTag) {
                                y = ((IntTag) entry.getValue()).getValue();
                            }
                            break;
                        case "z":
                            if (entry.getValue() instanceof IntTag) {
                                z = ((IntTag) entry.getValue()).getValue();
                            }
                            break;
                    }
                    
                    values.put(entry.getKey(), entry.getValue());
                }
                
                BlockVector vec = new BlockVector(x, y, z);
                tileEntitiesMap.put(vec, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        
        // Check for key blocks
        // Find top most bedrock - this is the key stone
        // Find top most chest
        // Find top most grass
        List<Vector> grassBlocks = new ArrayList<>();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    if (blocks[index] == 54) {
                        // Last chest
                        if (chest == null || chest.getY() < y) {
                            chest = new Vector(x, y, z);
                        }
                    } else if (blocks[index] == 2) {
                        // Grass
                        grassBlocks.add(new Vector(x, y, z));
                    }
                }
            }
        }
        // Find other key blocks
        if (!grassBlocks.isEmpty()) {
            // Sort by height
            List<Vector> sorted = new ArrayList<>();
            for (Vector v : grassBlocks) {
                //if (GridManager.isSafeLocation(v.toLocation(world))) {
                // Add to sorted list
                boolean inserted = false;
                for (int i = 0; i < sorted.size(); i++) {
                    if (v.getBlockY() > sorted.get(i).getBlockY()) {
                        sorted.add(i, v);
                        inserted = true;
                        break;
                    }
                }
                if (!inserted) {
                    // just add to the end of the list
                    sorted.add(v);
                }
            }
        }
        
        // Preload the blocks
        // prePasteSchematic(blocks, data);
    }
    
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }
    
    /**
     * @return the length
     */
    public short getLength() {
        return length;
    }
    
    /**
     * @return the tileEntitiesMap
     */
    public Map<BlockVector, Map<String, Tag>> getTileEntitiesMap() {
        return tileEntitiesMap;
    }
    
    public void pasteEntity(final Location blockLoc) {
        // PASTE ENTS
        for (EntityObject ent : entitiesList) {
            // If TileX/Y/Z id defined, we have to use it (for Item Frame & Painting)
            if (ent.getTileX() != null && ent.getTileY() != null && ent.getTileZ() != null) {
                ent.setLocation(new BlockVector(ent.getTileX(), ent.getTileY(), ent.getTileZ()));
            }
            
            Location entitySpot = ent.getLocation().toLocation(blockLoc.getWorld()).add(blockLoc.toVector());
            entitySpot.setPitch(ent.getPitch());
            entitySpot.setYaw(ent.getYaw());
            if (ent.getType() == EntityType.PAINTING) {
                
                try {
                    Painting painting = blockLoc.getWorld().spawn(entitySpot, Painting.class);
                    if (painting != null) {
                        // Set default
                        painting.setArt(paintingList.getOrDefault(ent.getMotive(), Art.ALBAN), true);
                        // http://minecraft.gamepedia.com/Painting#Data_values
                        //set default direction
                        painting.setFacingDirection(facingList.getOrDefault(ent.getFacing(), BlockFace.NORTH), true);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else if (ent.getType() == EntityType.ITEM_FRAME) {
                ItemFrame itemFrame = (ItemFrame) blockLoc.getWorld().spawnEntity(entitySpot, EntityType.ITEM_FRAME);
                if (itemFrame != null) {
                    // Need to improve this shity fix ...
                    Material material = Material.matchMaterial(ent.getId().substring(10).toUpperCase());
                    
                    if (material == null && ArenaBlock.WEtoM.containsKey(ent.getId().substring(10).toUpperCase())) {
                        material = ArenaBlock.WEtoM.get(ent.getId().substring(10).toUpperCase());
                    }
                    
                    ItemStack item;
                    
                    if (material != null) {
                        if (ent.getCount() != null) {
                            if (ent.getDamage() != null) {
                                item = new ItemStack(material, ent.getCount(), ent.getDamage());
                            } else {
                                item = new ItemStack(material, ent.getCount(), (short) 0);
                            }
                        } else {
                            if (ent.getDamage() != null) {
                                item = new ItemStack(material, 1, ent.getDamage());
                            } else {
                                item = new ItemStack(material, 1, (short) 0);
                            }
                        }
                    } else {
                        // Set to default content
                        item = new ItemStack(Material.STONE, 0, (short) 4);
                    }
                    
                    ItemMeta itemMeta = item.getItemMeta();
                    
                    item.setItemMeta(itemMeta);
                    itemFrame.setItem(item);
                    
                    //set default direction
                    itemFrame.setFacingDirection(facingList.getOrDefault(ent.getFacing(), BlockFace.NORTH), true);
                    
                    // Set default direction
                    itemFrame.setRotation(rotationList.getOrDefault(ent.getItemRotation(), Rotation.NONE));
                }
            }
        }
        // return schematicArenaData.getViewPlayer();
    }
    
    public void pasteSchematic(final Plugin plugin, final SkyUtilities main, final Location loc, final GenerationType generationType, final Boolean isLast) {
        if (this.file == null) return;
        if (arenaBlocks == null) prePasteSchematic();
        
        World world = loc.getWorld();
        Location blockLoc = new Location(world, loc.getX(), loc.getY(), loc.getZ());
        new SchematicBlockTask(main, generationType, arenaBlocks, blockLoc, nms, this, isLast).runTaskTimer(plugin,
                (generationType.getSecondsBetweenEachGeneration() * 20L),
                (generationType.getSecondsBetweenEachGeneration() * 20L));
     /*   for (ArenaBlock b : arenaBlocks) {
            b.paste(nms, blockLoc, this.usePhysics, schematicArenaData);
        }*/
    }
    
    @SuppressWarnings("deprecation")
    public void prePasteSchematic() {
        arenaBlocks = new ArrayList<>();
        Map<BlockVector, Map<String, Tag>> tileEntitiesMap = this.getTileEntitiesMap();
        // Start with non-attached blocks
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    // Only bother if this block is above ground zero and
                    // only bother with air if it is below sea level
                    // Only bother if the schematic blocks are within the range that y can be
                    if (blocks[index] == Material.AIR.getId()) continue;
                    ArenaBlock block = new ArenaBlock(x, y, z);
                    if (!attachable.contains((int) blocks[index]) || blocks[index] == 179) {
                        block.setBlock(blocks[index], data[index]);
                        // Tile Entities
                        if (tileEntitiesMap.containsKey(new BlockVector(x, y, z))) {
                            if (block.getTypeId() == Material.STANDING_BANNER.getId()) {
                                block.setBanner(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            } else if (block.getTypeId() == Material.SKULL.getId()) {
                                block.setSkull(tileEntitiesMap.get(new BlockVector(x, y, z)), block.getData());
                            } else if (block.getTypeId() == Material.FLOWER_POT.getId()) {
                                block.setFlowerPot(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            }
                            // Monster spawner blocks
                            if (block.getTypeId() == Material.MOB_SPAWNER.getId()) {
                                block.setSpawnerType(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            } else if ((block.getTypeId() == Material.SIGN_POST.getId())) {
                                block.setSign(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            } else if (block.getTypeId() == Material.CHEST.getId()
                                    || block.getTypeId() == Material.TRAPPED_CHEST.getId()
                                    || block.getTypeId() == Material.FURNACE.getId()
                                    || block.getTypeId() == Material.BURNING_FURNACE.getId()
                                    || block.getTypeId() == Material.DISPENSER.getId()
                                    || block.getTypeId() == Material.HOPPER.getId()
                                    || block.getTypeId() == Material.DROPPER.getId()
                                    || block.getTypeId() == Material.STORAGE_MINECART.getId()
                                    || block.getTypeId() == Material.HOPPER_MINECART.getId()
                                    || block.getTypeId() == Material.POWERED_MINECART.getId()
                                    || Material.getMaterial(block.getTypeId()).name().contains("SHULKER_BOX")
                            ) {
                                block.setChest(nms, tileEntitiesMap.get(new BlockVector(x, y, z)));
                            }
                        }
                        arenaBlocks.add(block);
                    }
                }
            }
        }
        // Second pass - just paste attachables and deal with chests etc.
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    ArenaBlock block = new ArenaBlock(x, y, z);
                    if (attachable.contains((int) blocks[index])) {
                        block.setBlock(blocks[index], data[index]);
                        // Tile Entities
                        if (tileEntitiesMap.containsKey(new BlockVector(x, y, z))) {
                            if (block.getTypeId() == Material.WALL_BANNER.getId()) {
                                block.setBanner(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            }
                            // Wall Sign
                            if (block.getTypeId() == Material.WALL_SIGN.getId()) {
                                block.setSign(tileEntitiesMap.get(new BlockVector(x, y, z)));
                            }
                        }
                        arenaBlocks.add(block);
                    }
                }
            }
        }
    }

    /*
     * Removes all the air blocks if they are not to be pasted.
     *
     public void setPasteAir(boolean pasteAir) {
        if (!pasteAir) {
            arenaBlocks.removeIf(b -> b.getTypeId() == 0);
        }
    }
     */
    
    /**
     * Get child tag of a NBT structure.
     *
     * @param items    The parent tag map
     * @param key      The name of the tag to get
     * @param expected The expected type of the tag
     * @return child tag casted to the expected type
     */
    private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException {
        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
        }
        Tag tag = items.get(key);
        if (!expected.isInstance(tag)) {
            throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
        }
        return expected.cast(tag);
    }
}