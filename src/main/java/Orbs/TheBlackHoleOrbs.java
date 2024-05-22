package Orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.BobEffect;

import java.util.ArrayList;

public abstract class TheBlackHoleOrbs extends AbstractOrb {
    public String name;
    public String [] description;
    public String ID;
    protected ArrayList<PowerTip> tips;
    public int evokeAmount;
    public int passiveAmount;
    protected int baseEvokeAmount;
    protected int basePassiveAmount;
    public float cX;
    public float cY;
    public float tX;
    public float tY;
    protected Color color;
    protected static final int W = 96;
    public Hitbox hb;
    String imgResource;
    protected Texture img;
    protected BobEffect bobEffect;
    protected float angle;
    protected float scale;
    protected float fontScale;
    protected boolean showEvokeValue;
    protected static final float CHANNEL_IN_TIME = 0.5f;
    protected float channelAnimTimer;
    private com.badlogic.gdx.graphics.Texture orbImg;

    public TheBlackHoleOrbs(String ID, String name, String [] description,int baseEvokeAmount, int basePassiveAmount,
                            Texture texture){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.baseEvokeAmount = baseEvokeAmount;
        this.basePassiveAmount = basePassiveAmount;
        this.passiveAmount = this.basePassiveAmount;
        this.evokeAmount = this.baseEvokeAmount;
        this.orbImg = texture;

        this.cX = 0.0f;
        this.cY = 0.0f;
        this.hb = new Hitbox(96.0f * Settings.scale, 96.0f * Settings.scale);
        this.bobEffect = new BobEffect(3.0f * Settings.scale, 3.0f);
        this.fontScale = 0.7f;
    }

    public void setSlot(final int slotNum, final int maxOrbs) {
        final float dist = 160.0f * Settings.scale + maxOrbs * 10.0f * Settings.scale;
        float angle = 100.0f + maxOrbs * 12.0f;
        final float offsetAngle = angle / 2.0f;
        angle *= slotNum / (maxOrbs - 1.0f);
        angle += 90.0f - offsetAngle;
        this.tX = dist * MathUtils.cosDeg(angle) + AbstractDungeon.player.drawX;
        this.tY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0f;
        if (maxOrbs == 1) {
            this.tX = AbstractDungeon.player.drawX;
            this.tY = 160.0f * Settings.scale + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0f;
        }
        this.hb.move(this.tX, this.tY);
    }

    @Override
    public void updateDescription() {

    }

    @Override
    public void onEvoke() {
    }

    public abstract AbstractOrb makeCopy();
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
}
