# CustomItems for Developers

The goal of this project, give developer easy tools for creation CustomItems and control them.

## Let's start use:

Import into your plugin one dependency:

```groovy
repositories {
    maven {
        name = 'CodeMC'
        url = 'https://repo.codemc.org/repository/maven-public/'
    }
}

dependencies {
    compileOnly 'org.zendal.customitems:CustomItems:1.0.3' //current version
}
```

Optional dependencies for plugin:

- [NBTAPI](https://github.com/tr7zw/Item-NBT-API) - if you want to change NBT tags into your CustomItems

Create CustomItem class:

```java
package domain.yourname.groupid.items;

@CustomItem(type = Material.DIAMON_SWORD, customModelData = 12)
public class MyFirstItem extends AbstractCustomItemStack {
    public ServiceItemStack(ItemStack itemStack) {
        super(itemStack);
    }
}
```

And inject your CustomItems (Main class your JavaPlugin):

```java
package domain.yourname.groupid;

public final class SimpleJavaPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomItems plugin = (CustomItems) this.getServer().getPluginManager().getPlugin("CustomItems");
        if (plugin == null) {
            //Handle that
            return;
        }
        //Auto find your custom items
        plugin.getCustomItemsApi().getCustomItemStackManager().scanPackagesForCustomItemStack(
                this.getClassLoader(),
                "domain.yourname.groupid.items"
        );
        //...Or register manually with default Factory
        plugin.getCustomItemsApi().getCustomItemStackManager().registerCustomItemStack(MyFirstItem.class);
        

        //Configure resource pack if you wanna
        try {
            plugin.getCustomItemsApi().setConfigurationData(
                    CustomItemsConfigurationData.builder()
                            .resourcePackUrl(new URL("https://resource.pack/1"))
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            //Handle that, when can't calculate hash of ResourcePack
        }
    }
    //etc...
}
```

### Custom events:

| Class name                                   | Description                                                                                                                                                                   |
|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| EntityDamageByPlayerWithCustomItemStackEvent | Invoked when Player Damage another entity with CustomItemStack                                                                                                                 |
| EntityPickupCustomItemEvent                  | Invoked when Entity pickup CustomItemStack                                                                                                                                     |
| PlayerClickOnCustomItemStackInInventoryEvent | Invoked when Player click on CustomItemStack in Inventory. Watch out don't use this event for handle players with creative mode, which clicking at PlayerInventory (dupe bug)! |
| PlayerDropCustomItemEvent                    | Invoked when drop CustomItemStack                                                                                                                                              |
| PlayerInteractWithCustomItemStackEvent       | Invoked when user interact something (AIR/BLOCK) CustomItemStack                                                                                                                                            |
