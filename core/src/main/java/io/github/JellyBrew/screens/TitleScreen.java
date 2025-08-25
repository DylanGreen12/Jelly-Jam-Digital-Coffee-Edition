package io.github.JellyBrew.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TitleScreen implements Screen {

    private final Stage stage;
    private final SpriteBatch batch;
    private final Texture background;
    private final Skin skin;
    private final BitmapFont font;
    
    // Button configuration
    private final String[] buttonLabels = {"Single Play", "Multi Play", "Rules", "Settings", "Quit"};
    private TextButton[] buttons;
    
    // Colors
    private final Color buttonColor = new Color(0.2f, 0.2f, 0.6f, 0.8f);
    private final Color hoverColor = new Color(0.3f, 0.3f, 0.8f, 1f);
    private final Color textColor = Color.WHITE;
    
    public TitleScreen() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        // Load background image
        background = new Texture(Gdx.files.internal("title/title.png"));
        
        // Create skin and font for buttons
        skin = new Skin();
        font = new BitmapFont();
        
        // Setup button style
        skin.add("default-font", font);
        createButtonStyle();
        
        // Create buttons
        createButtons();
    }
    
    private void createButtonStyle() {
        // Create solid color textures for button backgrounds
        Texture buttonTexture = createSolidColorTexture(buttonColor);
        Texture hoverTexture = createSolidColorTexture(hoverColor);
        
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = skin.getFont("default-font");
        buttonStyle.fontColor = textColor;
        buttonStyle.overFontColor = Color.LIGHT_GRAY;
        
        // Set background textures
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        buttonStyle.over = new TextureRegionDrawable(new TextureRegion(hoverTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(hoverTexture));
        
        skin.add("default", buttonStyle);
    }
    
    private Texture createSolidColorTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    
    private void createButtons() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        
        // Calculate button positions relative to screen size
        float buttonWidth = screenWidth * 0.3f;
        float buttonHeight = screenHeight * 0.08f;
        float buttonSpacing = screenHeight * 0.02f;
        
        // Start position for buttons (centered vertically)
        float startY = screenHeight * 0.5f;
        
        buttons = new TextButton[buttonLabels.length];
        
        for (int i = 0; i < buttonLabels.length; i++) {
            final String buttonName = buttonLabels[i];
            
            TextButton button = new TextButton(buttonName, skin);
            
            // Position button centered horizontally
            float x = (screenWidth - buttonWidth) / 2;
            float y = startY - (i * (buttonHeight + buttonSpacing));
            
            button.setPosition(x, y);
            button.setSize(buttonWidth, buttonHeight);
            
            // Scale font based on screen size
            float fontScale = Math.min(screenWidth / 800f, screenHeight / 600f) * 1.2f;
            button.getLabel().setFontScale(fontScale);
            
            // Add click listener
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.log("TitleScreen", "Button pressed: " + buttonName);
                    
                    switch (buttonName) {
                        case "Single Play":
                            // TODO: Implement single player screen
                            break;
                        case "Multi Play":
                            // TODO: Implement multiplayer screen
                            break;
                        case "Rules":
                            // TODO: Implement rules screen
                            break;
                        case "Settings":
                            // TODO: Implement settings screen
                            break;
                        case "Quit":
                            Gdx.app.exit();
                            break;
                    }
                }
            });
            
            stage.addActor(button);
            buttons[i] = button;
        }
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        ScreenUtils.clear(0, 0, 0, 1);
        
        // Draw background stretched to fit the window size
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        
        // Draw UI
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update both the stage viewport AND the batch projection matrix
        stage.getViewport().update(width, height, true);
        
        // Update the batch's projection matrix to match the screen size
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        
        // Recreate buttons with new screen size
        stage.clear();
        createButtons();
    }

    @Override
    public void pause() {
        // Not needed for this screen
    }

    @Override
    public void resume() {
        // Not needed for this screen
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.dispose();
        skin.dispose();
        font.dispose();
    }
}