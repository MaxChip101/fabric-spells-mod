package net.maxchip.spellmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WindSpellItem extends Item {
    public WindSpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient && user.experienceLevel > 0 || !world.isClient && user.isInCreativeMode()) {
            float yawRadians = (float) Math.toRadians(user.getHeadYaw());
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.NEUTRAL, 1f, 1f);
            user.getItemCooldownManager().set(this, 100);
            user.addExperienceLevels(-1);
            itemStack.damage(1, user, EquipmentSlot.MAINHAND);
            user.swingHand(hand, true);
            // middle
            WindChargeEntity bullet1 = new WindChargeEntity(EntityType.WIND_CHARGE, world);
            bullet1.setOwner(user);
            bullet1.setYaw(user.getHeadYaw());
            bullet1.setPitch(user.getPitch());
            bullet1.updatePosition(user.getX(), user.getY() + user.getHeight() / 2, user.getZ());
            bullet1.setVelocity(user.getRotationVector().x, user.getRotationVector().y, user.getRotationVector().z, 1, 0);
            world.spawnEntity(bullet1);

            // right
            WindChargeEntity bullet2 = new WindChargeEntity(EntityType.WIND_CHARGE, world);
            bullet2.setOwner(user);
            bullet2.setYaw(user.getHeadYaw());
            bullet2.setPitch(user.getPitch() + 45);
            bullet2.updatePosition(user.getX(), user.getY() + user.getHeight() / 2, user.getZ());
            bullet2.setVelocity(Math.cos(yawRadians + Math.toRadians(135)), user.getRotationVector().y, Math.sin(yawRadians + Math.toRadians(135)), 1, 0);
            world.spawnEntity(bullet2);

            // left
            WindChargeEntity bullet3 = new WindChargeEntity(EntityType.WIND_CHARGE, world);
            bullet3.setOwner(user);
            bullet3.setYaw(user.getHeadYaw());
            bullet3.setPitch(user.getPitch() - 45);
            bullet3.updatePosition(user.getX(), user.getY() + user.getHeight() / 2, user.getZ());
            bullet3.setVelocity(Math.cos(yawRadians + Math.toRadians(45)), user.getRotationVector().y, Math.sin(yawRadians + Math.toRadians(45)), 1, 0);
            world.spawnEntity(bullet3);

        }

        return super.use(world, user, hand);
    }
}
