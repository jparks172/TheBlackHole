//package Orbs;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.MathUtils;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.OrbStrings;
//import com.megacrit.cardcrawl.orbs.AbstractOrb;
//
//
//
//public class GravOrb extends TheBlackHoleOrbs {
//    private static final String ID = "blackHole:Gravity_Orb";
//    private static final OrbStrings ORB_STRINGS;
//    private static final String [] DESCRIPTION;
//    private static final String NAME;
//    private static final int STRENGTH_PASSIVE = 1;
//    private static final int EVOCATION_DMG = 6;
//    private static final String ORB_IMAGE= "img/lightning.png";
//    private static final com.badlogic.gdx.graphics.Texture ORB_TEXURE = new Texture (ORB_IMAGE);
//
//
//    public GravOrb(){
//        super(ID,NAME,DESCRIPTION,EVOCATION_DMG,STRENGTH_PASSIVE,ORB_TEXURE);
//
//    }
//
//    @Override
//    public void updateDescription() {
//        this.applyFocus();
//
//
//    }
//
//    @Override
//    public void onEvoke() {
//
//    }
//
//
//    public AbstractOrb makeCopy() {
//        return (new GravOrb());
//    }
//
//    @Override
//    public void playChannelSFX() {
//        public void render(final SpriteBatch sb) {
//            sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
//            sb.setBlendFunction(770, 1);
//            sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
//            sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale + MathUtils.sin(this.angle / 12.566371f) * 0.05f + 0.19634955f, this.scale * 1.2f, this.angle, 0, 0, 96, 96, false, false);
//            sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale * 1.2f, this.scale + MathUtils.sin(this.angle / 12.566371f) * 0.05f + 0.19634955f, -this.angle, 0, 0, 96, 96, false, false);
//            sb.setBlendFunction(770, 771);
//            sb.setColor(this.c);
//            sb.draw(this.img, this.cX - 48.0f, this.cY - 48.0f + this.bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, this.angle / 12.0f, 0, 0, 96, 96, false, false);
//            this.renderText(sb);
//            this.hb.render(sb);
//        }
//    }
//    static{
//        ORB_STRINGS = CardCrawlGame.languagePack.getOrbString(ID);
//        DESCRIPTION =  ORB_STRINGS.DESCRIPTION;
//        NAME = ORB_STRINGS.NAME;
//    }
//}
