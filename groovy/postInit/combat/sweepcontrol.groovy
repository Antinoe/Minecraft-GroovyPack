
import net.minecraftforge.event.entity.player.AttackEntityEvent;

eventManager.listen{
	AttackEntityEvent event ->{
		def entity = event.getEntityLiving();
		//	Only Sword-Sweep when Sneaking.
		if(!entity.isSneaking()){entity.onGround = false;}
		//	This makes it so that you can Sword-Sweep mid-air.
		if(entity.isSneaking()){entity.onGround = true;}
		//	This will come in handy later..
		//event.setCanceled(true);
	}
}

//Old code..
/*
import net.minecraftforge.event.entity.player.AttackEntityEvent;

eventManager.listen
{
	AttackEntityEvent event ->
	{
		def entity = event.getEntityLiving();
		//	Only Sword-Sweep when Sneaking.
		if (!entity.isSneaking())
		{
			entity.onGround = false;
		}
		//	This makes it so that you can Sword-Sweep mid-air.
		if (entity.isSneaking())
		{
			entity.onGround = true;
		}
		//	This will come in handy later..
		//event.setCanceled(true);
	}
}
*/
