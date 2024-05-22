package Orbs;

import Actions.MonsterPullHandler;
import Powers.GravitationalPullPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;

public class GravityOrb extends AbstractOrb {
    public static final String ORB_ID = "blackHole:Gravity_Orb";
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = GravityOrb.orbString.DESCRIPTION;
    private float vfxTimer;
    private float vfxIntervalMin;
    private float vfxIntervalMax;
    private static final int BASE_EVOC = 10;
    public static int basePull = Math.round(30 * Settings.scale);
    public static int evocRange = Math.round(400 * Settings.scale);
    private static final float PI_DIV_16 = 0.19634955f;
    private static final float ORB_WAVY_DIST = 0.05f;
    private static final float PI_4 = 12.566371f;
    private static final float ORB_BORDER_SCALE = 1.2f;
    private MonsterPullHandler monsterPullHandler;
    public GravityOrb() {
        this.vfxTimer = 1.0f;
        this.vfxIntervalMin = 0.15f;
        this.vfxIntervalMax = 0.8f;
        this.ID = ORB_ID;
        this.img = new Texture("img/GravityOrb1.png");
        this.name = GravityOrb.orbString.NAME;
        this.baseEvokeAmount = BASE_EVOC;
        this.evokeAmount = this.baseEvokeAmount;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0f);
        this.channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0] + Math.round(basePull * Settings.scale) + DESC[1] + DESC[2] + this.evokeAmount + DESC[3] + GravitationalPullPower.gOrbTriggerLoc + DESC[4];
    }

    @Override
    public void onEvoke() {
        if (AbstractDungeon.getMonsters() != null) {
            {
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if(MonsterPullHandler.inGravOrbRange(monster)) {
                        AbstractDungeon.actionManager.addToTop(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    }
                }
            }
        }
//        AbstractDungeon.actionManager.addToBottom(new GravityOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS)));
    }

    @Override
    public void onEndOfTurn() {
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;
        MonsterPullHandler.pullRandomMonster(monsters, GravityOrb.basePull);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new GravityOrb();
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

    public static boolean hasGravOrb(AbstractPlayer p) {
        boolean foundOrb = false;
        if (!p.orbs.isEmpty()) {
            int count = 0;
            for (AbstractOrb abstractOrb :p.orbs) {
                if (abstractOrb instanceof GravityOrb) {
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
}

