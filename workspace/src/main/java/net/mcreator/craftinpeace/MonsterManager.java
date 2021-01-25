package net.mcreator.craftinpeace;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CraftinpeaceModElements.ModElement.Tag
public class MonsterManager extends CraftinpeaceModElements.ModElement {

  public static final Logger log = LogManager.getLogger("MonsterManager");
  private List<EntityType<?>> deniedEntities = new ArrayList<>();

  public MonsterManager(CraftinpeaceModElements instance) {
    super(instance, 12);
    String[] entityNames = {
      "blaze",
      "creeper",
      "drowned",
      "elder_guardian",
      "enderman",
      "endermite",
      "evoker",
      "ghast",
      "guardian",
      "hoglin",
      "husk",
      "magma_cube",
      "phantom",
      "piglin_brute",
      "pillager",
      "ravager",
      "shulker",
      "shulker_bullet",
      "silverfish",
      "skeleton",
      "slime",
      "spider",
      "stray",
      "vex",
      "vindicator",
      "witch",
      "wither",
      "wither_skeleton",
      "zoglin",
      "zombie",
      "zombie_villager",
      "zombie_pigmen",
    };

    for (String entityName : entityNames) {
      ResourceLocation entity = ResourceLocation.tryCreate(entityName);
      deniedEntities.add(ForgeRegistries.ENTITIES.getValue(entity));
    }

    MinecraftForge.EVENT_BUS.addListener(this::onEntityJoinWorld);
  }

  private void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (deniedEntities.contains(event.getEntity().getType())) {
      event.getEntity().remove();
      event.setCanceled(true);
    }
  }
}
