package eu.endercentral.crazy_advancements.messages;

import net.minecraft.network.chat.IChatBaseComponent;

interface MessageProvider {
    fun getBaseComponent(): IChatBaseComponent?
}