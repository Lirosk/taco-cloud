package tacocloud.data;

import org.springframework.data.repository.CrudRepository;

import tacocloud.domain.CustomUserDetails;

public interface UserRepository extends CrudRepository<CustomUserDetails, Long> {
    CustomUserDetails findByUsername(String username);

    CustomUserDetails findByIdAndUsername(Long id, String username);
}
