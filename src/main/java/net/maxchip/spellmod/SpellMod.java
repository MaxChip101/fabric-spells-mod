package net.maxchip.spellmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.maxchip.spellmod.item.ModItemGroups;
import net.maxchip.spellmod.item.ModItems;
import net.maxchip.spellmod.block.ModBlocks;

public class SpellMod implements ModInitializer {
	public static final String MOD_ID = "spellmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Spells Mod has been initialized: " + MOD_ID);
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
/*
Sentry (found in pillager outposts) - Ravager Roar (but with more knockback dealt)
Vex (Found in Woodland Mansions) - Evocation fangs (I know we said this should be sentry earlier but makes more sense for Vex since evokers spawn in woodland mansions, not in outposts)
Wild (Found in Jungle Temples) - Wither skin
Coast (Found in Shipwrecks) - Riptide II (no need for water)
Tide (Found in Ocean Monuments) - Powerful Elder Guardian Beam
Dune (Found in Desert Temples) - TNT blast around the player (does not damage player who uses it, but does destroy surroundings and provide knockback)
Ward (Found in Ancient Cities) - Gives surrounding players and mobs darkness effect and highlights entities (like spectral arrows do) for the user
Silence (Found in Ancient Cities) - Sonic Boom (it's the rarer of the two so should be more powerful imo)
Snout (Found in Bastions) - Shoots barage of small fireballs (like blazes)
Rib (Found in Nether Fortresses) - Shoots wither head(s) (Maybe 3 cause wither has 3 heads )
Eye (Found in Strongholds) - Dragon Fireball
Spire (Found in End cities) - Fires several Shulker Pellets
Flow (Found in Trial Chambers) - Shoots a much more powerful Wind Charge
Bolt (Found in Trial Chambers) - Summons a series of Lightning Bolts down to smite foes
Wayfinder (Found in Trail Ruins) - Idk man
Raiser (Found in Trail Ruins) - Idk man
Shaper (Found in Trail Ruins) - Idk man
Host (Found in Trail Ruins) - Idk man
 */