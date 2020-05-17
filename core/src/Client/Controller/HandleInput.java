package Client.Controller;

import Client.Controller.DistanceValidator;
import Client.Controller.Move;
import Client.GUI.Constans;
import Client.GUI.Screens.PlayScreen;
import com.badlogic.gdx.InputProcessor;


public class HandleInput implements InputProcessor {

    public boolean heroChosen;
    private boolean skillChosen;
    private PlayScreen game;
    private int x, y;
    private int[] tab = new int[2];

    public int[] getTab() {
        return tab;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public HandleInput(PlayScreen game) {
        this.heroChosen = false;
        this.skillChosen = false;
        this.game = game;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX<=704) {
            getCord(screenX, screenY);
            getCord(screenX, screenY); //zwraca cordy gdzie przycisnelismy
            if (!heroChosen && game.client.getReceived().getMap()[tab[0]][tab[1]].getHero() != null) {
                heroChosen = true;
                this.y = tab[0];
                this.x = tab[1];
                return true;
            }
            if (heroChosen) {
                Move move = new Move(game.client.getReceived().getMap()[y][x].getHero(), game.client.getReceived().getMap()[tab[0]][tab[1]], game.client.getReceived().getMap()[y][x], game.client.getReceived().getMap()[y][x].getHero().getSkills().get(0));
                if (DistanceValidator.isValid(game.client.getReceived(), move)) {
                    game.client.getSend().addMove(move);
                    System.out.println("Adding move...");
                    System.out.println(game.client.getSend().getMoves().size());
                    heroChosen = false;
                    return true;
                }
            }
        }
        return false;
    }

    public void getCord(int x, int y) {
        for (int i = 0; i < game.size; i++) {
            for (int j = 0; j < game.size; j++) {
                if (game.getGameObjects()[i][j].contains(x, Constans.HEIGHT - y)) {
                    tab[0] = i;
                    tab[1] = j;
                }
            }
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
