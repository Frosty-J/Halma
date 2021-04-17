package rocks.poopjournal.halma.menu;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.Halma;
import rocks.poopjournal.halma.play.Play;

public class Menu extends BaseScreen {
    //variables
    private int playerCount;
    private int computerCount;
    public Label titleLabel;
    public PlayerButtons playerButtons;
    public ComputerButtons computerButtons;
    private TextButton squareBoardButton;
    public Button startButton;
    private boolean squareBoard;
    public Label rules;
    public Label infoLabel;

    public int state = 0;

    public Menu(Halma halma) {
        super(halma);
        create();
    }

    public void create(){
        playerCount = 2;
        computerCount = 0;
        squareBoard = false;

        titleLabel = new Label("choose amount of players", skin);
        titleLabel.setPosition(stage.getWidth()/2 - titleLabel.getWidth()/2, stage.getHeight()*0.9f);
        stage.addActor(titleLabel);

        playerButtons = new PlayerButtons(skin, this).create();
        computerButtons = new ComputerButtons(skin, this).create();
        stage.addActor(playerButtons);
        stage.addActor(computerButtons);

        squareBoardButton = new TextButton("Square Board", skin);
        squareBoardButton.setPosition(stage.getWidth()/2-squareBoardButton.getWidth()/2,
                stage.getHeight()/3-squareBoardButton.getHeight()/2);
        stage.addActor(squareBoardButton);
        squareBoardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        float dif= stage.getWidth()/5;
        infoLabel=new Label("about us",skin);
        rules=new Label("play-rules ",skin);
        infoLabel.setPosition(stage.getWidth()/2 + dif/2- infoLabel.getWidth()/2, stage.getHeight()/6-infoLabel.getHeight()/2);
        rules.setPosition(stage.getWidth()/2 - dif/2- rules.getWidth()/2, stage.getHeight()/6-rules.getHeight()/2);
        stage.addActor(rules);
        stage.addActor(infoLabel);
        rules.setColor(Color.CLEAR);
        infoLabel.setColor(Color.CLEAR);


        startButton = new Button(skin, "play");
        startButton.setPosition(stage.getWidth()/2 - startButton.getWidth()/2, stage.getHeight()/2 - startButton.getHeight()/2);
        stage.addActor(startButton);
        addButtonsToListener(startButton, squareBoardButton);
        addButtonsToListener(playerButtons.buttons);
        addButtonsToListener(computerButtons.buttons);
        startButton.setColor(1,1,1,0);
        computerButtons.setColor(1,1,1,0);
        playerButtons.setColor(1,1,1,0);

        playerButtons.addAction(Actions.color(Color.WHITE, 2f));
    }

    @Override
    public void clicked(Actor a) {
        super.clicked(a);
        if(computerButtons.buttons.contains(a)) computerButtons.clicked(a);
        if(playerButtons.buttons.contains(a)) playerButtons.clicked(a);
        if(a == squareBoardButton) squareBoard = squareBoardButton.isChecked();
        if(a == startButton) halma.setScreen(Play.createInstance(halma, playerCount, computerCount, squareBoard));

        computerCount = computerButtons.result;
        playerCount = playerButtons.result;

        if(squareBoard)
        {
            if(playerCount > 4)
                playerCount = 4;
            if(playerCount == 3)
                playerCount = 4;
        }

        if(computerCount >= playerCount)
            computerCount = playerCount-1;
    }
}