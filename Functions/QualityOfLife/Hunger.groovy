
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

//Not sure why these don't allow the message to work..
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

eventManager.listen
{
	PlayerTickEvent event ->
	{
		//	If Hunger is 1 or more.
		if (event.player.getFoodStats().getFoodLevel() >= 1)
		{
			//	Every 1 tick.
			if (event.player.getEntityWorld().getWorldTime() % 1 == 0)
			{
				//	Hunger will naturally diminish over time.
				event.player.getFoodStats().addExhaustion(0.0005F);
				/*if (event.player.isSneaking())
				{
					event.player.getFoodStats().addExhaustion(0.0025F);
					//	Not sure why this doesn't work..
					//event.player.sendMessage('Sneaking..');
					//	Makes the Player space-jumpy.
					//event.player.addVelocity(0F, 0.025F, 0F);
				}*/
				if (event.player.isSprinting())
				{
					event.player.getFoodStats().addExhaustion(0.005F);
				}
			}
		}
	}
}
