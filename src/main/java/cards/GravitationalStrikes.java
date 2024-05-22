package cards;

import Actions.MonsterPullHandler;
import Powers.GravitationalStrikesPower;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GravitationalStrikes extends CustomCard {

    public static final String ID = "blackHole:Gravitational_Strikes";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/GravitationalStrikes.png";
    private static final int COST = 2;



    public GravitationalStrikes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.BLACKHOLE, CardRarity.RARE, CardTarget.SELF);

    }



    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new GravitationalStrikesPower(p, 1)));
    }

    @Override
    public void upgrade() {
    }
}
