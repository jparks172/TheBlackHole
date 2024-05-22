package blackHole;
import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import basemod.animations.SpriterAnimation;
import cards.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import relics.GravitationalPull;

import static blackHole.AbstractCardEnum.BLACKHOLE;


public class BlackHole extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    public static final int ORB_SLOTS = 3;

    public static final String BLACK_HOLE_SHOULDER_2 = "BlackHoleSpriter/shoulder2.png"; // campfire pose
    public static final String BLACK_HOLE_SHOULDER_1 = "BlackHoleSpriter/shoulder1.png"; // another campfire pose
    public static final String BLACK_HOLE_CORPSE = "BlackHoleSpriter/corpse.png"; // dead corpse
    public static final String BLACK_HOLE_SPRITER = "BlackHoleSpriter/BlackHole Sprite_001.scml"; // spine animation atlas
    public static final String BLACK_HOLE_SKELETON_JSON = "BlackHoleSpriter/BlackHole_001.scon"; // spine animation json


    public BlackHole(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, AbstractAnimation animation) {
        super(name, GravitronClassEnum.BLACKHOLE, null,
                null, new SpriterAnimation(BLACK_HOLE_SPRITER) );
            this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
            this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
            initializeClass(null, BLACK_HOLE_SHOULDER_2, // required call to load textures and setup energy/loadout
                    BLACK_HOLE_SHOULDER_1,
                    BLACK_HOLE_CORPSE,
                    getLoadout(), 20.0F, -50.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_Blk.ID);
        retVal.add(Strike_Blk.ID);
        retVal.add(Strike_Blk.ID);
        retVal.add(Strike_Blk.ID);
        retVal.add(Strike_Blk.ID);
        retVal.add(Defend_Blk.ID);
        retVal.add(Defend_Blk.ID);
        retVal.add(Defend_Blk.ID);
        retVal.add(MeteorStrike.ID);
        retVal.add(GravitationalStrikes.ID);
        retVal.add(GetOverHere.ID);
        retVal.add(Implosion.ID);
        retVal.add(EterniumMaw.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(GravitationalPull.ID);
        UnlockTracker.markRelicAsSeen(GravitationalPull.ID);
        return retVal;
    }


    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("Black Hole", "He's always hungry and couldn't care less what he eats",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return this.getLocalizedCharacterName();
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return BLACKHOLE;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.BLUE;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return Color.BLUE;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("STS_SFX_AncientWriting_v1", MathUtils.random(-0.2f, -0.2f));

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "STS_SFX_AncientWriting_v1";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "Black Hole";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new BlackHole("The Black Hole", GravitronClassEnum.BLACKHOLE, null, null,
                new SpriterAnimation(BLACK_HOLE_SPRITER));
    }

    @Override
    public String getSpireHeartText() {
        return null;
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.BLUE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return "Nothing really different here already drank my fair share of blood";
    }

}