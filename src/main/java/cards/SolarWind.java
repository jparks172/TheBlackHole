package cards;

import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.powers.ArtifactPower;


import java.util.ArrayList;

public class SolarWind extends CustomCard {

    public static final String ID = "blackHole:SolarWind";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/SolarWind.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int TURNS_STUNNED = 0;
    private static final int UPGRADE_PLUS_STUN = 1;

    public SolarWind() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.BLACKHOLE, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);

        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = TURNS_STUNNED;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(
                    p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            ArrayList<AbstractMonster> monsterList;
            monsterList = AbstractDungeon.getMonsters().monsters;
            AbstractMonster monsterBehind = null;
            float closest = Float.MAX_VALUE;
            if (monsterList.size() > 1) {
                for(AbstractMonster mTemp : monsterList ) {

                    if (mTemp.drawX - m.drawX >= 0 && mTemp.drawX != m.drawX && mTemp.drawX - m.drawX < closest){
                        closest = mTemp.drawX - m.drawX;
                        monsterBehind = mTemp;

                    }
//                    if (mTemp.drawX - m.drawX < 0 & mTemp.drawX != m.drawX){
//                        closest = mTemp.drawX - m.drawX;
//                        monsterBehind = mTemp;
//
//                    }
                }
                if (monsterBehind != null && monsterBehind.drawX != Float.MIN_VALUE ) {
                    AbstractMonster mTemp = m;
                    m = monsterBehind;
                    AbstractDungeon.actionManager.addToBottom((new DamageAction(m, new DamageInfo(p,
                            this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY)));


                    if (this.upgraded) {
                        AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(m, p, magicNumber));
                        m.setMove((byte) 4, AbstractMonster.Intent.STUN);
                        m.createIntent();
                    }
                    m = mTemp;

                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                            this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

                }
            }
        }
    }

    @Override
    public void upgrade () {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_STUN);
        }

    }



}
