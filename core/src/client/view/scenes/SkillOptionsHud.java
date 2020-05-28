package client.view.scenes;

import client.controller.HandleInput;
import client.model.map.Field;
import client.view.utility.Constants;
import client.view.utility.Drawable;
import client.view.utility.Updatable;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SkillOptionsHud implements Drawable, Updatable {

    public Stage stage;
    private Viewport viewport;

    public SkillOptionsHud(SpriteBatch spriteBatch, Skin skin) {
        viewport = new FillViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        stage.draw();
    }

    public void skillOptions(HandleInput handleInput, Field[][] map, Skin skin) {
        TextField text;
        String s;
        int x = 730, y = 60, height = 60, width = 250;
        int[] tab = handleInput.getTab();
        if (map[tab[0]][tab[1]].getHero() != null) {
            int size = map[tab[0]][tab[1]].getHero().getSkills().size();
            for (int i = 0; i < size + 1; i++) { //adding one in order to add exit
                if (i < size)
                    s = map[tab[0]][tab[1]].getHero().getSkills().get(i).toString();
                else
                    s = "Exit";
                text = new TextField(s, skin);
                text.setSize(width, height);
                text.setPosition(x, Constants.HEIGHT - y * (i + 2));
                text.setAlignment(Align.center);
                handleInput.addRectangles(x, y * (i + 1), width, height);
                stage.addActor(text);
            }
        } else {
            handleInput.currentState = HandleInput.ControllerState.IDLE;
        }
    }

    @Override
    public void update(float delta) {
        for (Actor actor : stage.getActors()) {
            actor.remove();
        }
    }
}