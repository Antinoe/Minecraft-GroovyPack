
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

eventManager.listen{
	LivingHurtEvent event ->{
		World world = event.getEntityLiving().world;
		DamageSource damageSource = event.getSource();
		def defender = (EntityLivingBase)event.getEntityLiving();
		EntityLivingBase attacker = (EntityLivingBase)damageSource.getTrueSource();
		//world.playSound(null, defender.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 1.00F, 2.00F);
		float health = defender.getHealth();
		float maxHealth = defender.getMaxHealth();
		float absorption = defender.getAbsorptionAmount();
		armor = defender.getTotalArmorValue();
		armorToughness = defender.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue();
		int age = defender.getIdleTime();
		float damage = event.getAmount();
		float damageBlockable = (damage - armor) * 1f;
		float damageUnblockable = (damage - armor) * 1.75f;

		//Here's how I want this to work: For every point of Armor, 0.5 Damage is reduced.
		//Formulas:
		//4 - 2 * 1 = 2
		//4 - 2 * 1.25 = 2.5
		//4 - 2 * 1.5 = 3
		//4 - 2 * 1.75 = 3.5
		//4 - 2 * 2 = 4
		//6 - 6 * 1 = 0
		//6 - 6 * 1.25 = 0
		//6 - 6 * 1.5 = 0
		//6 - 6 * 1.75 = 0
		//6 - 6 * 2 = 0
		//19 - 8 * 1 = 11
		//19 - 8 * 1.25 = 13.75
		//19 - 8 * 1.5 = 16.5
		//19 - 8 * 1.75 = 19.25
		//19 - 8 * 2 = 22

		if (armor > 0){
			if (!damageSource.isUnblockable()){event.setAmount(damageBlockable);}
			else{
				//event.setAmount(damageUnblockable);
			}
			//world.playSound(null, defender.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 1.00F, 2.00F);
			event.setCanceled(false);
		}
	}
}
