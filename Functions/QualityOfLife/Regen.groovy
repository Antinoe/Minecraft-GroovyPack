
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

eventManager.listen
{
	PlayerTickEvent event ->
	{
		//	If Health is lower than maximum, and Hunger is 12 or more.
		if (event.player.getHealth() < event.player.getMaxHealth() && event.player.getFoodStats().getFoodLevel() >= 12)
		{
			// Every half-second.
			if (event.player.getEntityWorld().getWorldTime() % 10 == 0)
			{
				if (event.player.isSneaking())
				{
					event.player.heal(0.25F);
					event.player.getFoodStats().addExhaustion(2F);
				}
				else
				{
					event.player.heal(0.125F);
					event.player.getFoodStats().addExhaustion(1F);
				}
			}
		}
	}

	/*WorldEvent.Load event ->
	{
		event.getWorld().getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
	}*/
}
