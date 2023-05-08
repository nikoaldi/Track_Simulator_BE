package pkg.Radar;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RadarRepository implements PanacheRepository<Radar> {
}
