package net.maxchip.spellmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class LightningSpellItem extends Item {
    public LightningSpellItem(Settings settings) {
        super(settings);
    }

    public static void spell(World world, PlayerEntity user) {
        HitResult hit = user.raycast(10, 1, false);
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        RaycastContext context = new RaycastContext(new Vec3d(hit.getPos().x, 320, hit.getPos().z), new Vec3d(hit.getPos().x, -64, hit.getPos().z), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user);
        HitResult sky_hit = world.raycast(context);
        lightning.updatePosition(hit.getPos().x, sky_hit.getPos().y, hit.getPos().z);
        lightning.setYaw(user.getYaw());
        world.spawnEntity(lightning);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient && user.experienceLevel > 0 && user.getOffHandStack() != itemStack || !world.isClient && user.isInCreativeMode() && user.getOffHandStack() != itemStack) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.NEUTRAL, 1f, 1f);
            user.getItemCooldownManager().set(this, 600);
            user.addExperienceLevels(-1);
            itemStack.damage(1, user, EquipmentSlot.MAINHAND);
            spell(world, user);
        }

        return super.use(world, user, hand);
    }
}
