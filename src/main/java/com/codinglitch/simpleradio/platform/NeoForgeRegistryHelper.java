package com.codinglitch.simpleradio.platform;

import com.codinglitch.simpleradio.platform.services.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Consumer;

public class NeoForgeRegistryHelper implements RegistryHelper {
    private static UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException("Legacy registry helper is not used by the NeoForge 1.21.1 port.");
    }

    @Override
    public <E extends Entity> EntityType<E> registerEntity(EntityType.EntityFactory<E> factory, MobCategory spawnGroup, Consumer<EntityType.Builder<E>> builder, ResourceLocation resource) {
        throw unsupported();
    }

    @Override
    public <BE extends BlockEntity> BlockEntityType<BE> registerBlockEntity(BlockEntityFactory<BE> factory, ResourceLocation resource, Block... blocks) {
        throw unsupported();
    }

    @Override
    public <M extends AbstractContainerMenu> MenuType<M> registerMenu(ResourceLocation resource, MenuSupplier<M> supplier) {
        throw unsupported();
    }

    @Override
    public CreativeModeTab registerCreativeTab(ResourceLocation resource, CreativeModeTab creativeModeTab) {
        throw unsupported();
    }
}
