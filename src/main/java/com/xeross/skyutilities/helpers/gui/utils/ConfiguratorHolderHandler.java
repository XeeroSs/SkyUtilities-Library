package com.xeross.skyutilities.helpers.gui.utils;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.blocks.models.ColorType;
import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderAPI;
import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderMessageAPI;
import com.xeross.skyutilities.helpers.gui.api.GUI;
import com.xeross.skyutilities.helpers.gui.api.HolderItemAPI;
import com.xeross.skyutilities.helpers.gui.models.*;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderPaintingMaterialType;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderPaintingType;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderSizeType;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderType;
import com.xeross.skyutilities.helpers.items.models.Skull;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import com.xeross.skyutilities.helpers.messages.LangLinesWithTitleHandler;
import com.xeross.skyutilities.helpers.messages.LangMessageHandler;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import com.xeross.skyutilities.helpers.messages.models.MessagesHolder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.*;

/**
 * - Basic/Complex GUI
 * <p>
 * - Page system
 * <p>
 * - GUI Animated/Dynamic system
 * <p>
 * - Optimization code for your GUI class
 * <p>
 * - Smart and performance optimized
 * <p>
 * - Painting System
 * <p>
 * - Automatic items place even without position
 *
 * @author XeroSs
 * @version 1.7.0
 */
@SuppressWarnings({"unused", "CommentedOutCode"})
public abstract class ConfiguratorHolderHandler<P extends Plugin, O extends Enum<O> & MessageType, L extends Enum<L> & MessageType> extends GUI<P> {
    
    private final Map<String, ConfiguratorHolderData> cache;
    private final LinkedHashMap<Integer, ConfiguratorHolderItem<P, O>> items;
    private final ArrayList<ConfiguratorHolder<P, O>> fromItems;
    private final HashMap<Integer, ConfiguratorHolder<P, O>> fromItemsWithPosition;
    private final HashMap<Integer, HolderItem<P, O>> itemOnly;
    private final HashMap<Integer, HolderItemWithLore<P, L>> itemWithLoreOnly;
    private final HashMap<Integer, ItemStack> itemPainting;
    private final ArrayList<HolderItem<P, O>> itemOnlyToPlaceAtCenter;
    private final ArrayList<HolderItemWithLore<P, L>> itemWithLoreToPlaceAtCenter;
    private final short[] glasses;
    
    private final ChatColor[] colors;
    private int indexColor;
    private final int sizeInventory;
    
    int up = 0;
    int down = 0;
    boolean isUp = false;
    
    private String titleSuffix;
    private String titlePrefix;
    private boolean isAddons;
    
    private final SkyUtilities<P> skyUtilities;
    private int itemsStartPosition;
    private int itemsEndPosition;
    private final ArrayList<Integer> indexNotAvailableForPage;
    
    
    public ConfiguratorHolderHandler(final SkyUtilities<P> skyUtilities) {
        super(skyUtilities);
        this.indexColor = 0;
        this.itemsStartPosition = 0;
        this.itemsEndPosition = 0;
        this.skyUtilities = skyUtilities;
        this.glasses = new short[]{(short) ColorType.WHITE.getWoolData(), (short) ColorType.LIGHT_BLUE.getWoolData(), (short) ColorType.BLUE.getWoolData()};
        this.sizeInventory = getSize();
        this.cache = new HashMap<>();
        this.itemOnly = new HashMap<>();
        this.itemWithLoreOnly = new HashMap<>();
        this.indexNotAvailableForPage = new ArrayList<>();
        this.itemWithLoreToPlaceAtCenter = new ArrayList<>();
        this.isAddons = false;
        this.fromItemsWithPosition = new HashMap<>();
        this.itemPainting = new HashMap<>();
        this.items = new LinkedHashMap<>();
        this.fromItems = new ArrayList<>();
        this.itemOnlyToPlaceAtCenter = new ArrayList<>();
        this.colors = new ChatColor[]{ChatColor.AQUA, ChatColor.GOLD, ChatColor.GREEN, ChatColor.DARK_AQUA, ChatColor.YELLOW, ChatColor.LIGHT_PURPLE};
    }
    
