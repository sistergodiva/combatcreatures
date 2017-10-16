package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import java.util.List;


public class TextLog implements Updatable, Renderable{


    private float margin = 8;
    private int x;
    private int y;
    private float textTop;

    public TextLog(int x, int y) {
        this.x = x;
        this.y = y;
        ResourceManager.glyphLayout.setText(ResourceManager.font, "test");
        textTop = y+((ResourceManager.glyphLayout.height+margin)* 4);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

        ResourceManager.uiNinePatch.draw(batch, x,y, 300,textTop+margin);


        List<String> messages = GameLog.getLastMessages(4);
        int i = 0;
        for (String message : messages) {

            ResourceManager.font.draw(batch, message, x+margin, textTop-((ResourceManager.glyphLayout.height+margin)* i));
            i++;
        }

    }
}
