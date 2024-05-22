package cards;


import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Blk extends CustomCard
{
    public static final String ID = "blackHole:Strike_Blk";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Strike_Blk.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;

    public Strike_Blk() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.BLACKHOLE, AbstractCard.CardRarity.BASIC,AbstractCard.CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (Settings.isDebug) {
            if (Settings.isInfo) {
                this.multiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                    this.multiDamage[i] = 150;
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 150, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
//            if(LightOrb.hasLightOrb(p)) {
//                if (m.drawX < p.drawX +600) {
//                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + 8, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
//                    m.drawX -= 100;
//                }
//                else{
//                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + 3, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
//                    m.drawX -= 100;
//                }
//            }
//            else if (m.drawX < p.drawX +600) {
//                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + 8, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
//                m.drawX -= 100;
//            }
//            else{
//                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + 3, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
//                m.drawX -= 100;
//            }

//            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GravityOrb()));

        }
    }

//    @Override
//    public void hover() {
//        if (LightOrb.lightOrbCostUpdateCheck(AbstractDungeon.player)){
//            if (COST > 0) {
//                this.costForTurn = COST - LightOrb.getLightOrb(AbstractDungeon.player).evokeAmount;
//                this.isCostModifiedForTurn = true;
//            }
//        }
//        else{
//            this.costForTurn = COST;
//            this.isCostModifiedForTurn = false;
//        }
//        super.hover();
//    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_Blk();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }


}
