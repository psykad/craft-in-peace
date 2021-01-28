package net.mcreator.craftinpeace;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CraftinpeaceModElements.ModElement.Tag
public class MonsterManager extends CraftinpeaceModElements.ModElement {

  public static final Logger log = LogManager.getLogger("MonsterManager");
  private final Configuration config = new Configuration();
  private final List<EntityType<?>> deniedEntities = new ArrayList<>();

  public MonsterManager(CraftinpeaceModElements instance) {
    super(instance, 12);
    ModLoadingContext
      .get()
      .registerConfig(ModConfig.Type.COMMON, config.getSpec());
    FMLJavaModLoadingContext
      .get()
      .getModEventBus()
      .addListener(this::onConfigLoaded);
    MinecraftForge.EVENT_BUS.addListener(this::onEntityJoinWorld);
  }

  private void onConfigLoaded(ModConfig.Loading loaded) {
    log.info("Processing entity deny list...");
    for (String entityName : config.getDenyList()) {
      log.info("Adding " + entityName + " to deny list.");
      ResourceLocation entity = ResourceLocation.tryCreate(entityName);
      deniedEntities.add(ForgeRegistries.ENTITIES.getValue(entity));
    }
  }

  private void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (deniedEntities.contains(event.getEntity().getType())) {
      event.getEntity().remove();
      event.setCanceled(true);
    }
  }
}
