package cards;

import Powers.ReducedGravityPower;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VacuumSeal extends CustomCard {
    public static final String ID = "blackHole:Vacuum_Seal";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/VacuumSeal.jpg";
    private static final int COST = 1;
    private static final int DEBUFF_AMOUNT = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_DMG = 2;
    private static final int UPGRADE_DEBUFF = 1;

    public VacuumSeal() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.BLACKHOLE, CardRarity.UNCOMMON,AbstractCard.CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.magicNumber = baseMagicNumber = DEBUFF_AMOUNT;

    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new ReducedGravityPower(m, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG);
            this.upgradeMagicNumber(UPGRADE_DEBUFF);
        }
    }
}
/* Exception in thread "LWJGL Application" com.badlogic.gdx.utils.GdxRuntimeException: java.lang.ExceptionInInitializerError
	at com.badlogic.gdx.backends.lwjgl.LwjglApplication$1.run(LwjglApplication.java:133)
Caused by: java.lang.ExceptionInInitializerError
	at cards.VacuumSeal.use(VacuumSeal.java:38)
	at com.megacrit.cardcrawl.characters.AbstractPlayer.useCard(AbstractPlayer.java:1594)
	at com.megacrit.cardcrawl.actions.GameActionManager.getNextAction(GameActionManager.java:297)
	at com.megacrit.cardcrawl.actions.GameActionManager.update(GameActionManager.java:151)
	at com.megacrit.cardcrawl.rooms.AbstractRoom.update(AbstractRoom.java:279)
	at com.megacrit.cardcrawl.dungeons.AbstractDungeon.update(AbstractDungeon.java:2552)
	at com.megacrit.cardcrawl.core.CardCrawlGame.update(CardCrawlGame.java:878)
	at com.megacrit.cardcrawl.core.CardCrawlGame.render(CardCrawlGame.java:429)
	at com.badlogic.gdx.backends.lwjgl.LwjglApplication.mainLoop(LwjglApplication.java:225)
	at com.badlogic.gdx.backends.lwjgl.LwjglApplication$1.run(LwjglApplication.java:126)
Caused by: java.lang.NullPointerException
	at Powers.ReducedGravityPower.<clinit>(ReducedGravityPower.java:15)
	... 10 more*/