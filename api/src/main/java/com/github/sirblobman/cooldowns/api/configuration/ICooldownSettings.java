package com.github.sirblobman.cooldowns.api.configuration;

import java.util.List;
import java.util.Optional;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;

import com.github.sirblobman.api.xseries.XMaterial;
import com.github.sirblobman.api.xseries.XPotion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for CooldownsX configuration settings.
 *
 * @author SirBlobman
 */
public interface ICooldownSettings {
    /**
     * @return The id in the configuration.
     */
    @NotNull String getId();

    /**
     * @return The amount of actions required to trigger this cooldown.
     */
    int getAmount();

    /**
     * @return The amount of seconds the cooldown will last.
     */
    int getCooldownSeconds();

    /**
     * @return The type of action that will trigger this cooldown.
     */
    @NotNull CooldownType getCooldownType();

    /**
     * @return The optional list of materials for this cooldown.
     */
    @NotNull Optional<List<XMaterial>> getMaterialList();

    /**
     * @return The optional list of potion effects for this cooldown.
     */
    @NotNull Optional<List<XPotion>> getPotionList();

    /**
     * @return The name of the bypass permission for this cooldown.
     */
    @Nullable String getBypassPermissionName();

    /**
     * @return The cached permission object for this cooldown.
     */
    @Nullable Permission getBypassPermission();

    /**
     * @return {@code true} if a packet cooldown should be sent for materials, otherwise {@code false}.
     */
    boolean isUsePacketCooldown();

    /**
     * @return The mode for CombatLogX settings for this cooldown.
     */
    @NotNull CombatMode getCombatMode();

    /**
     * @return The amount of seconds to use instead of original seconds. Based on the combat mode.
     * @see #getCooldownSeconds()
     * @see #getCooldownSeconds(Player)
     * @see #getCombatMode()
     */
    int getCombatCooldownSeconds();

    /**
     * @return A list of world names in which this cooldown will not be triggered.
     */
    @NotNull List<String> getDisabledWorldList();

    /**
     * @return {@code true} if the disabled world list is actually an enabled worlds list.
     * @see #getDisabledWorldList()
     */
    boolean isInvertWorldList();

    /**
     * @return The action bar settings for this cooldown.
     */
    @NotNull IActionBarSettings getActionBarSettings();

    /**
     * @return The message format for the cooldown message.
     */
    @Nullable String getMessageFormat();

    /**
     * @return {@code true} if the amount should be reset once a cooldown is triggered.
     * @see #getAmount()
     */
    boolean isResetAmount();

    /**
     * @param player The player that will receive the cooldown.
     * @return The amount of seconds to apply this cooldown. Also checks player combat status if CombatLogX exists.
     * @see #getCombatMode()
     * @see #getCombatCooldownSeconds()
     * @see #getCooldownSeconds()
     */
    int getCooldownSeconds(Player player);

    /**
     * @param material The XMaterial value.
     * @return {@code true} if the material list exists and contains the value.
     */
    boolean hasMaterial(XMaterial material);

    /**
     * @param potion The XPotion value.
     * @return {@code true} if the potion list exists and contains the value.
     */
    boolean hasPotion(XPotion potion);

    /**
     * @param world The world object to check.
     * @return {@code true} if the cooldown should not trigger in the specified world.
     */
    boolean isDisabled(World world);

    /**
     * @param permissible The object that may or may not have the bypass permission. Usually a {@link Player}.
     * @return {@code true} if the specified permissible has the bypass permission.
     * @see #getBypassPermissionName()
     * @see #getBypassPermission()
     */
    boolean canBypass(Permissible permissible);
}
