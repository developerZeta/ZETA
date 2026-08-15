package net.minecraft.src;

public class TileEntityKrollReactor extends TileEntity implements IInventory {
    private ItemStack[] inventory = new ItemStack[10];
    public int cookTime = 0;
    public int cookTimeTotal = 400;

    public int getSizeInventory() {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int amount) {
        if(this.inventory[slot] != null) {
            ItemStack stack;
            if(this.inventory[slot].stackSize <= amount) {
                stack = this.inventory[slot];
                this.inventory[slot] = null;
                this.onInventoryChanged();
                return stack;
            } else {
                stack = this.inventory[slot].splitStack(amount);
                if(this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }
                this.onInventoryChanged();
                return stack;
            }
        }

        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.inventory[slot] = stack;
        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    public String getInvName() {
        return "Kroll Reactor";
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public int getCookProgressScaled(int scale) {
        return this.cookTime * scale / this.cookTimeTotal;
    }

    public boolean canInteractWith(EntityPlayer player) {
        int blockId = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
        if(blockId != Block.krollReactor.blockID && blockId != Block.krollReactorActive.blockID) {
            return false;
        }

        return player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void updateEntity() {
        boolean wasActive = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.krollReactorActive.blockID;
        boolean changed = false;
        boolean hasHeat = this.hasHeatSource();

        if(!this.worldObj.multiplayerWorld) {
            if(hasHeat && this.canProcess()) {
                ++this.cookTime;
                if(this.cookTime >= this.cookTimeTotal) {
                    this.cookTime = 0;
                    this.processItem();
                    changed = true;
                }
            } else {
                if(this.cookTime != 0) {
                    this.cookTime = 0;
                    changed = true;
                }
            }

            if(wasActive != hasHeat) {
                BlockKrollReactor.updateKrollReactorBlockState(hasHeat, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                changed = true;
            }
        }

        if(changed) {
            this.onInventoryChanged();
        }
    }

    private boolean hasHeatSource() {
        int blockBelow = this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord);
        return blockBelow == Block.fire.blockID ||
            blockBelow == Block.greenFire.blockID ||
            blockBelow == Block.lavaMoving.blockID ||
            blockBelow == Block.lavaStill.blockID;
    }

    private boolean canProcess() {
        ItemStack result = KrollReactorRecipes.getInstance().findMatchingRecipe(this.inventory);
        if(result == null) {
            return false;
        }

        ItemStack output = this.inventory[9];
        if(output == null) {
            return true;
        }

        if(!output.isItemEqual(result)) {
            return false;
        }

        if(output.stackSize >= this.getInventoryStackLimit() || output.stackSize >= output.getMaxStackSize()) {
            return false;
        }

        return output.stackSize < result.getMaxStackSize();
    }

    private void processItem() {
        if(!this.canProcess()) {
            return;
        }

        ItemStack result = KrollReactorRecipes.getInstance().findMatchingRecipe(this.inventory);
        if(result == null) {
            return;
        }

        if(this.inventory[9] == null) {
            this.inventory[9] = result.copy();
        } else if(this.inventory[9].itemID == result.itemID) {
            ++this.inventory[9].stackSize;
        }

        KrollReactorRecipes.getInstance().consumeIngredients(this.inventory);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for(int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound entry = (NBTTagCompound)list.tagAt(i);
            byte slot = entry.getByte("Slot");
            if(slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = new ItemStack(entry);
            }
        }

        this.cookTime = nbt.getShort("CookTime");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("CookTime", (short)this.cookTime);
        NBTTagList list = new NBTTagList();

        for(int i = 0; i < this.inventory.length; ++i) {
            if(this.inventory[i] != null) {
                NBTTagCompound entry = new NBTTagCompound();
                entry.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(entry);
                list.setTag(entry);
            }
        }

        nbt.setTag("Items", list);
    }
}
