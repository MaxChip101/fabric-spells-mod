package net.maxchip.spellmod.item;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.maxchip.spellmod.SpellMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GLCHS_Group = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SpellMod.MOD_ID, "spell"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.spells"))
                    .icon(() -> new ItemStack(ModItems.WAND_ITEM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.WAND_ITEM);
                        entries.add(ModItems.EVOCATION_SPELL_ITEM);
                        entries.add(ModItems.LIGHTNING_SPELL_ITEM);
                        entries.add(ModItems.SHULKER_SPELL_ITEM);
                        entries.add(ModItems.WITHER_SPELL_ITEM);
                        entries.add(ModItems.WARDEN_SPELL_ITEM);
                        entries.add(ModItems.DRAGON_SPELL_ITEM);
                        entries.add(ModItems.WIND_SPELL_ITEM);
                    }).build());

    public static void registerItemGroups() {
        SpellMod.LOGGER.info("Registering Item Groups for: " + SpellMod.MOD_ID);
    }

}
