package com.xeross.skyutilities.helpers.items.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.UUID;


public class SkullCreator {
    private Method metaSetProfileMethod;
    private Field metaProfileField;

    private ItemStack createSkull() {
        return new ItemStack(Material.SKULL_ITEM, 1, ((short) 3));
    }

    public ItemStack itemFromUuid(UUID id) {
        return itemWithUuid(createSkull(), id);
    }

    public ItemStack itemFromUrl(String url) {
        return itemWithUrl(createSkull(), url);
    }

    public ItemStack itemFromBase64(String base64) {
        return itemWithBase64(createSkull(), base64);
    }

    private ItemStack itemWithUuid(ItemStack item, UUID uuid) {
        SkullMeta skull = ((SkullMeta) item.getItemMeta());
        setOwningPlayer(skull, uuid);
        item.setItemMeta(skull);
        return item;
    }

    private void setOwningPlayer(SkullMeta skull, UUID uuid) {
        skull.setOwner(Bukkit.getOfflinePlayer(uuid).getName());
    }

    private ItemStack itemWithUrl(ItemStack item, String url) {
        notNull(item, "item");
        notNull(url, "url");
        return itemWithBase64(item, urlToBase64(url));
    }

    private ItemStack itemWithBase64(ItemStack item, String base64) {
        notNull(item, "item");
        notNull(base64, "base64");
        if (!(item.getItemMeta() instanceof SkullMeta)) {
            return null;
        }
        SkullMeta meta = ((SkullMeta) item.getItemMeta());
        if (meta == null) return null;
        mutateItemMeta(meta, base64);
        item.setItemMeta(meta);
        return item;
    }

    private void notNull(Object o, String name) {
        if (o == null) {
            throw new NullPointerException(name + " should not be null!");
        }
    }

    private String urlToBase64(String url) {
        URI actualUrl;
        try {
            actualUrl = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"$actualUrl\"}}}";
        return Base64.getEncoder().encodeToString(toEncode.getBytes());
    }

    private GameProfile makeProfile(String b64) {
        UUID id = new UUID(b64.substring(b64.length() - 20).hashCode(), b64.substring(b64.length() - 10).hashCode());
        GameProfile profile = new GameProfile(id, "aaaaa");
        profile.getProperties().put("textures", new Property("textures", b64));
        return profile;
    }

    private void mutateItemMeta(SkullMeta meta, String b64) {
        try {
            if (metaSetProfileMethod == null) {
                metaSetProfileMethod = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                metaSetProfileMethod.setAccessible(true);
            }
            metaSetProfileMethod.invoke(meta, makeProfile(b64));
        } catch (NoSuchMethodException ex) {
            try {
                setMetaProfileField(meta, b64);
            } catch (NoSuchFieldException ex2) {
                ex2.printStackTrace();
            } catch (IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        } catch (IllegalAccessException ex) {
            try {
                setMetaProfileField(meta, b64);
            } catch (NoSuchFieldException ex2) {
                ex2.printStackTrace();
            } catch (IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        } catch (InvocationTargetException ex) {
            try {
                setMetaProfileField(meta, b64);
            } catch (NoSuchFieldException ex2) {
                ex2.printStackTrace();
            } catch (IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    private void setMetaProfileField(SkullMeta meta, String b64) throws NoSuchFieldException, IllegalAccessException {
        if (metaProfileField == null) {
            metaProfileField = meta.getClass().getDeclaredField("profile");
        }
        metaProfileField.setAccessible(true);
        metaProfileField.set(meta, makeProfile(b64));
    }
}
