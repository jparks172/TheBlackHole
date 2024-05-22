package Orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class LightOrb extends AbstractOrb {
    public static final String ORB_ID = "blackHole:Light_Orb";
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = LightOrb.orbString.DESCRIPTION;
    private float vfxTimer;
    private float vfxIntervalMin;
    private float vfxIntervalMax;
    private static final int BASE_EVOC = 1;
    private static final int BASE_PASSIVE = 1;
    private static final float PI_DIV_16 = 0.19634955f;
    private static final float ORB_WAVY_DIST = 0.05f;
    private static final float PI_4 = 12.566371f;
    private static final float ORB_BORDER_SCALE = 1.2f;
    public LightOrb() {
        this.vfxTimer = 1.0f;
        this.vfxIntervalMin = 0.15f;
        this.vfxIntervalMax = 0.8f;
        this.ID = ORB_ID;
        this.img = new Texture("img/lightning.png");
        this.name = LightOrb.orbString.NAME;
        this.baseEvokeAmount = BASE_EVOC;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = BASE_PASSIVE;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0f);
        this.channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = DESC[0];
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new LightOrb();
    }

    public void onStartOfTurn(){
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new DexterityPower(AbstractDungeon.player, this.passiveAmount)), this.passiveAmount));
    }

    public void onEndOfTurn(){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new LoseDexterityPower(AbstractDungeon.player, this.passiveAmount)), this.passiveAmount));
    }
    @Override
    public void render(final SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale + MathUtils.sin(this.angle / 12.566371f) * 0.05f + 0.19634955f, this.scale * 1.2f, this.angle, 0, 0, 96, 96, false, false);
        sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale * 1.2f, this.scale + MathUtils.sin(this.angle / 12.566371f) * 0.05f + 0.19634955f, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, this.angle / 12.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    @Override
    public void playChannelSFX() {

    }

    public static boolean hasLightOrb(AbstractPlayer p) {
        boolean foundOrb = false;
        if (!p.orbs.isEmpty()) {
            int count = 0;
            for (AbstractOrb abstractOrb :p.orbs) {
                if (abstractOrb instanceof LightOrb) {
                    abstractOrb.onEvoke();
                    final AbstractOrb orbSlot = new EmptyOrbSlot();
                    p.orbs.set(count, orbSlot);
                    p.orbs.get(count).setSlot(count, p.maxOrbs);
                    foundOrb = true;
                    break;
                }
                count++;
            }
        }
        return foundOrb;
    }

    public static boolean lightOrbCostUpdateCheck(AbstractPlayer p){
        boolean foundOrb = false;
        if (!p.orbs.isEmpty()) {
            for (AbstractOrb abstractOrb :p.orbs) {
                if (abstractOrb instanceof LightOrb) {
                    foundOrb = true;
                    break;
                }
            }
        }
        return foundOrb;
    }
    public static AbstractOrb getLightOrb(AbstractPlayer p){
        if (!p.orbs.isEmpty()) {
            for (AbstractOrb abstractOrb :p.orbs) {
                if (abstractOrb instanceof LightOrb) {
                    return abstractOrb;
                }
            }
        }
        return null;
    }

}


