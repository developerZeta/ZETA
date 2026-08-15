package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class KrollReactorRecipes {
    private static final KrollReactorRecipes instance = new KrollReactorRecipes();
    private List recipeList = new ArrayList();

    public static final KrollReactorRecipes getInstance() {
        return instance;
    }

    private KrollReactorRecipes() {
        // Add your shapeless recipe: 1 Titanium Dioxide + 1 Garnet = 1 Titanium Ingot
        this.addShapelessRecipe(
            new ItemStack(Item.ingotTitanium, 1),
            new ItemStack(Item.titaniumDust, 1),
            new ItemStack(Item.garnet, 1),
            new ItemStack(Item.netherCoal, 1)//as a fuel
        );
        this.addShapelessRecipe(
                new ItemStack(Item.ruby, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1),
                new ItemStack(Item.redstone, 1)

        );
        this.addShapelessRecipe(
                new ItemStack(Item.netherCoal, 1),
                new ItemStack(Item.coal, 1),
                new ItemStack(Item.coal, 1),
                new ItemStack(Item.coal, 1),
                new ItemStack(Item.coal, 1)
        );
        this.addShapelessRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(Item.iridiumDust, 1),
                new ItemStack(Item.iridiumDust, 1)
        );
    }

    public void addShapelessRecipe(ItemStack output, ItemStack... varargs) {
        ArrayList ingredients = new ArrayList();

        for(int i = 0; i < varargs.length; ++i) {
            ingredients.add(varargs[i].copy());
        }

        this.recipeList.add(new KrollReactorShapelessRecipe(output.copy(), ingredients));
    }

    public ItemStack findMatchingRecipe(ItemStack[] inputSlots) {
        KrollReactorShapelessRecipe recipe = this.findMatchingShapelessRecipe(inputSlots);
        return recipe == null ? null : recipe.output.copy();
    }

    public boolean consumeIngredients(ItemStack[] inputSlots) {
        KrollReactorShapelessRecipe recipe = this.findMatchingShapelessRecipe(inputSlots);
        if(recipe == null) {
            return false;
        }

        ArrayList needed = new ArrayList(recipe.ingredients);

        // Only consume from first 9 slots (input slots), not the output slot
        for(int slot = 0; slot < 9 && !needed.isEmpty(); ++slot) {
            ItemStack stack = inputSlots[slot];
            if(stack == null || stack.stackSize <= 0) {
                continue;
            }

            // Try to match this stack against needed ingredients
            for(int i = 0; i < needed.size() && stack.stackSize > 0; ++i) {
                ItemStack neededStack = (ItemStack)needed.get(i);
                if(this.isIngredientMatch(stack, neededStack)) {
                    --stack.stackSize;
                    needed.remove(i);
                    --i; // Adjust index after removal
                }
            }

            // Update the slot if empty
            if(stack.stackSize <= 0) {
                inputSlots[slot] = null;
            }
        }

        return needed.isEmpty();
    }

    private KrollReactorShapelessRecipe findMatchingShapelessRecipe(ItemStack[] inputSlots) {
        for(int i = 0; i < this.recipeList.size(); ++i) {
            KrollReactorShapelessRecipe recipe = (KrollReactorShapelessRecipe)this.recipeList.get(i);
            if(this.matches(recipe, inputSlots)) {
                return recipe;
            }
        }

        return null;
    }

    private boolean matches(KrollReactorShapelessRecipe recipe, ItemStack[] inputSlots) {
        ArrayList needed = new ArrayList(recipe.ingredients);

        // Only check first 9 slots (input slots), not the output slot
        for(int slot = 0; slot < 9; ++slot) {
            ItemStack in = inputSlots[slot];
            if(in == null) {
                continue;
            }

            int matched = this.matchCount(needed, in);
            if(matched == 0 && !this.isPotentialIngredient(in, recipe.ingredients)) {
                return false;
            }
        }

        return needed.isEmpty();
    }

    private int matchCount(ArrayList needed, ItemStack input) {
        if(input == null || input.stackSize <= 0) {
            return 0;
        }

        int matched = 0;

        for(int count = 0; count < input.stackSize; ++count) {
            int matchIndex = this.findFirstMatchIndex(needed, input);
            if(matchIndex < 0) {
                break;
            }

            needed.remove(matchIndex);
            ++matched;
        }

        return matched;
    }

    private int consumeFromStack(ArrayList needed, ItemStack stack) {
        if(stack == null || stack.stackSize <= 0) {
            return 0;
        }

        int consumed = 0;

        while(stack.stackSize > 0) {
            int matchIndex = this.findFirstMatchIndex(needed, stack);
            if(matchIndex < 0) {
                break;
            }

            needed.remove(matchIndex);
            --stack.stackSize;
            ++consumed;

            if(needed.isEmpty()) {
                break;
            }
        }

        return consumed;
    }

    private int findFirstMatchIndex(ArrayList needed, ItemStack input) {
        for(int i = 0; i < needed.size(); ++i) {
            ItemStack required = (ItemStack)needed.get(i);
            if(this.isIngredientMatch(input, required)) {
                return i;
            }
        }

        return -1;
    }

    private boolean isPotentialIngredient(ItemStack input, List ingredients) {
        for(int i = 0; i < ingredients.size(); ++i) {
            ItemStack required = (ItemStack)ingredients.get(i);
            if(this.isIngredientMatch(input, required)) {
                return true;
            }
        }

        return false;
    }

    private boolean isIngredientMatch(ItemStack input, ItemStack required) {
        if(input.itemID != required.itemID) {
            return false;
        }

        return required.getItemDamage() == -1 || input.getItemDamage() == required.getItemDamage();
    }

    private static class KrollReactorShapelessRecipe {
        private final ItemStack output;
        private final List ingredients;

        private KrollReactorShapelessRecipe(ItemStack output, List ingredients) {
            this.output = output;
            this.ingredients = ingredients;
        }
    }
}
