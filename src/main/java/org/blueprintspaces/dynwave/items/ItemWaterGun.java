package org.blueprintspaces.dynwave.items;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.blueprintspaces.dynwave.entity.BulletProjectile;
import org.blueprintspaces.dynwave.init.EntityInit;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class ItemWaterGun extends Item {
    private int ammoCapacity;
    private int currentAmmo;
    private final ItemBullets bullet;

    // Custom key binding for reloading
    private static final KeyBinding RELOAD_KEYBIND = new KeyBinding(
            "key.dynwave.reload",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.dynwave.items"
    );

    public ItemWaterGun(Settings settings, int ammoCapacity, ItemBullets bullet) {
        super(settings);
        this.ammoCapacity = ammoCapacity;
        this.currentAmmo = 0;
        this.bullet = bullet;

        // Register the reload key binding
        KeyBindingHelper.registerKeyBinding(RELOAD_KEYBIND);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (RELOAD_KEYBIND.wasPressed()) {
                PlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null && player.getMainHandStack().getItem() instanceof ItemWaterGun) {
                    reload(player);
                }
            }
        });
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack gunStack = user.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && currentAmmo > 0) {
            shootWater(world, user);
            currentAmmo--;
            return TypedActionResult.success(gunStack);
        } else if (currentAmmo == 0) {
            user.sendMessage(Text.of("Out of ammo! Press 'R' to reload."), true);
            return TypedActionResult.fail(gunStack);
        }
        return TypedActionResult.pass(gunStack);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (currentAmmo > 0) {
            shootWater(player.getWorld(), player);
            currentAmmo--;
            return true;
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    private void shootWater(World world, PlayerEntity user) {

            if (!world.isClient) {
                Vec3d lookVec = user.getRotationVec(1.0F);
                double posX = user.getX() + lookVec.x;
                double posY = user.getEyeY() - 0.1; 
                double posZ = user.getZ() + lookVec.z;

                BulletProjectile projectile = new BulletProjectile(EntityInit.MAGIC_PROJECTILE, world);
                projectile.setPosition(posX, posY, posZ);
                projectile.setVelocity(lookVec.x, lookVec.y, lookVec.z, 1.5F, 1.0F);
                world.spawnEntity(projectile);

                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }


    }

    public int reload(PlayerEntity user) {
        int bulletsLoaded = 0;
        if (currentAmmo < ammoCapacity) {
            // Simulate reloading from bullet items in player's inventory
            int bulletsInInventory = getBulletsInInventory(user);
            if (bulletsInInventory > 0) {
                int bulletsToLoad = Math.min(ammoCapacity - currentAmmo, bulletsInInventory);
                bulletsLoaded = bulletsToLoad; // Update bulletsLoaded with the number of bullets loaded
                currentAmmo += bulletsToLoad;
                // Remove bullets from inventory
                consumeBullets(user, bulletsToLoad);
                user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
        return bulletsLoaded; // Return the number of bullets loaded
    }

    private int getBulletsInInventory(PlayerEntity player) {
        int count = 0;
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == bullet) {
                count += stack.getCount();
            }
        }
        return count;
    }

    private void consumeBullets(PlayerEntity player, int amount) {
        for (int i = 0; i < player.getInventory().main.size() && amount > 0; i++) {
            ItemStack stack = player.getInventory().main.get(i);
            if (stack.getItem() == bullet) {
                int toRemove = Math.min(amount, stack.getCount());
                stack.decrement(toRemove);
                amount -= toRemove;
            }
        }
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        // Handle continuous usage effects here if needed
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Handle custom behavior when the item is in the player's inventory
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
