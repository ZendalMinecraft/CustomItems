# CustomItems for Developers
The goal of this project, give developer easy tools for creation CustomItems and control them.

## Let's start use:
Import into your plugin one dependency:
```groovy
    compile 'org.zendal:customitems:1.0'
```

Create CustomItem class:
```java
package domain.yourname.groupid.items;

@CustomItem(type = Material.DIAMON_SWORD, customData = 12)
public class MyFirstItem extends AbstractCustomItemStack{
    public ServiceItemStack(ItemStack itemStack) {
        super(itemStack);
    }
}
```