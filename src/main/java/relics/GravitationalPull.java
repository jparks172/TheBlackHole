package relics;

import Actions.MonsterPullHandler;
import Orbs.GravityOrb;
import Powers.GravitationalPullPower;
import Powers.ReducedGravityPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import de.robojumper.ststwitch.TwitchConfig;
import org.apache.logging.log4j.LogManager;
import org.clapper.util.logging.Logger;

import java.util.ArrayList;
import java.util.Set;

public class GravitationalPull extends CustomRelic {
    public static final String ID = "blackHole:Gravitational Pull";
    private static final int DAMAGE = 3;
    private static final int STACK_AMOUNT = 3;
    private static final String IMG_PATH = "img/GravitationalPull.png";
    private static final String IMG_OUTLINE_PATH = "img/GravitationalPullOutline.png";
//    private int originalPosistion;
    public static int basePull = Math.round(40 * Settings.scale);
    public static int eventHorizon =Math.round(250 * Settings.scale);
    public static int bossMaxPull = Math.round(100 * Settings.scale);
    private MonsterPullHandler monsterPullHandler;
//    private static final ArrayList<AbstractMonster> MONSTER_LIST = new ArrayList<>();

    public GravitationalPull() {
        super(ID, new Texture(IMG_PATH), RelicTier.STARTER, LandingSound.CLINK);
    }




    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + GravitationalPull.basePull + DESCRIPTIONS[1]+ DESCRIPTIONS[2] +
                GravitationalPull.eventHorizon + DESCRIPTIONS[3];
    }

    @Override
    public void atBattleStart() {
        this.flash();
//        MONSTER_LIST.clear();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new GravityOrb()));
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
            if (!monster.isDeadOrEscaped()){
                monster.addPower(new GravitationalPullPower(monster));
            }
        }
//        addPullDebuffs();

//        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(
//                AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(
//                        AbstractDungeon.player, DAMAGE), STACK_AMOUNT));

    }
    private static void pullAllMonstersGPRelic(ArrayList<AbstractMonster> monsterList) {
        if (monsterList != null) {

            for (AbstractMonster monster : monsterList){
                GravitationalPullPower gpp = (GravitationalPullPower) monster.getPower("blackHole:Gravitational_Pull_Power");

                if (MonsterPullHandler.inEHRange(monster) && !monster.isDeadOrEscaped()) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, monster.currentHealth / 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    float max = Math.round(1000 * Settings.scale);
                    float min = Math.round(700 * Settings.scale);
                    float range = (max - min) + 1;
                    float newPos = (int) (Math.random() * range) + min;
                    if (monster.id.equals("FuzzyLouseNormal")) {
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
                    }
                    monster.drawX = Math.round(monster.drawX) + newPos;
                    gpp.isSpatOut = true;
                } else {
                    if (monster.hasPower("blackHole:Reduced_Gravity")) {
                        monster.drawX = Math.round(monster.drawX) - GravitationalPull.basePull * ReducedGravityPower.pullMultiplier;
                    } else {
                        monster.drawX = monster.drawX - GravitationalPull.basePull;
                    }

                }
                gpp.onSpecificTrigger();
            }
        }
    }
    @Override
    public void atTurnStart() {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 10000));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() && !monster.hasPower("blackHole:Gravitational_Pull_Power")) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(monster, AbstractDungeon.player, new GravitationalPullPower(monster)));
            }

        }
        if (AbstractDungeon.getMonsters().monsters != null) {
            GravitationalPull.pullAllMonstersGPRelic(AbstractDungeon.getMonsters().monsters);
        }

        super.atTurnStart();
    }

//    public void addPullDebuffs(){
//        if (!AbstractDungeon.getMonsters().monsters.isEmpty())
//            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
//                if (!monster.isDeadOrEscaped() && !monster.hasPower("blackHole:Gravitational_Pull_Power")){
//                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player,
//                                    new GravitationalPullPower(monster, monster.drawX)));
//                    GravitationalPull.MONSTER_LIST.add(monster);
//                }
//            }
//    }

    @Override
    public void onPlayerEndTurn() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() && !monster.hasPower("blackHole:Gravitational_Pull_Power")) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(monster, AbstractDungeon.player, new GravitationalPullPower(monster)));
            }
        }
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDeadOrEscaped() && !monster.hasPower("blackHole:Gravitational_Pull_Power")) {
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(monster, AbstractDungeon.player, new GravitationalPullPower(monster)));
                }
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GravitationalPull();
    }
}