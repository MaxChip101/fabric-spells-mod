package net.maxchip.spellmod.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.maxchip.spellmod.SpellMod;

public class ModBlocks {
    /*
    public static final Block GLCHS_BLOCK = registerBlock("glchs_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .instrument(NoteBlockInstrument.DRAGON)
                    .requiresTool()
                    .strength(2.5F, 1000000.0F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));
    */

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SpellMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(SpellMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        SpellMod.LOGGER.info("Registering ModBlocks for: " + SpellMod.MOD_ID);

    }

}
