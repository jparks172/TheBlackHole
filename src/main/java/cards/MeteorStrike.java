package cards;

import Actions.MonsterPullHandler;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MeteorStrike extends CustomCard {

    public static final String ID = "blackHole:Meteor_Strike";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/MeteorStrike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int BONUS_DMG = 8;
    private static final int UPGRADE_BONUS_DMG = 4;
    private static final int UPGRADE_DMG = 2;


    public MeteorStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.BLACKHOLE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = baseMagicNumber = BONUS_DMG;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(MonsterPullHandler.inGravOrbRange(monster) && !monster.isDeadOrEscaped()){
                for(int i = 0; i < 2; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                }
            }
            else if(!monster.isDeadOrEscaped()){
                for(int i = 0; i < 2; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG);
            this.upgradeMagicNumber(UPGRADE_BONUS_DMG);
        }
    }
}
