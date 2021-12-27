package eu.endercentral.crazy_advancements.messages;

import io.papermc.paper.text.PaperComponents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;

private val serializer = PaperComponents.gsonSerializer();

class ComponentProvider(val component: Component) : MessageProvider {
    override fun getBaseComponent(): IChatBaseComponent? {
        return IChatBaseComponent.ChatSerializer.a(serializer.serialize(component))
    }

}