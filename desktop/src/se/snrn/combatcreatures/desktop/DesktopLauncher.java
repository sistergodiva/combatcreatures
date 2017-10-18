package se.snrn.combatcreatures.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.snrn.combatcreatures.CombatCreatures;

public class DesktopLauncher {
    public static void main(String[] arg) {
        //System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;

        //config.width = 1920;
        //config.height =1080;
        //config.fullscreen = true;
        new LwjglApplication(new CombatCreatures(), config);
    }
}
