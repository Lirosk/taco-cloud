package tacocloud.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import tacocloud.domain.CustomUserDetails;
import tacocloud.domain.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>, CrudRepository<Taco, Long> {
    Optional<Taco> findById(Long id);
}
