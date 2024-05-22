package cards;

import Orbs.GravityOrb;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import blackHole.AbstractCardEnum;
import blackHole.GravitronClassEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.Collections;

public class Defend_Blk extends CustomCard {
    public static final String ID = "blackHole:Defend_Blk";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Defend_Blk.jpg";
    private static final int COST = 1;
    private static final int BLOCK_INIT_GAINED = 5;


    public Defend_Blk() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CustomCard.CardType.SKILL, AbstractCardEnum.BLACKHOLE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = BLOCK_INIT_GAINED;
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
    }

    public void onEndOfTurn(){

    }
    @Override
    public AbstractCard makeCopy() {
            return new Defend_Blk();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

}
