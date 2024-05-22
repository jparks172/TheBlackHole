import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.animations.SpriterAnimation;
import basemod.interfaces.*;
import blackHole.AbstractCardEnum;
import blackHole.BlackHole;
import blackHole.GravitronClassEnum;
import cards.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import relics.GravitationalPull;

import java.nio.charset.StandardCharsets;

import static blackHole.AbstractCardEnum.BLACKHOLE;

@SpireInitializer
public class TheBlackHole implements basemod.interfaces.EditCharactersSubscriber, EditStringsSubscriber, basemod.interfaces.EditCardsSubscriber,basemod.interfaces.EditRelicsSubscriber,PostInitializeSubscriber{
    public static final float TBD_LABEL_X = 350.0f;
    public static final float TBD_LABEL_Y = 750.0f;
    public static final String MODNAME = "test";
    public static final String AUTHOR = "jp";
    public static final String DESCRIPTION = "v1.0.0\n  test";
    public static BlackHole blackHole;
    private static final com.badlogic.gdx.graphics.Color BLACK_HOLE_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(0f, 0f, 0f);

    public TheBlackHole(){

        BaseMod.addColor(BLACKHOLE, BLACK_HOLE_COLOR,
                BLACK_HOLE_COLOR, BLACK_HOLE_COLOR,
                BLACK_HOLE_COLOR,  BLACK_HOLE_COLOR,  BLACK_HOLE_COLOR,
                BLACK_HOLE_COLOR, "img/bg_img/bg_attack_blackhole.png", "img/bg_img/bg_skill_blackhole.png", "img/bg_img/bg_power_blackhole.png",
                "img/bg_img/card_blackhole_orb.png", "img/Insp_bg_img/bg_attack_blackhole.png", "img/Insp_bg_img/bg_skill_blackhole.png",
                "img/Insp_bg_img/bg_power_blackhole.png", "img/Insp_bg_img/card_blackhole_orb.png", "img/bg_img/card_small_orb.png");

        BaseMod.subscribe(this);
    }
    @Override
    public void receiveEditCharacters() {
        blackHole = new BlackHole("TheBlackHole", GravitronClassEnum.BLACKHOLE, null,null, new SpriterAnimation("BlackHoleSpriter/BlackHole Sprite_001.scml"));
        BaseMod.addCharacter(blackHole, "img/Button.png",
                "BlackHoleSpriter/Black Hole Sticker.png", GravitronClassEnum.BLACKHOLE);

    }

    public void receivePostInitialize(){
        Texture badgeTexture = new Texture("BlackHoleSpriter/NebStick.png");
        ModPanel settingsPanel = new ModPanel();
        ModLabel TBD_Label = new ModLabel("test",
                TBD_LABEL_X,
                TBD_LABEL_Y,
                settingsPanel, me -> {
        });

        settingsPanel.addUIElement(TBD_Label);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;

    }

    public static void initialize(){
        TheBlackHole theBlackHole = new TheBlackHole();
    }

    @Override
    public void receiveEditStrings() {
        String cardStrings = Gdx.files.internal("localizations/cardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String relicStrings = Gdx.files.internal("localizations/relicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String orbStrings = Gdx.files.internal("localizations/orbStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String powerStrings = Gdx.files.internal("localizations/powerStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new cards.Strike_Blk());
        BaseMod.addCard(new cards.Defend_Blk());
        BaseMod.addCard(new GetOverHere());
        BaseMod.addCard(new Implosion());

        UnlockTracker.unlockCard(Defend_Blk.ID);
        UnlockTracker.unlockCard(Strike_Blk.ID);
        UnlockTracker.unlockCard(GetOverHere.ID);
        UnlockTracker.unlockCard(Implosion.ID);


        BaseMod.addCard(new HarnessGravity());
        BaseMod.addCard(new ChannelGravity());
        BaseMod.addCard(new DimensionalRift());


        UnlockTracker.unlockCard(HarnessGravity.ID);
        UnlockTracker.unlockCard(ChannelGravity.ID);
        UnlockTracker.unlockCard(DimensionalRift.ID);

        BaseMod.addCard(new MeteorStrike());
        BaseMod.addCard(new VacuumSeal());

        UnlockTracker.unlockCard(MeteorStrike.ID);
        UnlockTracker.unlockCard(VacuumSeal.ID);

        BaseMod.addCard(new SolarWind());
        BaseMod.addCard(new EterniumMaw());

        UnlockTracker.unlockCard(SolarWind.ID);
        UnlockTracker.unlockCard(EterniumMaw.ID);

        BaseMod.addCard(new GravitationalStrikes());

        UnlockTracker.unlockCard(GravitationalStrikes.ID);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new GravitationalPull(), AbstractCardEnum.BLACKHOLE);
    }
}
