package Powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReducedGravityPower extends AbstractPower {
    public static final String POWER_ID = "blackHole:Reduced_Gravity_Power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = ReducedGravityPower.POWER_STRINGS.NAME;
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    public static final String IMG = "img/ReducedGravityS.png";
    public static String [] DESCRIPTIONS = ReducedGravityPower.POWER_STRINGS.DESCRIPTIONS;
    public static int pullMultiplier = 2;

    public ReducedGravityPower(final AbstractCreature owner, final int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.amount = amount;
        this.img = new com.badlogic.gdx.graphics.Texture(IMG);
        this.owner = owner;
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription(){
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ReducedGravityPower.POWER_ID));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, ReducedGravityPower.POWER_ID, 1));
        }
    }

}
