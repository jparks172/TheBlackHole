package Powers;

import Actions.MonsterPullHandler;
import basemod.helpers.BaseModCardTags;
import cards.GravitationalStrikes;
import cards.MeteorStrike;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GravitationalStrikesPower extends AbstractPower {
    public static final String POWER_ID = "blackHole:Gravitational_Strikes_Power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = GravitationalStrikesPower.POWER_STRINGS.NAME;
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "img/GravitationalStrikesPowerS.png";
    public static String [] DESCRIPTIONS = GravitationalStrikesPower.POWER_STRINGS.DESCRIPTIONS;
    public static int pullDist = Math.round(50 * Settings.scale);
    public MonsterPullHandler monsterPullHandler;

    public GravitationalStrikesPower(final AbstractCreature owner, final int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.amount = amount;
        this.img = new com.badlogic.gdx.graphics.Texture(IMG);
        this.owner = owner;
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription(){
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(card.tags.contains(BaseModCardTags.BASIC_STRIKE)){

            if(m != null &&!m.isDeadOrEscaped()) {
                MonsterPullHandler.pullMonster(m, GravitationalStrikesPower.pullDist);
            }
        }
        if(card.cardID.equals(MeteorStrike.ID)){
            if(AbstractDungeon.getMonsters().monsters != null) {
                MonsterPullHandler.pullAllMonsters(AbstractDungeon.getMonsters().monsters, pullDist);
            }
        }
        super.onPlayCard(card, m);
    }
}
