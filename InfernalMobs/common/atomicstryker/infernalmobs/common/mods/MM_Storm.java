package atomicstryker.infernalmobs.common.mods;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import atomicstryker.infernalmobs.common.MobModifier;

public class MM_Storm extends MobModifier
{
    public MM_Storm(EntityLiving mob)
    {
        this.mob = mob;
        this.modName = "Storm";
    }
    
    public MM_Storm(EntityLiving mob, MobModifier prevMod)
    {
        this.mob = mob;
        this.modName = "Storm";
        this.nextMod = prevMod;
    }
    
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private final static float MIN_DISTANCE = 3F;
    
    @Override
    public boolean onUpdate()
    {
        if (getMobTarget() != null
        && getMobTarget() instanceof EntityPlayer)
        {
            tryAbility(getMobTarget());
        }
        
        return super.onUpdate();
    }

    private void tryAbility(EntityLiving target)
    {        
        long time = System.currentTimeMillis();
        if (time > nextAbilityUse
        && mob.getDistanceToEntity(target) > MIN_DISTANCE)
        {
            nextAbilityUse = time+coolDown;
            mob.worldObj.addWeatherEffect(new EntityLightningBolt(mob.worldObj, target.posX, target.posY-1, target.posZ));
        }
    }
}
