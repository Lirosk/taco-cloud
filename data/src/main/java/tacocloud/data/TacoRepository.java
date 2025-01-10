package tacocloud.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tacocloud.domain.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
    Optional<Taco> findById(Long id);
}
