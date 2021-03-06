package se.snrn.combatcreatures.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import se.snrn.combatcreatures.CombatCreatures;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        //GwtApplicationConfiguration configuration = new GwtApplicationConfiguration(1280, 720);

        return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new CombatCreatures();
    }
}