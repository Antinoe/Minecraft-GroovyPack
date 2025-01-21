
//	import net.minecraft.sounds.SoundEvents;
//	import net.minecraft.init.SoundCategory;
//	^ These are incorrect.

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumHand;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemShield;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

//	We use ``LivingHurtEvent`` here because Damage cannot be modified in ``LivingAttackEvent``.
eventManager.listen{
	LivingHurtEvent event ->{
		/*
		if(event.getEntityLiving().world.isRemote || event.isCanceled() || !(event.getEntityLiving() instanceof EntityPlayer) || !(event.getSource().getTrueSource() instanceof EntityLivingBase) || event.getAmount() <= 0.0F) return;
		if(!(event.getEntityLiving() instanceof EntityPlayer)) return;
		*/
		World world = event.getEntityLiving().world;
		DamageSource damageSource = event.getSource();
		//	The reason this can't be used is because the hurt entity is cast to Players, but you can't assume the hurt entity is always a Player.
		//def defenderPlayer = (EntityPlayer)event.getEntityLiving();
		def defender = (EntityLivingBase)event.getEntityLiving();
		def attacker = (EntityLivingBase)damageSource.getTrueSource();
		float damage = event.getAmount();
		float damageQuarter = (float)(damage * 0.25f);
		float damageHalf = (float)(damage * 0.5f);
		float damageBlockable = damageHalf;
		float damageUnblockable = damageHalf;
		if(defender.isSneaking()){
			if(!damageSource.isUnblockable()){
				event.setAmount(damageBlockable);
				//defender.getFoodStats().addExhaustion(1F);
				//attacker.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5 * 20, 0));
			}
			else{
				event.setAmount(damageUnblockable);
				//defender.getFoodStats().addExhaustion(2F);
			}
			world.playSound(null, defender.getPosition(), SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.PLAYERS, 1.00F, 0.25F);
			event.setCanceled(false);
		}
	}
}

/*
eventManager.listen{
	//	You cannot deal damage while Sneaking.
	LivingAttackEvent event ->{
		Entity source = event.getSource().getTrueSource();
		EntityPlayer attacker = (EntityPlayer) source;
		if(attacker.isSneaking()){event.setCanceled(true);}
	}
}
*/

//	Tool Guarding
//	We use ``LivingAttackEvent`` here because it's called right before Damage is received.
/*
eventManager.listen{
	LivingAttackEvent event ->{
		World world = event.getEntityLiving().world;
		DamageSource damageSource = event.getSource();
		EntityPlayer defender = event.getEntityLiving();
		EntityLivingBase attacker = (EntityLivingBase)damageSource.getTrueSource();
		float damageGuarding = event.getAmount();
		int damageGuardingInteger = (int) damageGuarding;
		int parryTime = 10;
		int parryCooldown = 20;
		//ItemStack defenderItem = defender.isHandActive() ? defender.getActiveItemStack() : ItemStack.EMPTY;
		ItemStack defenderItem = defender.getHeldItem(defender.getActiveHand());
		ItemStack attackerItem = attacker.getHeldItem(attacker.getActiveHand());
		boolean defenderHasParry = false;
		boolean defenderHasSword = false;
		boolean defenderHasAxe = false;
		boolean defenderHasPickaxe = false;
		boolean defenderHasShovel = false;
		boolean defenderHasHoe = false;
		if(!defenderItem.isEmpty() && defenderItem.getItem() instanceof ItemSword){defenderHasSword = true;}
		if(!defenderItem.isEmpty() && defenderItem.getItem() instanceof ItemAxe){defenderHasAxe = true;}
		if(defender.isSneaking()){
			if(defenderHasSword || defenderHasAxe){
				defenderItem.damageItem(damageGuardingInteger, defender);
				world.playSound(null, defender.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0F, 2.00F);
				event.setCanceled(true);
			}
		}
	}
}
*/

//	Old code..
/*
import net.minecraftforge.event.entity.living.LivingHurtEvent;

eventManager.listen{
	LivingHurtEvent event ->{
		def entity = event.getEntityLiving();
		float damage = event.getAmount() * 0.25f;
		if(entity.isSneaking()){event.setAmount(damage);}
	}
}
*/
