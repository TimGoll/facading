package de.timgoll.facading.items;

import de.timgoll.facading.Facading;
import de.timgoll.facading.client.IHasModel;
import de.timgoll.facading.init.ModRegistry;
import de.timgoll.facading.titleentities.TileBlockFacade;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import org.lwjgl.input.Keyboard;

import static de.timgoll.facading.init.ModRegistry.BLOCK_FACADE;

public class ItemHammer extends Item implements IHasModel {

    public ItemHammer(String name) {
        setRegistryName(name);
        setUnlocalizedName(Facading.MODID + "." + name);
        setCreativeTab(ModRegistry.TAB);
        ModRegistry.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        //if (world.isRemote)
        //    player.sendMessage(new TextComponentString("You have right clicked with a " + this.getRegistryName()));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            if (world.getBlockState(pos).getBlock() == BLOCK_FACADE) {
                boolean isShiftPressed = (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT));
                TileBlockFacade tileBlockFacade = (TileBlockFacade) world.getTileEntity(pos);

                player.sendMessage(new TextComponentString( pos.toString() ));

                if (isShiftPressed) {
                    player.sendMessage(new TextComponentString("prev isSlab: " + tileBlockFacade.isSlab));
                    tileBlockFacade.isSlab = false;
                    player.sendMessage(new TextComponentString("Changing Blocktype reversed, now isSlab: " + tileBlockFacade.isSlab));
                } else {
                    player.sendMessage(new TextComponentString("prev isSlab: " + tileBlockFacade.isSlab));
                    tileBlockFacade.isSlab = true;
                    player.sendMessage(new TextComponentString("Changing Blocktype, now isSlab: " + tileBlockFacade.isSlab));

                }
            }
        }
        return EnumActionResult.SUCCESS;
    }

}