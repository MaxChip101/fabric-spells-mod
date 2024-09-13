package net.maxchip.spellmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ShulkerSpellItem extends Item {
    public ShulkerSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);



        if (!world.isClient && user.experienceLevel > 0 || !world.isClient && user.isInCreativeMode()) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.NEUTRAL, 1f, 1f);
            user.getItemCooldownManager().set(this, 100);
            user.addExperienceLevels(-1);
            itemStack.damage(1, user, EquipmentSlot.MAINHAND);
            world.addParticle(ParticleTypes.ELECTRIC_SPARK, true, user.getX(), user.getY() + user.getHeight() / 2, user.getZ(), 0, 0, 0);
            ShulkerBulletEntity bullet = new ShulkerBulletEntity(EntityType.SHULKER_BULLET, world);
            bullet.setOwner(user);
            bullet.setYaw(user.getHeadYaw());
            bullet.setPitch(user.getPitch());
            bullet.updatePosition(user.getX(), user.getY() + user.getHeight() / 2, user.getZ());
            bullet.setVelocity(user.getRotationVector().x, user.getRotationVector().y, user.getRotationVector().z, 1, 0);
            world.spawnEntity(bullet);
        }

        return super.use(world, user, hand);
    }
}
