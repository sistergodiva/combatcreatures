package se.snrn.combatcreatures.visualeffects;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import java.util.ArrayList;

public class VisualEffectManager implements Updatable, Renderable {

    private ArrayList<VisualEffect> effects;
    private ArrayList<VisualEffect> effectsToAdd;
    private ArrayList<VisualEffect> effectsToRemove;
    private boolean done;

    public VisualEffectManager() {

        done = true;
        effects = new ArrayList<>();
        effectsToAdd = new ArrayList<>();
        effectsToRemove = new ArrayList<>();


    }


    public void addEffect(VisualEffect effect) {
        effectsToAdd.add(effect);
        done = false;
    }


    @Override
    public void update(float delta) {


        if (!effectsToAdd.isEmpty()) {
            effects.addAll(effectsToAdd);
            effectsToAdd.clear();
        }
        if (!effectsToRemove.isEmpty()) {
            effects.removeAll(effectsToRemove);
            effectsToRemove.clear();
        }


        for (VisualEffect effect : effects
                ) {
            if (effect.isDone()) {
                effectsToRemove.add(effect);
            }
        }
        if (!effects.isEmpty()) {
            done = false;

            effects.get(0).update(delta);

        }

    }

    @Override
    public void render(Batch batch) {
        for (VisualEffect effect : effects
                ) {
            effect.render(batch);
        }
    }

    public boolean isDone() {
        return effects.isEmpty();
    }
}
