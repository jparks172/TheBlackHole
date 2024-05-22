package cards;

import Orbs.GravityOrb;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HarnessGravity extends CustomCard
{
    public static final String ID = "blackHole:HarnessGravity";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID) ;
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/HarnessGravity.png";
    private static final int COST = 1;
    private static final int DEFENSE_GAINED = 5;

    public HarnessGravity() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.BLACKHOLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = DEFENSE_GAINED;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GravityOrb()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HarnessGravity();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }


}
