package Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class GravityOrbPassiveAction extends AbstractGameAction {
    private DamageInfo damageInfo;
    private AbstractOrb abstractOrb;

    public GravityOrbPassiveAction(final DamageInfo damageInfo, final AbstractOrb abstractOrb){
        this.damageInfo = damageInfo;
        this.abstractOrb = abstractOrb;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.BLUNT_HEAVY;
    }
    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damageInfo.base, true, true), DamageInfo.DamageType.THORNS, attackEffect));
        this.isDone = true;
    }
}
