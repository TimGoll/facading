package de.timgoll.facading.items;

import de.timgoll.facading.Facading;
import de.timgoll.facading.client.IHasModel;
import de.timgoll.facading.init.ModRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.client.model.ModelLoader;

public class ItemToolDemeraldHoe extends ItemHoe implements IHasModel {

    public ItemToolDemeraldHoe(String name) {
        super(ModRegistry.TM_DEMERALD);

        this.setRegistryName(name);
        this.setUnlocalizedName(Facading.MODID + "." + name);
        this.setCreativeTab(ModRegistry.TAB);

        ModRegistry.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
