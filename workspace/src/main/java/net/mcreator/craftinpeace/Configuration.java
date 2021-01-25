package net.mcreator.craftinpeace;

import java.util.ArrayList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@CraftinpeaceModElements.ModElement.Tag
public class Configuration extends CraftinpeaceModElements.ModElement {

  private ForgeConfigSpec spec;

  public Configuration(CraftinpeaceModElements instance) {
    super(instance, 38);
    final ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

    configBuilder.push("general");

    configBuilder.comment("The list of mobs not allowed to spawn.");
    configBuilder.defineList(
      "denyList",
      new ArrayList<String>(),
      val -> val instanceof String
    );

    configBuilder.pop();
    this.spec = configBuilder.build();
  }

  @Override
  public void initElements() {}

  @Override
  public void init(FMLCommonSetupEvent event) {}

  @Override
  public void serverLoad(FMLServerStartingEvent event) {}

  @OnlyIn(Dist.CLIENT)
  @Override
  public void clientLoad(FMLClientSetupEvent event) {}
}