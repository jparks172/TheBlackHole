package cards;

import Actions.MonsterPullHandler;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DimensionalRift extends CustomCard {

    public static final String ID = "blackHole:Dimensional_Rift";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/DimensionalRift.jpg";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 10;

    public DimensionalRift() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.BLACKHOLE, CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;

    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m.drawX <= p.drawX + Math.round(350 * Settings.scale)){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.baseDamage + 15, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DimensionalRift();
    }
}
