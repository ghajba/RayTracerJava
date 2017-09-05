package hu.japy.dev.raytracer.puttingittogether;

import hu.japy.dev.raytracer.Tuple;

public class World {

    public final Tuple gravity;
    public final Tuple wind;

    public World(Tuple gravity, Tuple wind) {
        this.gravity = gravity;
        this.wind = wind;
    }

    public Projectile tick(Projectile p) {
        return new Projectile(p.position.add(p.velocity), p.velocity.add(gravity).add(wind));
    }
}
