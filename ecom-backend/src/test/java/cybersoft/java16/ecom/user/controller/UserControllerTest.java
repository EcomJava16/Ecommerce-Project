package cybersoft.java16.ecom.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import cybersoft.java16.ecom.user.service.UserService;

@DisplayName("UserControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin")
public class UserControllerTest {
	@MockBean
	private UserService service;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnEmpltyListWhenNoEntityFound() throws Exception {
		when(service.findAllUser()).thenReturn(List.of());
		mockMvc.perform(get("/api/v1/user")).andExpect(status().isFound());
	}
}
