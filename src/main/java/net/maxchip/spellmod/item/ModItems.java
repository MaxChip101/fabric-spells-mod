package net.maxchip.spellmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.maxchip.spellmod.SpellMod;
import net.maxchip.spellmod.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item EVOCATION_SPELL_ITEM = registerItem("evocation_spell_item", new EvocationSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item LIGHTNING_SPELL_ITEM = registerItem("lightning_spell_item", new LightningSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item SHULKER_SPELL_ITEM = registerItem("shulker_spell_item", new ShulkerSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item WITHER_SPELL_ITEM = registerItem("wither_spell_item", new WitherSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item WAND_ITEM = registerItem("wand_item", new Wandtem(new Item.Settings().maxDamage(20)));
    public static final Item WARDEN_SPELL_ITEM = registerItem("warden_spell_item", new WardenSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item DRAGON_SPELL_ITEM = registerItem("dragon_spell_item", new DragonSpellItem(new Item.Settings().maxDamage(20)));
    public static final Item WIND_SPELL_ITEM = registerItem("wind_spell_item", new WindSpellItem(new Item.Settings().maxDamage(20)));

    private static void addItemsToItemGroup(FabricItemGroupEntries entries) {
        entries.add(WAND_ITEM);
        entries.add(EVOCATION_SPELL_ITEM);
        entries.add(LIGHTNING_SPELL_ITEM);
        entries.add(SHULKER_SPELL_ITEM);
        entries.add(WITHER_SPELL_ITEM);
        entries.add(WARDEN_SPELL_ITEM);
        entries.add(DRAGON_SPELL_ITEM);
        entries.add(WIND_SPELL_ITEM);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SpellMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SpellMod.LOGGER.info("Registering Mod Items for: " + SpellMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToItemGroup);
    }

}
