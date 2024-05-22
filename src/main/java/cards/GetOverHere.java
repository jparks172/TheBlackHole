package cards;

import Actions.MonsterPullHandler;
import Powers.GravitationalPullPower;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;


public class GetOverHere extends CustomCard
{
    public static final String ID = "blackHole:GetOverHere";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/GetOverHere.jpg";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 3;
    private static final int WEAK_AMOUNT = 1;
    private MonsterPullHandler monsterPullHandler;


    public GetOverHere() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.BLACKHOLE, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        GravitationalPullPower gpp = (GravitationalPullPower) m.getPower("blackHole:Gravitational_Pull_Power");
        if(MonsterPullHandler.inEHRange(m) || MonsterPullHandler.inGravOrbRange(m)){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            MonsterPullHandler.pullMonsterExact(m, GravitationalPullPower.gOrbTriggerLoc);

        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GetOverHere();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
        }
    }

}
