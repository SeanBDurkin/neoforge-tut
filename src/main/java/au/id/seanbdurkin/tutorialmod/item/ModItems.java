package au.id.seanbdurkin.tutorialmod.item;

import au.id.seanbdurkin.tutorialmod.TutorialMod;
import au.id.seanbdurkin.tutorialmod.item.custom.ChiselItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);
    public static final DeferredItem<Item> BISMUTH = ITEMS.registerItem("bismuth", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerItem("raw_bismuth", Item::new, new Item.Properties());
    // public static final DeferredItem<Item> CHISEL = ITEMS.registerItem("chisel", () -> new ChiselItem( new Item.Properties().durability( 32)));
    public static final DeferredItem<Item> CHISEL = ITEMS.registerItem("chisel", ChiselItem::new, new Item.Properties().durability(32));

    public static void register( IEventBus eventBus){
        ITEMS.register( eventBus);
    }
}
