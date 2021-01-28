package net.mcreator.craftinpeace;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class Configuration {

  private ForgeConfigSpec spec;

  private ConfigValue<List<? extends String>> denyList;

  public Configuration() {
    final ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

    configBuilder.push("general");

    configBuilder.comment("The list of mobs not allowed to spawn.");
    this.denyList =
      configBuilder.defineList(
        "denyList",
        new ArrayList<String>(),
        val -> val instanceof String
      );

    configBuilder.pop();
    this.spec = configBuilder.build();
  }

  public ForgeConfigSpec getSpec() {
    return this.spec;
  }

  public List<? extends String> getDenyList() {
    return this.denyList.get();
  }
}
