package Actions;

import Orbs.GravityOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import java.util.Collections;

public class GravityOrbEvokeAction extends AbstractGameAction {
    private AbstractPlayer player;
    private AbstractOrb abstractOrb;

    public GravityOrbEvokeAction(AbstractPlayer player) {
        this.player = player;

    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters() != null) {
            evokeOrbBH();
        }
    }

    public void evokeOrbBH() {
        if (!player.orbs.isEmpty() && !(player.orbs.get(0) instanceof EmptyOrbSlot)) {
            if (player.orbs.get(0) instanceof GravityOrb) {
                player.orbs.get(0).onEvoke();
                final AbstractOrb orbSlot = new EmptyOrbSlot();
                for (int i = 1; i < player.orbs.size(); ++i) {
                    Collections.swap(player.orbs, i, i - 1);
                }
                AbstractDungeon.player.orbs.set(AbstractDungeon.player.orbs.size() - 1, orbSlot);
                for (int i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
                    AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
                }
            } else {
                int orbIndex = 0;
                for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof GravityOrb) {
                        player.orbs.get(i).onEvoke();
                        final AbstractOrb orbSlot = new EmptyOrbSlot();
                        player.orbs.set(i, orbSlot);
                        break;
                    }
                }

                for (int i = 1; i < AbstractDungeon.player.orbs.size(); i++) {
                    Collections.swap(AbstractDungeon.player.orbs, i, i - 1);
                }
                for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
                    AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
                }
            }
        }
    }
}
