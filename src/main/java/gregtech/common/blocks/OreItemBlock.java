package gregtech.common.blocks;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.properties.PropertyStoneType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class OreItemBlock extends ItemBlock {

    private final BlockOre oreBlock;
    private final Material material;
    private final PropertyStoneType stoneTypeProperty;

    public OreItemBlock(BlockOre oreBlock) {
        super(oreBlock);
        this.oreBlock = oreBlock;
        this.material = oreBlock.material;
        this.stoneTypeProperty = oreBlock.STONE_TYPE;
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    protected IBlockState getBlockState(ItemStack stack) {
        return oreBlock.getStateFromMeta(getMetadata(stack.getItemDamage()));
    }

    @NotNull
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        IBlockState blockState = getBlockState(stack);
        StoneType stoneType = blockState.getValue(stoneTypeProperty);
        return stoneType.processingPrefix.getLocalNameForItem(material);
    }
}
