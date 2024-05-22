package Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz=AbstractMonster.class,
        method="renderName"
)
public class MonsterDistanceInfo {
    @SpirePrefixPatch
    public static void updateRenderName(AbstractMonster __instance, SpriteBatch sb){

            __instance.name = __instance.name + "\n" + "Distance to Player:" +
                    Math.abs(AbstractDungeon.player.drawX - __instance.drawX) + " Units";

    }

    @SpirePostfixPatch
    public static void resetToOriginalName(AbstractMonster __instance, SpriteBatch sb){

            __instance.name = CardCrawlGame.languagePack.getMonsterStrings(__instance.id).NAME;
        }

}
