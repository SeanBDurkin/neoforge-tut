package au.id.seanbdurkin.tutorialmod.block;

import au.id.seanbdurkin.tutorialmod.TutorialMod;
import au.id.seanbdurkin.tutorialmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks( TutorialMod.MOD_ID);
    public static void register(IEventBus eventBus){
        BLOCKS.register( eventBus);
    }

    // https://youtu.be/G1WaIkh_j8g?t=442
    // error: incompatible types: DeferredBlock is not a functional interface
    // WHY ???
    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock(
        "bismuth_block",
        () -> new Block( BlockBehaviour.Properties.of()
          .strength( 4f) // Normal number of hits to dig it.
          .requiresCorrectToolForDrops() // Can't use hand to dig it.
          .sound( SoundType.AMETHYST))); // Jingly sound when creating or digging it.

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
      DeferredBlock<T> toReturn = BLOCKS.register( name, block);
      registerBlockItem( name, toReturn);
      return toReturn;
    }
    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register( name, () -> new BlockItem( block.get(), new Item.Properties()));
    }
}
