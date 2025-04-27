package SandB;

import java.util.List;

public class ParticleList {
    List<Particle> particles;
    public ParticleList(List<Particle> particles) {
        this.particles = particles;
    }
    public Particle getParticle(String name) {
        return particles.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
    public void addParticle(Particle p) {
        particles.add(p);
    }
    public List<String> getNames() {
        return particles.stream().map(Particle::getName).toList();
    }
}
