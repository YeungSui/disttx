package disttx.club.repository;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import disttx.club.model.TeamDetails;
import disttx.generic.repository.GenericRepository;

@Repository
@PersistenceContext(name="secondaryPU")
public class ClubRepository extends GenericRepository<TeamDetails>{
	
}
