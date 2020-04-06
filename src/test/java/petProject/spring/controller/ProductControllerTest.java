package petProject.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import petProject.spring.dto.ProductDTO;
import petProject.spring.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
	}

	@Test
	void getProductsWhenEmptyList() throws Exception {
		BDDMockito.given(productService.getAll())
				.willReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	void getProductsWhenOK() throws Exception {
		ProductDTO dto = new ProductDTO("name", "supplier", 10, "cat");
		given(productService.getAll())
				.willReturn(List.of(dto)); // todo: !!!!! СКОБКИ


		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		String expectedResult = writeObjectAsString(List.of(dto));

		assertEquals(expectedResult, result);
	}

	@Test
	void getProductsWhenOK2() throws Exception {
		ProductDTO dto = new ProductDTO("name", "supplier", 10, "cat");
		ProductDTO dto2 = new ProductDTO("name2", "supplier2", 20, "cat2");
		given(productService.getAll())
				.willReturn(List.of(dto, dto2)); // todo: !!!!! СКОБКИ


		mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2))) // todo: СКОБКИ (2)
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalTo("name")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].supplier", equalTo("supplier")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].count", equalTo(10)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category", equalTo("cat")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalTo("name2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].supplier", equalTo("supplier2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].count", equalTo(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].category", equalTo("cat2")));
	}

	private String writeObjectAsString(Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
