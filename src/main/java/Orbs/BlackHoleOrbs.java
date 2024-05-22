//package Orbs;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Interpolation;
//import com.badlogic.gdx.math.MathUtils;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.*;
//import com.megacrit.cardcrawl.orbs.*;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.vfx.BobEffect;
//
//import java.util.ArrayList;
//
//public abstract class BlackHoleOrbs extends AbstractOrb{
//    public String name;
//    public String description;
//    public String ID;
//    protected ArrayList<PowerTip> tips;
//    public int evokeAmount;
//    public int passiveAmount;
//    protected int baseEvokeAmount;
//    protected int basePassiveAmount;
//    public float cX;
//    public float cY;
//    public float tX;
//    public float tY;
//    protected Color c;
//    protected static final int W = 96;
//    public Hitbox hb;
//    protected Texture img;
//    protected BobEffect bobEffect;
//    protected static final float NUM_X_OFFSET;
//    protected static final float NUM_Y_OFFSET;
//    protected float angle;
//    protected float scale;
//    protected float fontScale;
//    protected boolean showEvokeValue;
//    protected static final float CHANNEL_IN_TIME = 0.5f;
//    protected float channelAnimTimer;
//
//    public BlackHoleOrbs() {
//        this.tips = new ArrayList<PowerTip>();
//        this.evokeAmount = 0;
//        this.passiveAmount = 0;
//        this.baseEvokeAmount = 0;
//        this.basePassiveAmount = 0;
//        this.cX = 0.0f;
//        this.cY = 0.0f;
//        this.c = Settings.CREAM_COLOR.cpy();
//        this.hb = new Hitbox(96.0f * Settings.scale, 96.0f * Settings.scale);
//        this.img = null;
//        this.bobEffect = new BobEffect(3.0f * Settings.scale, 3.0f);
//        this.fontScale = 0.7f;
//        this.showEvokeValue = false;
//        this.channelAnimTimer = 0.5f;
//    }
//
//    public abstract void updateDescription();
//
//    public abstract void onEvoke();
//
//    public static com.megacrit.cardcrawl.orbs.AbstractOrb getRandomOrb(final boolean useCardRng) {
//        final ArrayList<com.megacrit.cardcrawl.orbs.AbstractOrb> orbs = new ArrayList<com.megacrit.cardcrawl.orbs.AbstractOrb>();
//        orbs.add(new Dark());
//        orbs.add(new Frost());
//        orbs.add(new Lightning());
//        orbs.add(new Plasma());
//        if (useCardRng) {
//            return orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1));
//        }
//        return orbs.get(MathUtils.random(orbs.size() - 1));
//    }
//
//    public void onStartOfTurn() {
//    }
//
//    public void onEndOfTurn() {
//    }
//
//    public void applyFocus() {
//        final AbstractPower power = AbstractDungeon.player.getPower("Focus");
//        if (power != null && !this.ID.equals("Plasma")) {
//            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
//            this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount);
//        }
//        else {
//            this.passiveAmount = this.basePassiveAmount;
//            this.evokeAmount = this.baseEvokeAmount;
//        }
//    }
//
//    public static int applyLockOn(final AbstractCreature target, final int dmg) {
//        int retVal = dmg;
//        if (target.hasPower("Lockon")) {
//            retVal *= (int)1.5f;
//        }
//        return retVal;
//    }
//
//    public abstract com.megacrit.cardcrawl.orbs.AbstractOrb makeCopy();
//
//    public void update() {
//        this.hb.update();
//        if (this.hb.hovered) {
//            TipHelper.renderGenericTip(this.tX + 96.0f * Settings.scale, this.tY + 64.0f * Settings.scale, this.name, this.description);
//        }
//        this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7f);
//    }
//
//    public void updateAnimation() {
//        this.bobEffect.update();
//        this.cX = MathHelper.orbLerpSnap(this.cX, AbstractDungeon.player.animX + this.tX);
//        this.cY = MathHelper.orbLerpSnap(this.cY, AbstractDungeon.player.animY + this.tY);
//        if (this.channelAnimTimer != 0.0f) {
//            this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
//            if (this.channelAnimTimer < 0.0f) {
//                this.channelAnimTimer = 0.0f;
//            }
//        }
//        this.c.a = Interpolation.pow2In.apply(1.0f, 0.01f, this.channelAnimTimer / 0.5f);
//        this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01f, this.channelAnimTimer / 0.5f);
//    }
//
//    public void setSlot(final int slotNum, final int maxOrbs) {
//        final float dist = 160.0f * Settings.scale + maxOrbs * 10.0f * Settings.scale;
//        float angle = 100.0f + maxOrbs * 12.0f;
//        final float offsetAngle = angle / 2.0f;
//        angle *= slotNum / (maxOrbs - 1.0f);
//        angle += 90.0f - offsetAngle;
//        this.tX = dist * MathUtils.cosDeg(angle) + AbstractDungeon.player.drawX;
//        this.tY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0f;
//        if (maxOrbs == 1) {
//            this.tX = AbstractDungeon.player.drawX;
//            this.tY = 160.0f * Settings.scale + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0f;
//        }
//        this.hb.move(this.tX, this.tY);
//    }
//
//    public abstract void render(final SpriteBatch p0);
//
//    protected void renderText(final SpriteBatch sb) {
//        if (!(this instanceof EmptyOrbSlot)) {
//            if (this.showEvokeValue) {
//                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + com.megacrit.cardcrawl.orbs.AbstractOrb.NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0f + com.megacrit.cardcrawl.orbs.AbstractOrb.NUM_Y_OFFSET, new Color(0.2f, 1.0f, 1.0f, this.c.a), this.fontScale);
//            }
//            else {
//                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + com.megacrit.cardcrawl.orbs.AbstractOrb.NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0f + com.megacrit.cardcrawl.orbs.AbstractOrb.NUM_Y_OFFSET, this.c, this.fontScale);
//            }
//        }
//    }
//
//    public void triggerEvokeAnimation() {
//    }
//
//    public void showEvokeValue() {
//        this.showEvokeValue = true;
//        this.fontScale = 1.5f;
//    }
//
//    public void hideEvokeValues() {
//        this.showEvokeValue = false;
//    }
//
//    public abstract void playChannelSFX();
//
//    static {
//        NUM_X_OFFSET = 20.0f * Settings.scale;
//        NUM_Y_OFFSET = -12.0f * Settings.scale;
//    }
//}