    public void addPage(int itemsStartPosition, int itemsEndPosition, int positionLeft, int positionRight, O messageLeftType, O messageRightType,
                        String[] keysLeft, String[] keysRight,
                        ConfiguratorHolderMessageAPI[] valuesLeft, ConfiguratorHolderMessageAPI[] valuesRight,
                        HolderItemAPI<P> apiLeftItem, HolderItemAPI<P> apiRightItem) {
        if (itemsStartPosition > itemsEndPosition) return;
        this.itemsStartPosition = itemsStartPosition;
        this.itemsEndPosition = itemsEndPosition;
        
        ItemStack iLeft = skyUtilities.getAPI().getItemAPI().getSkullAPI().itemFromBase64(Skull.OAK_LEFT.getBase64());
        if (iLeft == null)
            iLeft = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(ColorType.SILVER.getWoolData()).getItem();
        this.itemOnly.put(positionLeft, new HolderItem<>(messageLeftType, new ItemCreator(iLeft), keysLeft, valuesLeft, apiLeftItem));
        
        ItemStack iRight = skyUtilities.getAPI().getItemAPI().getSkullAPI().itemFromBase64(Skull.OAK_RIGHT.getBase64());
        if (iRight == null)
            iRight = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(ColorType.SILVER.getWoolData()).getItem();
        this.itemOnly.put(positionRight, new HolderItem<>(messageRightType, new ItemCreator(iRight), keysRight, valuesRight, apiRightItem));
    }
    
    public void addPage(int itemsStartPosition, int itemsEndPosition, O messageLeftType, O messageRightType,
                        String[] keysLeft, String[] keysRight,
                        ConfiguratorHolderMessageAPI[] valuesLeft, ConfiguratorHolderMessageAPI[] valuesRight,
                        HolderItemAPI<P> apiLeftItem, HolderItemAPI<P> apiRightItem) {
        if (itemsStartPosition > itemsEndPosition) return;
        this.itemsStartPosition = itemsStartPosition;
        this.itemsEndPosition = itemsEndPosition;
        
        ItemStack iLeft = skyUtilities.getAPI().getItemAPI().getSkullAPI().itemFromBase64(Skull.OAK_LEFT.getBase64());
        if (iLeft == null)
            iLeft = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(ColorType.SILVER.getWoolData()).getItem();
        int positionLeft = getSlots() - 5;
        this.itemOnly.put(positionLeft, new HolderItem<>(messageLeftType, new ItemCreator(iLeft), keysLeft, valuesLeft, apiLeftItem));
        
        ItemStack iRight = skyUtilities.getAPI().getItemAPI().getSkullAPI().itemFromBase64(Skull.OAK_RIGHT.getBase64());
        if (iRight == null)
            iRight = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(ColorType.SILVER.getWoolData()).getItem();
        int positionRight = getSlots() - 3;
        this.itemOnly.put(positionRight, new HolderItem<>(messageRightType, new ItemCreator(iRight), keysRight, valuesRight, apiRightItem));
    }
    
    protected boolean onClickRightPage(Player player, int page) {
        return true;
    }
    
    
    private ItemCreator addToCache(final short glassData, final ChatColor color, final ConfiguratorHolderType type, final int typePosition, final ConfiguratorHolderAPI api) {
        final int value = type.getValues()[typePosition];
        final String name = color + type.getStrings()[typePosition];
        cache.put(name, new ConfiguratorHolderData(value, api));
        return new ItemCreator(Material.STAINED_GLASS).setDurability(glassData).setName(name);
    }
    
    /**
     * Set title with an addon (Value no static)
     * <p>
     * <p>
     * <p>
     * Example: To title "§8[ §6Spec - §71 §8]" addon is 1 so [prefix] = "§8[ §6Spec - §7" and [suffix] = " §8]"
     * <p>
     * Example: To title "§6Duel §bXeroSs" addon is XeroSs so [prefix] = "§6Duel §b" and [suffix] = ""
     */
    protected void setTitleWithAddons(String prefix, String suffix) {
        this.titlePrefix = prefix;
        this.titleSuffix = suffix;
        this.isAddons = true;
    }
    
