package cards;

import Orbs.GravityOrb;
import basemod.abstracts.CustomCard;
import blackHole.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChannelGravity extends CustomCard {

        public static final String ID = "blackHole:ChannelGravity";
        private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        public static final String NAME = cardStrings.NAME;
        public static final String DESCRIPTION = cardStrings.DESCRIPTION;
        private static final String IMG_PATH = "img/ChannelGravity.jpg";
        private static final int COST = 1;
        private static final int UPGRADE_COST = 0;
        public ChannelGravity() {
            super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL,
                    AbstractCardEnum.BLACKHOLE, AbstractCard.CardRarity.COMMON, CardTarget.NONE);

        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
           AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GravityOrb()));
        }

        @Override
        public void upgrade () {
            if (!this.upgraded) {
                this.upgradeName();
                this.upgradeBaseCost(UPGRADE_COST);
            }
        }
}

