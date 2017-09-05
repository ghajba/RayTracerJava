package hu.japy.dev.raytracer.puttingittogether;

import hu.japy.dev.raytracer.Tuple;

public class Projectile {
    public final Tuple position;
    public final Tuple velocity;

    public Projectile(Tuple position, Tuple velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}