    protected int getPageFromTitle(String title) {
        if (titleSuffix == null || titlePrefix == null) return 1;
        final String indexTitle = title.replace(titlePrefix, "").replace(titleSuffix, "");
        try {
            return Integer.parseInt(indexTitle);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    
    protected String getAddonFromTitle(String title) {
        if (titleSuffix == null || titlePrefix == null) return null;
        return title.replace(titlePrefix, "").replace(titleSuffix, "");
    }
    
    public void addPainting(final ConfiguratorHolderPaintingType painting, final int slots, final ItemCreator[] items, ConfiguratorHolderPaintingMaterialType type) {
        if (sizeInventory < 3 && painting != ConfiguratorHolderPaintingType.FULLY) return;
        itemPainting.clear();
        itemPainting.putAll(painting.getApi().painting(skyUtilities, slots, items, type));
    }
    
    public void add(final ConfiguratorHolderSizeType size, final ConfiguratorHolderType type, final ItemCreator item, final O messageType, final String[] keys, final ConfiguratorHolderMessageAPI[] values, final ConfiguratorHolderAPI api) {
        fromItems.add(new ConfiguratorHolder<>(size, type, messageType, keys, values, item, api));
    }
    
    public void add(final int position, final ConfiguratorHolderSizeType size, final ConfiguratorHolderType type, final ItemCreator item, final O messageType, final String[] keys, final ConfiguratorHolderMessageAPI[] values, final ConfiguratorHolderAPI api) {
        fromItemsWithPosition.put(position, new ConfiguratorHolder<>(size, type, messageType, keys, values, item, api));
    }
    
    public void add(int position, ItemCreator item, O messageType, String[] keys, ConfiguratorHolderMessageAPI[] values, HolderItemAPI<P> apiItem) {
        itemOnly.put(position, new HolderItem<>(messageType, item, keys, values, apiItem));
    }
    
    public void add(ItemCreator item, O messageType, String[] keys, ConfiguratorHolderMessageAPI[] values, HolderItemAPI<P> apiItem) {
        itemOnlyToPlaceAtCenter.add(new HolderItem<>(messageType, item, keys, values, apiItem));
    }
    
    public void addWithLore(ItemCreator item, L messageType, String[] keys, ConfiguratorHolderMessageAPI[] values, HolderItemAPI<P> apiItem) {
        itemWithLoreToPlaceAtCenter.add(new HolderItemWithLore<>(messageType, item, keys, values, apiItem));
    }
    
    private <A> ArrayList<Integer> getIndexForPlaceItemAtCenter(final int sizeHeight, final int spaceBetweenItemsAndBorderRightAndLeft, ArrayList<A> items) {
        
        final ArrayList<Integer> indexes = new ArrayList<>();
        
        if (sizeHeight <= 0) return indexes;
        final int amount = items.size();
        if (amount <= 0) return indexes;
        
        if (spaceBetweenItemsAndBorderRightAndLeft < 0 || spaceBetweenItemsAndBorderRightAndLeft > 4) return indexes;
        
        final int sizeWeight = 9;
        final int slots = ((sizeHeight * sizeWeight) - 1);
        
        float centerCalculate = (((float) slots) / 2.0f);
        if ((slots % 2) != 0) centerCalculate -= (sizeWeight / 2.0f);
        
        final int center = ((int) centerCalculate);
        
        final float linesCalculate = (amount / (sizeWeight - 2.0f));
        
        int indexLines = (((int) linesCalculate) < linesCalculate) ? (((int) linesCalculate) + 1) : ((int) linesCalculate);
        int adding = 0;
        boolean up = false;
        boolean isAdding = true;
        int amount_index = amount;
        int array_index = 0;
        
        if (sizeHeight == 1) {
            int c = 4;
            
            if (amount_index >= 8 || amount_index % 2 != 0) {
                final Object item = getFirstOrNull(array_index, items);
                amount_index--;
                array_index++;
                if (item == null) return indexes;
                //   itemOnly.put(c, item);
                indexes.add(c);
            }
            
            boolean add = true;
            int c_left = c;
            int c_right = c;
            
            while (amount_index > 0) {
                
                if (add) c_right += 1;
                else c_left -= 1;
                
                if (c_left < spaceBetweenItemsAndBorderRightAndLeft || c_right > (8 - spaceBetweenItemsAndBorderRightAndLeft))
                    return indexes;
                
                if (c_left <= 0 || c_right >= 8) return indexes;
                final Object item = getFirstOrNull(array_index, items);
                amount_index--;
                array_index++;
                if (item == null) return indexes;
                indexes.add((add ? c_right : c_left));
                //    itemOnly.put((add ? c_right : c_left), item);
                add = !add;
            }
            return indexes;
        }
        
        while (indexLines >= 0) {
            
            int c = ((center - ((sizeWeight / 2) - 1)) - 1);
            
            if (adding != 0) {
                if (up) c = (c + adding);
                else {
                    c = (c - adding);
                    isAdding = true;
                }
            }
            
/*
            if (c <= 7 && lines > 1) {
                c = ((center - ((sizeWeight / 2) - 1)) - 1);
            }
            */
            
            if (c >= (slots + 1)) return indexes;
            
            if (c < 0 || c > slots) {
                return indexes;
            }
            
/*            if (indexLines == 1) {
                
                int index = (c + 4);
                
                boolean add = true;
                int c_left = index;
                int c_right = index;
                
                for (int i = spaceBetweenItemsAndBorderRightAndLeft; i < (9 - spaceBetweenItemsAndBorderRightAndLeft); i++) {
                    
                    if (i == spaceBetweenItemsAndBorderRightAndLeft && amount_index < 8 && amount_index % 2 != 0) {
                        final HolderItem item = getFirstOrNull(array_index);
                        amount_index--;
                        array_index++;
                        if (item == null) return;
                        itemOnly.put(index, item);
                        index++;
                        continue;
                    }
                    
                    if (add) c_right += 1;
                    else c_left -= 1;
                    
                    final HolderItem item = getFirstOrNull(array_index);
                    amount_index--;
                    array_index++;
                    if (item == null) return;
                    itemOnly.put((add ? c_right : c_left), item);
                    add = !add;
                }
                
                //  return;
                
                up = !up;
                if (isAdding) {
                    adding += 9;
                    isAdding = false;
                }
                indexLines--;
                continue;
            }*/
            
            int index = (c + 4);
            
            boolean add = true;
            int c_left = index;
            int c_right = index;
            
            for (int i = spaceBetweenItemsAndBorderRightAndLeft; i < (9 - spaceBetweenItemsAndBorderRightAndLeft); i++) {
                
                if (i == spaceBetweenItemsAndBorderRightAndLeft && (amount_index > 8 - (spaceBetweenItemsAndBorderRightAndLeft + spaceBetweenItemsAndBorderRightAndLeft) || amount_index % 2 != 0)) {
                    final Object item = getFirstOrNull(array_index, items);
                    amount_index--;
                    array_index++;
                    if (item == null) return indexes;
                    //   itemOnly.put(index, item);
                    indexes.add(index);
                    index++;
                    continue;
                }
                
                if (add) c_right += 1;
                else c_left -= 1;
                
                final Object item = getFirstOrNull(array_index, items);
                amount_index--;
                array_index++;
                if (item == null) return indexes;
                //   itemOnly.put((add ? c_right : c_left), item);
                indexes.add((add ? c_right : c_left));
                add = !add;
            }
            
            up = !up;
            if (isAdding) {
                adding += 9;
                isAdding = false;
            }
            indexLines--;
        }
        
        return indexes;
    }
    
    private void placeToCenter(final int sizeHeight, final int spaceBetweenItemsAndBorderRightAndLeft) {
        final ArrayList<Integer> indexes = getIndexForPlaceItemAtCenter(sizeHeight, spaceBetweenItemsAndBorderRightAndLeft, itemOnlyToPlaceAtCenter);
        Collections.sort(indexes);
        final int size = itemOnlyToPlaceAtCenter.size();
        for (int i = 0; i < size; i++) {
            
            final HolderItem<P, O> item = itemOnlyToPlaceAtCenter.size() <= i ? null : itemOnlyToPlaceAtCenter.get(i);
            if (item == null) return;
            
            final Integer index = indexes.size() <= i ? null : indexes.get(i);
            if (index == null) return;
            
            itemOnly.put(index, item);
        }
    }
    
    private void placeWithLoreToCenter(final int sizeHeight, final int spaceBetweenItemsAndBorderRightAndLeft) {
        final ArrayList<Integer> indexes = getIndexForPlaceItemAtCenter(sizeHeight, spaceBetweenItemsAndBorderRightAndLeft, itemWithLoreToPlaceAtCenter);
        Collections.sort(indexes);
        final int size = itemWithLoreToPlaceAtCenter.size();
        for (int i = 0; i < size; i++) {
            
            final HolderItemWithLore<P, L> item = itemWithLoreToPlaceAtCenter.size() <= i ? null : itemWithLoreToPlaceAtCenter.get(i);
            if (item == null) return;
            
            final Integer index = indexes.size() <= i ? null : indexes.get(i);
            if (index == null) return;
            
            itemWithLoreOnly.put(index, item);
        }
    }
    
    private <A> Object getFirstOrNull(int array_index, ArrayList<A> items) {
        return items.size() <= array_index ? null : items.get(array_index);
    }
    
    public <T extends Enum<T>> void placeWithLore(final P plugin, final LangLinesWithTitleHandler<T, L> langMessageHandler,
                                                  final Player player, final ItemStack[] itemSlot, String addons, T lang) {
        for (Map.Entry<Integer, HolderItemWithLore<P, L>> item : itemWithLoreOnly.entrySet()) {
            final HolderItemWithLore<P, L> value = item.getValue();
            if (item.getValue().getType() == null) {
                itemSlot[item.getKey()] = value.getItem() == null ? null : value.getItem().getItem();
                continue;
            }
            ArrayList<String> lore = new ArrayList<>();
            MessagesHolder itemMessage = skyUtilities.getAPI().getMessageAPI().getLinesWithTitle(langMessageHandler, value.getType(), lang, value.getKeys(),
                    Arrays.stream(value.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new));
            itemSlot[item.getKey()] = item.getValue().getKeys() == null ? value.getItem().setName(itemMessage.getTitle()).getItem() :
                    value.getItem().setName(itemMessage.getTitle()).addLore(itemMessage.getLines()).getItem();
    /*
            itemSlot[item.getKey()] = item.getValue().getKeys() == null ? value.getItem().setName(main.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(),
                    lang)).getItem() : value.getItem().setName(main.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(), lang, value.getKeys(),
                    Arrays.stream(value.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new))).getItem();*/
        }
    }
    
    
    public <T extends Enum<T>> void place(final P plugin, final LangMessageHandler<T, O> langMessageHandler,
                                          final Player player, final ItemStack[] itemSlot, String addons, T lang) {
        if (!itemPainting.isEmpty()) itemPainting.forEach((integer, itemStack) -> {
            if (itemSlot[integer] == null) itemSlot[integer] = itemStack;
        });
        for (Map.Entry<Integer, ConfiguratorHolderItem<P, O>> items : items.entrySet()) {
            final ConfiguratorHolderItem<P, O> value = items.getValue();
            if (value.getType() == null) {
                itemSlot[items.getKey()] = value.getItem().getItem();
                continue;
            }
            itemSlot[items.getKey()] = value.getKeys() == null ? value.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(),
                    lang)).getItem() : value.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(), lang, value.getKeys(),
                    Arrays.stream(value.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new))).getItem();
        }
        for (Map.Entry<Integer, HolderItem<P, O>> item : itemOnly.entrySet()) {
            final HolderItem<P, O> value = item.getValue();
            if (item.getValue().getType() == null) {
                itemSlot[item.getKey()] = value.getItem() == null ? null : value.getItem().getItem();
                continue;
            }
            itemSlot[item.getKey()] = item.getValue().getKeys() == null ? value.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(),
                    lang)).getItem() : value.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, value.getType(), lang, value.getKeys(),
                    Arrays.stream(value.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new))).getItem();
        }
    }
    
    protected <T extends Enum<T>> void placePage(final P plugin, final LangMessageHandler<T, O> langMessageHandler,
                                                 final Player player, final ItemStack[] itemSlot,
                                                 String addons, ArrayList<HolderItem<P, O>> allItemsForPage,
                                                 T lang) {
        if (!allItemsForPage.isEmpty()) {
            final ArrayList<Integer> spaces = getSpace();
            final int size = spaces.size();
            List<HolderItem<P, O>> items = PageUtils.getPageItems(allItemsForPage, getPageFromTitle(addons), size);
            if (items.isEmpty()) return;
            for (int i = 0; i < size; i++) {
                if (indexNotAvailableForPage.contains(i)) continue;
                Integer index = spaces.get(i);
                if (index == null) continue;
                if (items.size() <= i) return;
                HolderItem<P, O> item = items.get(i);
                if (item == null) continue;
                if (item.getType() == null) {
                    itemSlot[index] = item.getItem().getItem();
                    continue;
                }
                itemSlot[index] = item.getKeys() == null ? item.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, item.getType(),
                        lang)).getItem() : item.getItem().setName(skyUtilities.getAPI().getMessageAPI().getMessage(langMessageHandler, item.getType(), lang, item.getKeys(),
                        Arrays.stream(item.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new))).getItem();
            }
        }
    }
    
    protected <T extends Enum<T>> void placePageWithLore(final P plugin, final LangLinesWithTitleHandler<T, L> langLinesWithTitleHandler,
                                                         final Player player, final ItemStack[] itemSlot, String addons,
                                                         ArrayList<HolderItemWithLore<P, L>> allItemsForPage,
                                                         T lang) {
        if (!allItemsForPage.isEmpty()) {
            final ArrayList<Integer> spaces = getSpace();
            final int size = spaces.size();
            List<HolderItemWithLore<P, L>> items = PageUtils.getPageItems(allItemsForPage, getPageFromTitle(addons), size);
            if (items.isEmpty()) return;
            for (int i = 0; i < size; i++) {
                if (indexNotAvailableForPage.contains(i)) continue;
                Integer index = spaces.get(i);
                if (index == null) continue;
                if (items.size() <= i) return;
                HolderItemWithLore<P, L> item = items.get(i);
                if (item == null) continue;
                if (item.getType() == null) {
                    itemSlot[index] = item.getItem().getItem();
                    continue;
                }
                ArrayList<String> lore = new ArrayList<>();
                MessagesHolder itemMessage = skyUtilities.getAPI().getMessageAPI().getLinesWithTitle(langLinesWithTitleHandler, item.getType(), lang, item.getKeys(),
                        Arrays.stream(item.getValue()).map(configuratorHolderMessageAPI -> configuratorHolderMessageAPI.get(player)).toArray(String[]::new));
                itemSlot[index] = item.getKeys() == null ? item.getItem().setName(itemMessage.getTitle()).getItem() :
                        item.getItem().setName(itemMessage.getTitle()).addLore(itemMessage.getLines()).getItem();
            }
        }
    }
    
    private ArrayList<Integer> getSpace() {
        final ArrayList<Integer> spaces = new ArrayList<>();
        final int max = (itemsEndPosition + 1);
        for (int i = itemsStartPosition; i < max; i++) {
            if (indexNotAvailableForPage.contains(i)) continue;
            spaces.add(i);
        }
        return spaces;
    }
    
    @Override
    public void animated(Player player, Inventory inventory, String addonsForTile) {
    }
    
    @Override
    public boolean isGUI(String title) {
        if (isAddons) {
            String addon = title.replace(titlePrefix, "").replace(titleSuffix, "");
            return title.equals(getName(addon));
        }
        return title.equals(getName(""));
    }
    
    private void initialize(final Integer position, final ConfiguratorHolder<P, O> holder) {
        final Integer center = position == null ? getCenter() : position;
        if (center == null) return;
        
        items.put(center, new ConfiguratorHolderItem<>(holder.getMessageType(), holder.getItem(), holder.getKeys(), holder.getValues()));
        
        final ChatColor chatColor = colors[indexColor];
        final ConfiguratorHolderSizeType size = holder.getSize();
        for (int i = 0, j = 0; i < size.getSize(); i++, j = (j + 2)) {
            items.put((center - (i + 1)), new ConfiguratorHolderItem<>(null, addToCache(glasses[i], chatColor, holder.getType(), (j + 1), holder.getApi()), null, null));
            items.put((center + (i + 1)), new ConfiguratorHolderItem<>(null, addToCache(glasses[i], chatColor, holder.getType(), j, holder.getApi()), null, null));
        }
        
        indexColor++;
    }
    
    @Override
    public void onClick(final P plugin, Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta) {
        e.setCancelled(true);
        if (meta == null || meta.getDisplayName() == null) return;
        onClick(plugin, e, meta.getDisplayName(), player, currentItem, meta);
    }
    
    private Integer getCenter() {
        if (sizeInventory <= 0) return null;
        
        final int sizeWeight = 9;
        final int slots = ((sizeInventory * sizeWeight) - 1);
        
        float centerCalculate = (((float) slots) / 2.0f);
        if ((slots % 2) != 0) centerCalculate -= (sizeWeight / 2.0f);
        
        final int center = ((int) centerCalculate);
        
        if (!isUp) {
            final int iCenter = (center - up);
            up = (up + 9);
            isUp = true;
            if (down <= 0) down = (down + 9);
            return iCenter;
        }
        
        final int iCenter = (center + up);
        down = (down + 9);
        isUp = false;
        
        return iCenter;
    }
    
    private static String getServerVersion() {
        String version;
        try {
            
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            
        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return "unknown";
        }
        return version;
    }
    
    @SuppressWarnings("UnusedReturnValue")
    public boolean onClick(final P plugin, final InventoryClickEvent e, final String name, final Player player, ItemStack currentItem, ItemMeta meta) {
        if (name == null || name.equals("") || name.equals("§r")) return false;
        final HolderItem<P, O> apiItem = itemOnly.get(e.getRawSlot());
        if (apiItem != null) {
            if (apiItem.getAPI() == null) return false;
            apiItem.getAPI().perform(plugin, name, player, e.getClickedInventory().getTitle(), e);
            return false;
        }
        final HolderItemWithLore<P, L> apiItemWithLore = itemWithLoreOnly.get(e.getRawSlot());
        if (apiItemWithLore != null) {
            if (apiItemWithLore.getAPI() == null) return false;
            apiItemWithLore.getAPI().perform(plugin, name, player, e.getClickedInventory().getTitle(), e);
            return false;
        }
        final ConfiguratorHolderData api = cache.get(name);
        if (api == null) return true;
        if (api.getApi() == null) return false;
        api.getApi().perform(api.getValue(), player);
        return false;
    }
    
    public void setup(final int spaceBetweenItemsAndBorderRightAndLeft) {
        final int size = fromItems.size();
        int center = ((size % 2) != 0) ? (size / 2) : ((size / 2) - 1);
        int down = center;
        int up = center;
        boolean isDown = true;
        for (int i = 0; i < size; i++) {
            final ConfiguratorHolder<P, O> holder = isDown ? fromItems.get(down) : fromItems.get(up);
            initialize(null, holder);
            if (isDown) down = down - 1;
            else up = up + 1;
            if (up == center) up = up + 1;
            isDown = !isDown;
        }
        
        placeToCenter(sizeInventory, spaceBetweenItemsAndBorderRightAndLeft);
        placeWithLoreToCenter(sizeInventory, spaceBetweenItemsAndBorderRightAndLeft);
        fromItemsWithPosition.forEach(this::initialize);
        
        if (isAddons) {
            indexNotAvailableForPage.addAll(itemOnly.keySet());
            indexNotAvailableForPage.addAll(itemPainting.keySet());
            indexNotAvailableForPage.addAll(items.keySet());
            indexNotAvailableForPage.addAll(fromItemsWithPosition.keySet());
        }
    }
    
    public void setup() {
        final int size = fromItems.size();
        int center = ((size % 2) != 0) ? (size / 2) : ((size / 2) - 1);
        int down = center;
        int up = center;
        boolean isDown = true;
        for (int i = 0; i < size; i++) {
            final ConfiguratorHolder<P, O> holder = isDown ? fromItems.get(down) : fromItems.get(up);
            initialize(null, holder);
            if (isDown) down = down - 1;
            else up = up + 1;
            if (up == center) up = up + 1;
            isDown = !isDown;
        }
        
        placeToCenter(sizeInventory, 0);
        placeWithLoreToCenter(sizeInventory, 0);
        fromItemsWithPosition.forEach(this::initialize);
        
        if (isAddons) {
            indexNotAvailableForPage.addAll(itemOnly.keySet());
            indexNotAvailableForPage.addAll(itemPainting.keySet());
            indexNotAvailableForPage.addAll(items.keySet());
            indexNotAvailableForPage.addAll(fromItemsWithPosition.keySet());
        }
    }
}
