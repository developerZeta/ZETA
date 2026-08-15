package net.minecraft.src;

public class BlockMossylog extends Block{
    public BlockMossylog(int var1){
        super(var1,Material.wood);
    }
    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 20);
    }

   public int getBlockTextureFromSide(int side) {
        if (side==0 || side==1){
            //uses mod_Terrain.png
            return this.blockIndexInTexture+21;
        }
        return ModBlocksRenderer.getTextureIndex(4, 1);
    }

/*
  // NEW: return texture depending on side and render pass
  public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
      int pass = Block.getCurrentRenderPass();

      if (ModAtlasInjector.isInjected() || pass == 2) {
          // Mod atlas pass: only render sides here
          if (side == 0 || side == 1) {
              return 0; // dummy, won't be rendered for top/bottom in this pass
          }
          return ModBlocksRenderer.getTextureIndex(4, 1);
      } else {
          // Vanilla atlas pass: only render top/bottom here
          if (side == 0 || side == 1) {
              return this.blockIndexInTexture + 21; // vanilla terrain index for log top
          }
          return 0; // dummy, won't be rendered for sides in this pass
      }
  }

    // NEW: also used for inventory rendering (single pass)
    public int getBlockTextureFromSide(int side) {
        if (side == 0 || side == 1) {
            return this.blockIndexInTexture + 21; // show top texture in inventory
        }
        return ModBlocksRenderer.getTextureIndex(4, 1); // show side texture in inventory
    }

    // NEW: allow two render passes when mod atlas is available
    public boolean shouldRenderInPass(int pass) {
        return ModAtlasInjector.isInjected() ? (pass == 0 || pass == 2) : pass == 0;
    }

    // NEW: skip faces that belong to the other pass
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        int pass = Block.getCurrentRenderPass();
        if (ModAtlasInjector.isInjected() || pass == 2) {
            // Mod pass: only sides
            return side != 0 && side != 1;
        } else {
            // Vanilla pass: only top/bottom
            return side == 0 || side == 1;
        }
    }

*/

    public int getRenderType() {
        return 0;
    }

    public boolean renderAsNormalBlock() {
        return true;
    }
}
