
//	Animal Punt mod ported to Groovy.

/*
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

eventManager.listen
{
	LivingAttackEvent event ->
	{
		Entity source = event.getSource().getTrueSource();
		EntityPlayer attacker = (EntityPlayer) source;
		EntityLivingBase victim = event.getEntityLiving();
		//if (attacker.isSneaking() && victim instanceof EntityAnimal)
		if (attacker.isSneaking())
		{
			event.setCanceled(true);
			int xr = (int) -(victim.posX - attacker.posX);
			int zr = (int) -(victim.posZ - attacker.posZ);
			//When xr & zr == 0, both are on the same block, we don't know where to push
			if (xr != 0 || zr != 0)
			{
				victim.knockBack(attacker, 1.00F, xr, zr);
			}
			//TODO push randomly into nearest free spot for else
		}
	}
}
*/
