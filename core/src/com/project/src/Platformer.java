package com.project.src;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.MathUtils;
import java.util.Iterator;
import com.badlogic.gdx.physics.box2d.*;

public class Platformer extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture dropImg;
	Texture bucketImg;
	Rectangle bucket;
	Array<Rectangle> rain;
	long lastDropTime;

	World world;
    Box2DDebugRenderer debugRenderer;
	@Override
	public void create () {
	    Box2D.init();
		batch = new SpriteBatch();
		dropImg = new Texture("droplet.png");
		bucketImg = new Texture("bucket.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        rain = new Array<Rectangle>();

        //Adding box2d stuff

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(100, 300);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 10f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
// Set its world position
        groundBodyDef.position.set(new Vector2(0, 10));

// Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

// Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
// Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
// Clean up after ourselves
        groundBox.dispose();

	}

	@Override
	public void render () {
        debugRenderer.render(world, camera.combined);
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.end();

        debugRenderer.render(world, camera.combined);

        world.step(1/45f, 6, 2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bucketImg.dispose();
        dropImg.dispose();
        batch.dispose();
	}
}
