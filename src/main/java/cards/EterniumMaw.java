package cards;

import Actions.MonsterPullHandler;
import Powers.GravitationalPullPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EterniumMaw extends CustomCard {
    public static final String ID = "blackHole:Eternium_Maw";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/EterniumMaw.jpeg";
    private static final int COST = 1;
    private MonsterPullHandler monsterPullHandler;



    public EterniumMaw() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CustomCard.CardType.SKILL, AbstractCardEnum.BLACKHOLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.monsterPullHandler = new MonsterPullHandler();
        this.exhaust = true;
    }



    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        MonsterPullHandler.pullAllMonstersExact(p,AbstractDungeon.getMonsters().monsters, GravitationalPullPower.eHorizonTriggerLoc);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}
