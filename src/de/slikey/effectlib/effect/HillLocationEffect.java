package de.slikey.effectlib.effect;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.VectorUtils;

/**
 * Taken from http://en.wikipedia.org/wiki/Torus
 * 
 * @author Kevin
 * 
 */
public class HillLocationEffect extends LocationEffect {

	/**
	 * ParticleType of spawned particle
	 */
	public ParticleEffect particle = ParticleEffect.FLAME;

	/**
	 * Height of the hill in blocks
	 */
	public float height = 2.5f;
	
	/**
	 * Amount of particles per row
	 */
	public float particles = 30;
	
	/**
	 * Lenght of the edge
	 */
	public float edgeLenght = 6.5f;

	/**
	 * Rotation of the Hill
	 */
	public double yRotation = Math.PI / 7;

	public HillLocationEffect(EffectManager effectManager, Location location) {
		super(effectManager, location);
		type = EffectType.REPEATING;
		period = 10;
		iterations = 20;
	}

	@Override
	public void onRun() {
		Vector v = new Vector();
		for (int x = 0; x <= particles; x++) {
			double y1 = Math.sin(Math.PI * x / particles);
			for (int z = 0; z <= particles; z++) {
				double y2 = Math.sin(Math.PI * z / particles);
				v.setX(edgeLenght * x / particles).setZ(edgeLenght * z / particles);
				v.setY(height * y1 * y2);
				VectorUtils.rotateAroundAxisY(v, yRotation);

				particle.display(location.add(v), visibleRange, 0, 0, 0, 0, 0);
				location.subtract(v);
			}
		}
	}
}
