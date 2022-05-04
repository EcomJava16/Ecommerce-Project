package cybersoft.java16.ecom.security.authorization;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import cybersoft.java16.ecom.common.exception.UnAuthorizedException;
import cybersoft.java16.ecom.program.model.UserProgram;
import cybersoft.java16.ecom.program.model.ProgramAuthorization;
import cybersoft.java16.ecom.program.repository.ProgramRepository;
import lombok.extern.log4j.Log4j2;

@Aspect
@Component
public class AuthorizationAspect {
	@Qualifier(value = "auditorAware")
	@Autowired
	private AuditorAware<String> auditor;
	@Autowired
	private ProgramRepository repository;

	@Before("@annotation(program)")
	public void beforeAuthorization(ProgramAuthorization program) {
		String username = auditor.getCurrentAuditor().get();
		String programName = program.value();
		if (checkPermission(username, programName)) {
			throw new UnAuthorizedException();
		}
	}

	private boolean checkPermission(String username, String programName) {
		UserProgram program = repository.findProgramWithUsername(programName, username);
		if (program == null) {
			return true;
		}
		return false;
	}
}
