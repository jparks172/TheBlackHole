package Actions;

import Orbs.GravityOrb;
import Powers.GravitationalPullPower;
import Powers.ReducedGravityPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import relics.GravitationalPull;

import java.util.ArrayList;

public class MonsterPullHandler {



    public static  AbstractPlayer player = AbstractDungeon.player;

    public static int calculateTotalDist(int triggerLoc, int drawX){
        return Math.abs(triggerLoc - drawX);
    }

    public static int normalizeDist(int currentPos, int totalDist){
        return Math.round(((float) currentPos / (float)totalDist) * 100);
    }

    public static boolean isInRange(int normalizedDistToTarget){
        return normalizedDistToTarget <= 0;
    }

    public static void pullMonster(AbstractMonster monster, int pullDist) {
        pullDist = MonsterPullHandler.handleBossPull(monster, pullDist);
        if(inEHRange(monster)){
            return;
        }
        else{
            monster.drawX -= pullDist;
        }
        monster.getPower("blackHole:Gravitational_Pull_Power").onSpecificTrigger();
    }

    public static void pullMonsterExact(AbstractMonster monster, int pullDist) {
        pullDist = MonsterPullHandler.handleBossPull(monster, pullDist);
        if(inEHRange(monster)){
            return;
        }
        else{
            if(hasReducedGravity(monster)){
                pullDist /= ReducedGravityPower.pullMultiplier;
                monster.drawX = pullDist;
            }
            else {
                monster.drawX = pullDist;
            }
        }

        monster.getPower("blackHole:Gravitational_Pull_Power").onSpecificTrigger();
    }

    public static void pullAllMonstersExact(AbstractPlayer player, ArrayList<AbstractMonster> monsterList, float exactLoc){
        exactLoc = Math.round(exactLoc * Settings.scale);
        for (AbstractMonster monster : monsterList) {
            if (monster.type.equals(AbstractMonster.EnemyType.BOSS)) {
                exactLoc = MonsterPullHandler.handleBossPull(monster, Math.round(Math.abs(monster.drawX - exactLoc)));
            }
            if (inEHRange(monster) && !monster.isDeadOrEscaped()) {
               continue;
            }
            else{
                monster.drawX = player.drawX + exactLoc;
            }
            monster.getPower("blackHole:Gravitational_Pull_Power").onSpecificTrigger();
        }
    }




    public static void pullAllMonsters(ArrayList<AbstractMonster> monsterList, int pullDist){
        for (AbstractMonster monster : monsterList) {
            pullDist = MonsterPullHandler.handleBossPull(monster, pullDist);
            if (inEHRange(monster)) {
                continue;
            }
            else{
                monster.drawX -= pullDist;
            }
            monster.getPower("blackHole:Gravitational_Pull_Power").onSpecificTrigger();
        }
    }

    public static void pullRandomMonster(ArrayList<AbstractMonster> monsters, int pullDist){
        ArrayList<AbstractMonster> monstersNotInEHRange = new ArrayList<>();
        AbstractMonster monster = null;
        for(AbstractMonster m : monsters){
            if(!MonsterPullHandler.inEHRange(m)){
                monstersNotInEHRange.add(m);
            }
        }
        if (!monstersNotInEHRange.isEmpty()){
            if (monstersNotInEHRange.size() == 1) {
                monster = monstersNotInEHRange.get(0);
                MonsterPullHandler.handleBossPull(monster, pullDist);
                MonsterPullHandler.pullMonster(monster, pullDist);
            } else {
                int index = (int) (Math.random() * monstersNotInEHRange.size());
                monster = monstersNotInEHRange.get(index);
                MonsterPullHandler.handleBossPull(monster, pullDist);
                MonsterPullHandler.pullMonster(monster, pullDist);
            }
        }
        if (monster != null) {
            monster.getPower("blackHole:Gravitational_Pull_Power").onSpecificTrigger();
        }
    }

    public static boolean inEHRange(AbstractMonster monster){
        return monster.drawX <= GravitationalPullPower.eHorizonTriggerLoc && !monster.isDeadOrEscaped();
    }
    public static boolean inGravOrbRange(AbstractMonster monster){
        return monster.drawX <= GravitationalPullPower.gOrbTriggerLoc;
    }

    public static boolean hasReducedGravity(AbstractMonster monster){
        return monster.hasPower(ReducedGravityPower.POWER_ID);
    }

    public static int handleBossPull(AbstractMonster monster, int pullDist){
        if(monster.type.equals(AbstractMonster.EnemyType.BOSS) && pullDist > GravitationalPull.bossMaxPull){
            return Math.round(monster.drawX - GravitationalPull.bossMaxPull);
        }else{
            if(hasReducedGravity(monster)) {
                pullDist *= ReducedGravityPower.pullMultiplier;
            }
        }
        return pullDist;
    }
}
