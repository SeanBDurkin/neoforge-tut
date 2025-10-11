package au.id.seanbdurkin.tutorialmod.item.custom;

import au.id.seanbdurkin.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerLevel;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map< Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.IRON_BLOCK, Blocks.STONE,
                    Blocks.NETHERRACK, ModBlocks.BISMUTH_BLOCK.get()
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn( UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Block clickedBlock = level.getBlockState( clickedPos).getBlock();
        if (CHISEL_MAP.containsKey( clickedBlock)) {
            if (!level.isClientSide()){
                Player player = context.getPlayer();
                level.setBlockAndUpdate( clickedPos, CHISEL_MAP.get( clickedBlock).defaultBlockState());
                context.getItemInHand().hurtAndBreak(
                  1,
                  ((ServerLevel) level),
                  player,
                  item -> player.onEquippedItemBroken( item, EquipmentSlot.MAINHAND));
                level.playSound( null, clickedPos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
