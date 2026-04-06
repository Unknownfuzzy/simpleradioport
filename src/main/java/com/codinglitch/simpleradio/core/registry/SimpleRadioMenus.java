package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.core.registry.menus.RadiosmitherMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, CommonSimpleRadio.ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CommonSimpleRadio.ID);

    public static final MenuType<RadiosmitherMenu> RADIOSMITHER_MENU = registerMenu(
            "radiosmither",
            IMenuTypeExtension.create((windowId, inventory, extraData) -> new RadiosmitherMenu(windowId, inventory))
    );

    public static final CreativeModeTab RADIO_TAB = registerCreativeTab("simple_radio_tab", CreativeModeTab.builder(CreativeModeTab.Row.TOP, 7)
            .title(Component.translatable("item_group." + CommonSimpleRadio.ID))
            .icon(() -> new ItemStack(SimpleRadioItems.TRANSCEIVER.get()))
            .displayItems((params, output) -> {
                SimpleRadioItems.ITEMS.getEntries().forEach(itemHolder -> {
                    output.accept(itemHolder.get());
                });
            })
            .build()
    );

    public static void load() {}

    private static <M extends AbstractContainerMenu> MenuType<M> registerMenu(String name, MenuType<M> menuType) {
        MENUS.register(name, () -> menuType);
        return menuType;
    }

    private static CreativeModeTab registerCreativeTab(String name, CreativeModeTab tab) {
        CREATIVE_MODE_TABS.register(name, () -> tab);
        return tab;
    }
}
