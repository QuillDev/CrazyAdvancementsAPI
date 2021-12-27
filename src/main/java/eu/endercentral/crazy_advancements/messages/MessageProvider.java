package eu.endercentral.crazy_advancements.messages;

import net.minecraft.network.chat.IChatBaseComponent;

public interface MessageProvider {
    IChatBaseComponent getBaseComponent();
}