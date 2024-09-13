package net.maxchip.spellmod.item.custom;

import net.maxchip.spellmod.item.ModItems;
import net.maxchip.spellmod.item.custom.EvocationSpellItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtByte;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Wandtem extends Item {

    public Wandtem(Settings settings) {
        super(settings);
    }

    //NbtByte spell;

    Item spell;
    
    static final Item[] spells = {
            ModItems.EVOCATION_SPELL_ITEM,
            ModItems.LIGHTNING_SPELL_ITEM,
            ModItems.SHULKER_SPELL_ITEM,
            ModItems.WITHER_SPELL_ITEM,
            ModItems.WARDEN_SPELL_ITEM,
            ModItems.WIND_SPELL_ITEM,
            ModItems.DRAGON_SPELL_ITEM
    };

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        boolean offhand = false;
        boolean hotbar = false;

        if (!world.isClient) {

            if (user.isSneaking()) {
                for (Item item : spells) {
                    if (user.getOffHandStack().isOf(item)) {
                        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1f, 1f);
                        spell = user.getOffHandStack().getItem();
                        user.sendMessage(Text.of("Loaded " + spell.getName().getString()), true);
                        offhand = true;
                        break;
                    }
                }

                 if (!offhand) {
                    for (int i = 8; i >= 0; i--) {
                        user.sendMessage(Text.of(user.getInventory().getStack(i).getName()));
                        for (Item item : spells) {
                            if ((user.getInventory().getStack(i)).isOf(item)) {
                                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1f, 1f);
                                spell = user.getInventory().getStack(i).getItem();
                                user.sendMessage(Text.of("Loaded " + spell.getName().getString()), true);
                                break;
                            }
                        }
                    }
                }
            }
            else if (user.getItemCooldownManager().getCooldownProgress(spell, 0) == 0f) {
                if (spell == spells[0]) {
                    EvocationSpellItem.spell(world, user);
                    user.getItemCooldownManager().set(this, 20);
                    user.addExperienceLevels(-1);
                    itemStack.damage(1, user, EquipmentSlot.MAINHAND);
                } else if (spell == spells[1]) {
                    LightningSpellItem.spell(world, user);
                    user.getItemCooldownManager().set(this, 20);
                    user.addExperienceLevels(-1);
                    itemStack.damage(1, user, EquipmentSlot.MAINHAND);
                }
            }

        }
        return super.use(world, user, hand);
    }
}
