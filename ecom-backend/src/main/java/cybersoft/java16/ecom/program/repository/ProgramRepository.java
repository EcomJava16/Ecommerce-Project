package cybersoft.java16.ecom.program.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.program.model.UserProgram;

@Repository
public interface ProgramRepository extends JpaRepository<UserProgram, UUID>{
	@Query("SELECT p FROM UserProgram  p LEFT JOIN p.roles r LEFT JOIN r.groups g LEFT JOIN g.users u WHERE p.name=?1 AND u.username=?2 ")
	UserProgram findProgramWithUsername(String programName, String username);

}
