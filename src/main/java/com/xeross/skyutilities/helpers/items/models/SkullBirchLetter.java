package com.xeross.skyutilities.helpers.items.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SkullBirchLetter {

    A("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ViMmE5NTg2Mjg5ZjM1YjJkNWYyYjFkYjI5NmNiNzc1MjhlMjBiNzkwM2U3N2NjNTNhYWM1MzhkNTk2NzMifX19"),
    B("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjQ3MjU3YWZkMzE1MThkOTRlODAyZTk5MzU2MjZiZjkzZTU1YWE2Zjc3YjU2YmE3ZTM3ZjhiZTRlNTdkMSJ9fX0="),
    C("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQ3NjUxYmM4NmI2YWJkODllZTdlYTY1NGQ0NjkwY2E2NDc0ZmFlMWY3ZjZkMjhiYzdkNGU0MTE2YTc0In19fQ=="),
    D("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA2Y2NlMjE0OGY2ODgxY2MxZTAzMzU3ZmQ5NWM4ODE1Njk3MDNkNmVkNjgzMjEyMDYzZTg4OGVjOGRmNmY2In19fQ=="),
    E("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmRmODU2OGQxYjQyZDdjMTFkOTBiZTQ2OTg2NzAzNjhhZmM4YjRhY2I3YzgzODI1NTgyZTJjOWViMTYxN2Q5In19fQ=="),
    F("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI5MjBjMzgxNWI5YzQ1OTJlNjQwOGUzMjIzZjMxMzUxZmM1NzhmMzU1OTFiYzdmOWJlYmQyMWVmZDhhMDk3In19fQ=="),
    G("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVkOGNiNjBlMDMxNTdkNTkwNDUyMmZmODk2MGE3MWYzY2IzM2ZhZDcxNWI1MTU5MzM0OWE2MmRjMDE1NzIzNCJ9fX0="),
    H("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTkxZDU4ZWMyNjI0NDg4N2QyZjg3ZGViNTU4ZDRlOTJiNDE0NTQ4MGM5MzFkODQyODIzMDk1NGE3YjMxZTZkMSJ9fX0="),
    I("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJjZGM2Zjg4Yzg1M2U4MzE0OTVhMTc0NmViMjdhYTYxYjlkYWMyZTg2YTQ0Yjk1MjJlM2UyYjdkYzUifX19"),
    J("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I0MTBjMGFiNDUyODE2NmNmZWNjMTRhNGUyMjM4YmZlYTAyYzMzN2NhODkzMzAxMTU3YzkzNTRmMzFiNTUifX19"),
    K("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY1YTExNWU5NzRkNTIxZjFiMzg5YmIzYmE0Y2I1OTM5YjI2NDYxN2M4MzhkYWE2NmI2YjhkYzUxNzFhOTYifX19"),
    L("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBiZDgwZGExOTllYzk2YzJhNDMxNWQ2MjdjNDU1YjhiMDUwMzFiNjEyMWQ4ZDRiYTI5N2VlOGFkOWY3M2VjIn19fQ=="),
    M("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlhOTUwY2EyZjlmZTZhMTlhNmFjN2U4OTRhYzUyNzM3Y2RjNDA0ZDg2ODAxNDIyNTI2ZjlhZTVlZTllOGYifX19"),
    N("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2M0OTEzYWZjNTI1MjZhZGRkOWU3NzNmODkwOTQyODdkZTZhNWJiNzc1OTVhOGI1Yzk3YWQ0MGJiNDc0ZTYifX19"),
    O("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTgxYWQ3YjQ0MWYxNDNmNzlmODhlNzljYTVjNjc0ZmZkYTkxNTIwZmUyZTFkZTQxYTcwMWFiZGNkYzBlYyJ9fX0="),
    P("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDlmODk0OWNiOWQ5ODQ0YmJjOThhMDhlZTg1YmZjNjQ4NThiMTUyZTZiN2QyMTQ2M2NjMjVkNWJlNzI4ZGM0In19fQ=="),
    Q("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJiMjIxY2FiMTZlOWM4OTRjYjdhODZlYzg3NDkzNjU3MzEzZjU4YTNlZjQ0ZTU4ODg1ZTE0NTI2OTYzZCJ9fX0="),
    R("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWMxZWE4MjA0Y2FiOGYzMzI3ZmZjZWY0OTJkMTkzZGE2MzQ0YThmODY0NTUyNDQyZTE1NWZlNzNiYjZiYSJ9fX0="),
    S("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRlYjgyNDVlOTJlYjY4YmI3N2VhMjZkNTU5YzM4YTNhZGYxOGYzY2VhNWJmMWRkZWM3ZDdmOGM1NTQ0NDhiIn19fQ=="),
    T("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhOWZiMzU1MmQ1NTE1NTNkOWRkNDNjMmJiMWQyNjg4OTNkZjY4ZDczZTQ2MTEzNDNiNTcyYWU2NDI1Y2EifX19"),
    U("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjdmNzM4ZWI1MDdlNTczMmIxNzcyYTUzYjBkZWFjMTg3YzkyYTc3ZmFkY2U4MjUyODcwNjU4NmVlMTlkIn19fQ=="),
    V("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRjMzkzZThkMzNiZTE5MmFjYWU5MzU1Zjc5ZWJhNjkzMDJjM2JmNDQxYWYxMGE2OGQxMTQ2YWM2NWU3In19fQ=="),
    W("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM0ZTI0OGU0MWQyOGViMzBiMjlmZDMxYTRlY2E2MDZlMTY3YmRmNjNhZjM2ZWZhZjk3MWFjNGE1NzMxZjMifX19"),
    X("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWY3NGQ0ZGI0Y2MzYmU0MWEzNzNkOWVmOWNhYzI3ZTYzNThjNTNmNjQxMTVkMTUwMjQzZjI1YWNmNjRmMmY1MCJ9fX0="),
    Y("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzljMTBkODI4MzkyNmQ3MjBmZDNkZTE1YzRlNGNkM2UxNTlmYjI1NmY3ZmE4ZDg5N2ViMmYxNGFiOGExOCJ9fX0="),
    Z("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDY0OTE3YzI0MTQ5NDFlZmY3ZTAxYmM5YmQxNTljNjk5ZTliZWUyZDg4ZTMxNWVhZDJiOWNlYzBjYmU1MWM5OCJ9fX0=");


    private final ItemStack defaultMaterial;
    private final String base64;

    SkullBirchLetter(String base64) {
        this.defaultMaterial = new ItemStack(Material.PAPER);
        this.base64 = base64;
    }

    public ItemStack getDefaultMaterial() {
        return defaultMaterial;
    }

    public String getBase64() {
        return base64;
    }
}
