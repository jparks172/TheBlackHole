package Actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class LightOrbEvokeAction extends AbstractGameAction {
    private AbstractOrb abstractOrb;
    private CustomCard custumCard;
    private int evocAmount;

    public LightOrbEvokeAction(final CustomCard customCard, final AbstractOrb abstractOrb, int evocAmount){
        this.custumCard = customCard;
        this.abstractOrb = abstractOrb;
        this.evocAmount = evocAmount;
    }
    @Override
    public void update() {
        custumCard.updateCost(-evocAmount);
    }
}
