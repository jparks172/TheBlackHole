package Powers;

import Actions.MonsterPullHandler;
import Orbs.GravityOrb;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.clapper.util.logging.Logger;
import relics.GravitationalPull;

public class GravitationalPullPower extends AbstractPower {
    public static final String POWER_ID = "blackHole:Gravitational_Pull_Power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = GravitationalPullPower.POWER_STRINGS.NAME;
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "img/GravPullS.png";
    public static String [] DESCRIPTIONS = GravitationalPullPower.POWER_STRINGS.DESCRIPTIONS;
    public static int eHorizonTriggerLoc;
    public static int gOrbTriggerLoc;
    public static int bossPullDist = Math.round(100 * Settings.scale);
    public int initialPos;
    public int currentPos;
    public int totalDistEH;
    public int totalDistGOrb;
    public boolean isSpatOut = false;
    public int normalizedEHDist;
    public int normalizedGOrbDist;
    public int distDesc;
    public AbstractPlayer player;
    public boolean inEventHorizon = false;
    public GravitationalPullPower(final AbstractCreature owner){

        this.name = NAME;
        this.ID = POWER_ID;
        this.img = new com.badlogic.gdx.graphics.Texture(IMG);
        this.owner = owner;
        this.type = POWER_TYPE;
        this.player = AbstractDungeon.player;
        this.initialPos = Math.round(owner.drawX);
        this.currentPos = Math.round(owner.drawX);
        GravitationalPullPower.eHorizonTriggerLoc = Math.round(GravitationalPull.eventHorizon + this.player.drawX);
        this.totalDistEH = MonsterPullHandler.calculateTotalDist(GravitationalPullPower.eHorizonTriggerLoc, this.initialPos);
        this.normalizedEHDist = 100;
        GravitationalPullPower.gOrbTriggerLoc = Math.round(GravityOrb.evocRange + this.player.drawX);
        this.totalDistGOrb = MonsterPullHandler.calculateTotalDist(GravitationalPullPower.gOrbTriggerLoc, this.initialPos);
        this.normalizedGOrbDist = 100;
        this.updateDescription();
    }


    @Override
    public void onSpecificTrigger(){
        if(!owner.isDeadOrEscaped() && Math.round(owner.drawX) != this.currentPos){
            if (this.isSpatOut){
                this.initialPos = Math.round(owner.drawX);
                this.currentPos = Math.round(owner.drawX);
                this.totalDistEH = MonsterPullHandler.calculateTotalDist(GravitationalPullPower.eHorizonTriggerLoc, this.initialPos);
                this.totalDistGOrb = MonsterPullHandler.calculateTotalDist(GravitationalPullPower.gOrbTriggerLoc, this.initialPos);
                this.normalizedEHDist = 100;
                this.normalizedGOrbDist = 100;
                this.isSpatOut = false;
            }
            else{
                this.currentPos = Math.round(owner.drawX);
                if(this.currentPos <= GravitationalPullPower.eHorizonTriggerLoc) {
                    this.normalizedEHDist = 0;
                }
                else {
                    this.normalizedEHDist = MonsterPullHandler.normalizeDist(Math.abs(GravitationalPullPower.eHorizonTriggerLoc - this.currentPos), this.totalDistEH);
                }
                if(this.currentPos <= GravitationalPullPower.gOrbTriggerLoc) {
                    this.normalizedGOrbDist = 0;
                }
                else{
                    this.normalizedGOrbDist = MonsterPullHandler.normalizeDist(Math.abs(GravitationalPullPower.gOrbTriggerLoc - this.currentPos), this.totalDistGOrb);

                }
            }
            this.updateDescription();
        }
    }
    @Override
    public String getHoverMessage(){
        this.updateDescription();
        return super.getHoverMessage();
    }
    public void updateDescription(){
        //Add check for initialPos == currentPos
        String eHString = "";
        String gOrbString = "";
        if(this.normalizedEHDist <= 0){
            eHString = DESCRIPTIONS[4];
        }
        else{
            eHString = DESCRIPTIONS[0] + this.normalizedEHDist + DESCRIPTIONS[1];
        }
        if(this.normalizedGOrbDist <= 0){
            gOrbString = DESCRIPTIONS[5];
        }
        else{
            gOrbString = DESCRIPTIONS[2] + this.normalizedGOrbDist + DESCRIPTIONS[3];
        }
        this.description = eHString + gOrbString;
    }

}
