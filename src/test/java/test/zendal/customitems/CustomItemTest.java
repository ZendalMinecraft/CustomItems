package test.zendal.customitems;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zendal.customitems.CustomItems;
import org.zendal.customitems.item.AbstractCustomItemStack;
import org.zendal.customitems.item.CustomItemStackFactory;
import org.zendal.customitems.item.manager.CustomItemStackManager;
import test.zendal.customitems.items.MyTestItem;
import test.zendal.customitems.items.MyTestItemWithCustomFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomItemTest {

    private ServerMock server;
    private CustomItems customItemsPlugin;
    private PlayerMock player;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        customItemsPlugin = MockBukkit.load(CustomItems.class);
        player = server.addPlayer();
    }


    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }


    @Test
    public void registerCustomItemStack() {

        customItemsPlugin.getCustomItemsApi().getCustomItemStackManager().registerCustomItemStack(MyTestItem.class);

        var item = customItemsPlugin.getCustomItemsApi().getCustomItemStackHelper().buildItem(MyTestItem.class);
        assertEquals(Material.ACACIA_BOAT, item.getType());
        assertEquals(12414, item.getItemMeta().getCustomModelData());


        assertEquals("TestItem", item.getItemMeta().getDisplayName());
        assertEquals(List.of("lore - 1", "lore - 2"), item.getItemMeta().getLore());
    }

    @Test
    public void registerCustomItemStackWithCustomFactory() {

        AtomicBoolean created = new AtomicBoolean(false);
        customItemsPlugin.getCustomItemsApi().getCustomItemStackManager().registerCustomItemStack(MyTestItemWithCustomFactory.class, new CustomItemStackFactory() {
            @Override
            public AbstractCustomItemStack build(CustomItemStackManager customItemStackManager, ItemStack itemStack) {
                created.set(true);
                return new MyTestItemWithCustomFactory(itemStack);
            }
        });

        var item = customItemsPlugin.getCustomItemsApi().getCustomItemStackHelper().buildItem(MyTestItemWithCustomFactory.class);

        assertEquals("MyTestItemWithCustomFactory", item.getItemMeta().getDisplayName());
        assertTrue(created.get());
    }
}
