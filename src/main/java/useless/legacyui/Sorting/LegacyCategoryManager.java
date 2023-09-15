package useless.legacyui.Sorting;

import net.minecraft.core.block.*;
import net.minecraft.core.item.*;
import net.minecraft.core.item.tool.*;
import useless.legacyui.Helper.IconHelper;
import useless.legacyui.LegacyUI;
import useless.legacyui.Sorting.Recipe.RecipeCategory;
import useless.legacyui.Sorting.Recipe.RecipeGroup;
import useless.legacyui.Sorting.Recipe.RecipeGroupBuilder;

import java.util.ArrayList;
import java.util.List;

public class LegacyCategoryManager {
    public static List<RecipeCategory> recipeCategories = new ArrayList<>();
    public static String MOD_ID = LegacyUI.MOD_ID;
    public static void register(){
        LegacyCategoryManager.recipeCategories.add(recipeBasics.category);
        LegacyCategoryManager.recipeCategories.add(recipeBricks.category);
        LegacyCategoryManager.recipeCategories.add(recipeTools.category);
        LegacyCategoryManager.recipeCategories.add(recipeFood.category);
        LegacyCategoryManager.recipeCategories.add(recipeRedstone.category);
        LegacyCategoryManager.recipeCategories.add(recipeTransit.category);
        LegacyCategoryManager.recipeCategories.add(recipeMisc.category);
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "modded", IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png"), recipeMisc.category.getRecipeGroups(false)));
    }
    static {

        /*LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "basics", IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "bricks", IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "equipment", IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "food", IconHelper.getOrCreateIconTexture(MOD_ID, "health.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "redstone", IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "travel", IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "misc", IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "modded", IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png")));*/

    }
    public static class recipeBasics {
        public static RecipeGroup planks = new RecipeGroupBuilder()
                .addItem(Block.planksOak)
                .addItemsWithMetaRange(Block.planksOakPainted.asItem(), 0, 16, false)
                .build();
        public static RecipeGroup torches = new RecipeGroupBuilder()
                .addClass(BlockTorch.class)
                .addItem(Item.stick)
                .excludeItem(Block.torchRedstoneActive.asItem())
                .build();
        public static RecipeGroup utilityBlocks = new RecipeGroupBuilder()
                .addItem(Block.jukebox)
                .addItem(Block.workbench)
                .addItem(Block.furnaceBlastIdle)
                .addItem(Block.furnaceStoneIdle)
                .addItem(Block.trommelIdle)
                .build();
        public static RecipeGroup chest = new RecipeGroupBuilder()
                .addClass(BlockChest.class)
                .build();
        public static RecipeGroup bed = new RecipeGroupBuilder()
                .addItem(Item.bed)
                .build();
        public static RecipeGroup fences = new RecipeGroupBuilder()
                .addClass(BlockFence.class)
                .build();
        public static RecipeGroup fencegates = new RecipeGroupBuilder()
                .addClass(BlockFenceGate.class)
                .build();
        public static RecipeGroup woodStairs = new RecipeGroupBuilder()
                .addKeyword("stairs.planks")
                .build();
        public static RecipeGroup woodSlabs = new RecipeGroupBuilder()
                .addKeyword("slab.planks")
                .build();
        public static RecipeGroup ladders = new RecipeGroupBuilder()
                .addClass(BlockLadder.class)
                .addClass(BlockFenceChainlink.class)
                .build();
        public static RecipeGroup doors = new RecipeGroupBuilder()
                .addKeyword("door")
                .build();
        public static RecipeGroup books = new RecipeGroupBuilder()
                .addItem(Block.bookshelfPlanksOak)
                .addItem(Item.book)
                .addItem(Item.paper)
                .build();
        public static RecipeGroup display = new RecipeGroupBuilder()
                .addClass(ItemSign.class)
                .addClass(ItemPainting.class)
                .addClass(ItemFlag.class)
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "basics",
                IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png"),
                new RecipeGroup[]{planks, torches, utilityBlocks, chest, bed, fences, fencegates, woodStairs, woodSlabs, ladders, doors, books, display});
    }
    public static class recipeBricks {
        public static RecipeGroup stoneStairs = new RecipeGroupBuilder()
                .addKeyword(".stairs.")
                .build();
        public static RecipeGroup stoneSlabs = new RecipeGroupBuilder()
                .addKeyword(".slab.")
                .build();
        public static RecipeGroup bricks = new RecipeGroupBuilder()
                .addKeyword(".brick.")
                .build();
        public static RecipeGroup polished = new RecipeGroupBuilder()
                .addKeyword(".polished")
                .addKeyword(".pillar")
                .addKeyword(".carved")
                .excludeItem(Block.pumpkin.asItem())
                .excludeItem(Block.pumpkinCarvedActive.asItem())
                .excludeItem(Block.pumpkinCarvedIdle.asItem())
                .build();
        public static RecipeGroup wool = new RecipeGroupBuilder()
                .addClass(BlockWool.class)
                .build();
        public static RecipeGroup natural = new RecipeGroupBuilder()
                .addItem(Block.sandstone)
                .addItem(Block.gravel)
                .addItem(Block.blockSnow)
                .addItem(Block.blockClay)
                .addKeyword(".cobble.")
                .excludeItem(Block.dispenserCobbleStone.asItem())
                .addClass(BlockIce.class)
                .addItem(Block.permaice)
                .addClass(BlockPumpkin.class)
                .addClass(BlockSponge.class)
                .build();
        public static RecipeGroup layers = new RecipeGroupBuilder()
                .addClass(BlockLayerBase.class)
                .build();
        public static RecipeGroup resourceBlocks = new RecipeGroupBuilder()
                .addItem(Block.blockGold)
                .addItem(Block.blockIron)
                .addItem(Block.blockDiamond)
                .addItem(Block.blockLapis)
                .addItem(Block.blockSteel)
                .addItem(Block.blockRedstone)
                .addItem(Block.blockQuartz)
                .addItem(Block.blockCoal)
                .addItem(Block.blockCharcoal)
                .addItem(Block.blockNetherCoal)
                .addItem(Block.blockOlivine)
                .build();
        public static RecipeGroup resourceBlocksUncompacts = new RecipeGroupBuilder()
                .addItem(Item.ingotGold)
                .addItem(Item.ingotIron)
                .addItem(Item.diamond)
                .addItem(Item.dye, 4, true)
                .addItem(Item.ingotSteel)
                .addItem(Item.dustRedstone)
                .addItem(Item.quartz)
                .addItem(Item.coal)
                .addItem(Item.nethercoal)
                .addItem(Item.olivine)
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "bricks",
                IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png"),
                new RecipeGroup[]{bricks, polished, stoneStairs, stoneSlabs, wool, natural, layers, resourceBlocks, resourceBlocksUncompacts});
    }
    public static class recipeTools {
        public static RecipeGroup pickaxe = new RecipeGroupBuilder()
                .addClass(ItemToolPickaxe.class)
                .build();
        public static RecipeGroup shovel = new RecipeGroupBuilder()
                .addClass(ItemToolShovel.class)
                .build();
        public static RecipeGroup axe = new RecipeGroupBuilder()
                .addClass(ItemToolAxe.class)
                .build();
        public static RecipeGroup hoe = new RecipeGroupBuilder()
                .addClass(ItemToolHoe.class)
                .build();
        public static RecipeGroup sword = new RecipeGroupBuilder()
                .addClass(ItemToolSword.class)
                .build();
        public static RecipeGroup fishing = new RecipeGroupBuilder()
                .addClass(ItemFishingRod.class)
                .build();
        public static RecipeGroup bow = new RecipeGroupBuilder()
                .addClass(ItemBow.class)
                .addItem(Item.ammoArrow)
                .addItem(Item.ammoArrowGold)
                .addItem(Item.ammoArrowPurple)
                .build();
        public static RecipeGroup handcannon = new RecipeGroupBuilder()
                .addClass(ItemHandCannonLoaded.class)
                .addClass(ItemHandCannonUnloaded.class)
                .addItem(Item.ammoChargeExplosive)
                .build();
        public static RecipeGroup miscTools = new RecipeGroupBuilder()
                .addClass(ItemFirestriker.class)
                .addClass(ItemBucket.class)
                .addClass(ItemBucketEmpty.class)
                .addClass(ItemLabel.class)
                .addClass(ItemToolShears.class)
                .build();
        public static RecipeGroup toolInfo = new RecipeGroupBuilder()
                .addItem(Item.toolCompass)
                .addItem(Item.toolClock)
                .addItem(Item.toolCalendar)
                .addItem(Item.map)
                .build();
        public static RecipeGroup helmet = new RecipeGroupBuilder()
                .addKeyword(".helmet.")
                .build();
        public static RecipeGroup chestplate = new RecipeGroupBuilder()
                .addKeyword(".chestplate.")
                .addClass(ItemQuiver.class)
                .addClass(ItemQuiverEndless.class)
                .build();
        public static RecipeGroup leggings = new RecipeGroupBuilder()
                .addKeyword(".leggings.")
                .build();
        public static RecipeGroup boots = new RecipeGroupBuilder()
                .addKeyword(".boots.")
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "equipment",
                IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png"),
                new RecipeGroup[]{pickaxe,shovel,axe,hoe,sword,fishing,bow,handcannon,miscTools,toolInfo,helmet,chestplate,leggings,boots});
    }
    public static class recipeFood {
        public static RecipeGroup bread = new RecipeGroupBuilder()
                .addItem(Item.foodBread)
                .build();
        public static RecipeGroup stew = new RecipeGroupBuilder()
                .addClass(ItemSoup.class)
                .addItem(Item.bowl)
                .build();
        public static RecipeGroup cake = new RecipeGroupBuilder()
                .addItem(Item.foodCake)
                .build();
        public static RecipeGroup cookies = new RecipeGroupBuilder()
                .addItem(Item.foodCookie)
                .build();
        public static RecipeGroup icecream = new RecipeGroupBuilder()
                .addClass(ItemBucketIceCream.class)
                .build();
        public static RecipeGroup apple = new RecipeGroupBuilder()
                .addItem(Item.foodApple)
                .addItem(Item.foodAppleGold)
                .build();
        public static RecipeGroup sugar = new RecipeGroupBuilder()
                .addItem(Item.dustSugar)
                .build();
        public static RecipeGroup foodGeneral = new RecipeGroupBuilder()
                .addClass(ItemFood.class)
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "food",
                IconHelper.getOrCreateIconTexture(MOD_ID, "health.png"),
                new RecipeGroup[]{bread,stew,cake,cookies,icecream,apple,sugar, foodGeneral});
    }
    public static class recipeRedstone {
        public static RecipeGroup basicRedstone = new RecipeGroupBuilder()
                .addItem(Item.repeater)
                .addItem(Item.dustRedstone, true)
                .addItem(Block.torchRedstoneActive)
                .build();
        public static RecipeGroup redstoneBlock = new RecipeGroupBuilder()
                .addClass(BlockRedstone.class, true)
                .build();
        public static RecipeGroup buttonLever = new RecipeGroupBuilder()
                .addClass(BlockButton.class)
                .addClass(BlockLever.class)
                .build();
        public static RecipeGroup pressureplates = new RecipeGroupBuilder()
                .addClass(BlockPressurePlate.class)
                .build();
        public static RecipeGroup pistons = new RecipeGroupBuilder()
                .addClass(BlockPistonBase.class)
                .build();
        public static RecipeGroup lamps = new RecipeGroupBuilder()
                .addClass(BlockLamp.class)
                .build();
        public static RecipeGroup noteBlock = new RecipeGroupBuilder()
                .addClass(BlockNote.class)
                .build();
        public static RecipeGroup dispensers = new RecipeGroupBuilder()
                .addClass(BlockDispenser.class)
                .build();
        public static RecipeGroup traps = new RecipeGroupBuilder()
                .addClass(BlockTNT.class)
                .addClass(BlockSpikes.class)
                .addClass(BlockMesh.class)
                .build();

        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "redstone",
                IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png"),
                new RecipeGroup[]{basicRedstone, redstoneBlock, buttonLever, pressureplates,pistons,lamps,noteBlock,dispensers,traps});
    }
    public static class recipeTransit {
        public static RecipeGroup baseRail = new RecipeGroupBuilder()
                .addItem(Block.rail)
                .build();
        public static RecipeGroup detectRail = new RecipeGroupBuilder()
                .addItem(Block.railDetector)
                .build();
        public static RecipeGroup powerRail = new RecipeGroupBuilder()
                .addItem(Block.railPowered)
                .build();
        public static RecipeGroup allRail = new RecipeGroupBuilder()
                .addClass(BlockRail.class)
                .build();
        public static RecipeGroup baseCart = new RecipeGroupBuilder()
                .addItem(Item.minecart)
                .build();
        public static RecipeGroup chestCart = new RecipeGroupBuilder()
                .addItem(Item.minecartChest)
                .build();
        public static RecipeGroup furnaceCart = new RecipeGroupBuilder()
                .addItem(Item.minecartFurnace)
                .build();
        public static RecipeGroup allCart = new RecipeGroupBuilder()
                .addClass(ItemMinecart.class)
                .build();
        public static RecipeGroup boat = new RecipeGroupBuilder()
                .addClass(ItemBoat.class)
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "travel",
                IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png"),
                new RecipeGroup[]{baseRail,powerRail,detectRail,allRail,baseCart,chestCart,furnaceCart,allCart,boat});
    }
    public static class recipeMisc{
        public static RecipeGroup dyes = new RecipeGroupBuilder()
                .addClass(ItemDye.class)
                .build();
        public static RecipeGroup allBlocks = new RecipeGroupBuilder()
                .addClass(Block.class)
                .build();
        public static RecipeGroup allItems = new RecipeGroupBuilder()
                .addClass(Item.class)
                .build();
        public static RecipeGroup all = new RecipeGroupBuilder()
                .addKeyword(".")
                .build();
        public static RecipeCategory category = new RecipeCategory(
                MOD_ID,
                "misc",
                IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png"),
                new RecipeGroup[]{dyes,allBlocks,allItems,all});
    }
}
