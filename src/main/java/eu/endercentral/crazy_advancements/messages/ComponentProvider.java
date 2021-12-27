package eu.endercentral.crazy_advancements.messages;

import io.papermc.paper.text.PaperComponents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;


public class ComponentProvider implements MessageProvider {

    private static final GsonComponentSerializer serializer = PaperComponents.gsonSerializer();

    private final Component component;

    public ComponentProvider(Component component) {
        this.component = component;
    }

    @Override
    public IChatBaseComponent getBaseComponent() {
        return IChatBaseComponent.ChatSerializer.a(serializer.serialize(component));
    }
}