package cards;

import Actions.MonsterPullHandler;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Implosion extends CustomCard {

    public static final String ID = "blackHole:Implosion";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Implosion.jpg";
    private static final int COST = 1;
    private static final int PULL = Math.round(50 * Settings.scale);
    private MonsterPullHandler monsterPullHandler;
    public Implosion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.BLACKHOLE, CardRarity.BASIC, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = PULL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            MonsterPullHandler.pullAllMonsters(AbstractDungeon.getMonsters().monsters, Implosion.PULL);
            AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1));
        }
    }
        @Override
        public void upgrade () {
            if (!this.upgraded) {
                this.upgradeName();

            }

        }
}







