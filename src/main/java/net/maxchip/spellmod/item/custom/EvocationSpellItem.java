package net.maxchip.spellmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EvocationSpellItem extends Item {

    public EvocationSpellItem(Settings settings) {
        super(settings);
    }

    static double radius = 3.0;
    static int numberOfFangs = 20;
    static double angleIncrement = (2 * Math.PI) / numberOfFangs;

    public static String name = "Evocation";

    public static void spell(World world, PlayerEntity user) {
        for (int i = 0; i < numberOfFangs; i++) {
            double angle = i * angleIncrement;
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            EvokerFangsEntity fang = new EvokerFangsEntity(EntityType.EVOKER_FANGS, world);
            fang.setOwner(user);
            fang.updatePosition(user.getX() + xOffset, user.getY(), user.getZ() + zOffset);
            fang.setYaw(user.getYaw());
            world.spawnEntity(fang);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient && user.experienceLevel > 0 && user.getOffHandStack() != itemStack || !world.isClient && user.isInCreativeMode() && user.getOffHandStack() != itemStack) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.NEUTRAL, 1f, 1f);
            user.getItemCooldownManager().set(this, 200);
            user.addExperienceLevels(-1);
            itemStack.damage(1, user, EquipmentSlot.MAINHAND);

            spell(world, user);


        }
        return super.use(world, user, hand);
    }
}
