package com.abneyonline.platter.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSaturationSync
{
	float saturationLevel;

	public MessageSaturationSync(float saturationLevel)
	{
		this.saturationLevel = saturationLevel;
	}

	public static void encode(MessageSaturationSync pkt, PacketBuffer buf)
	{
		buf.writeFloat(pkt.saturationLevel);
	}

	public static MessageSaturationSync decode(PacketBuffer buf)
	{
		return new MessageSaturationSync(buf.readFloat());
	}

	public static void handle(final MessageSaturationSync message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() -> {
			NetworkHelper.getSidedPlayer(ctx.get()).getFoodStats().setFoodSaturationLevel(message.saturationLevel);
		});
		ctx.get().setPacketHandled(true);
	}
}