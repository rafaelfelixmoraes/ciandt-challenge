package com.rafaelfelix.ciandt.challenge.delivery.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelfelix.ciandt.challenge.dto.DeliveryDataDTO;
import com.rafaelfelix.ciandt.challenge.dto.PackageDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryDataTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @Test
	 public void testInsertDeliveryData() throws Exception {
		 List<PackageDTO> packageData = new ArrayList<>();
		 packageData.add(new PackageDTO(1, 15.50));
		 packageData.add(new PackageDTO(2, 10.00));
		 packageData.add(new PackageDTO(1, 06.40));
		 
		 DeliveryDataDTO deliveryData = new DeliveryDataDTO(10, "100123", "000123000456", packageData);
		 
		 mockMvc.perform(post("/delivery")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(deliveryData)))
		        .andExpect(status().isCreated());
	 }
	 
	 @Test
	 @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:data.sql")
	 public void testGetDeliverySteps() throws Exception {
		 mockMvc.perform(get("/delivery/{deliveryId}/step", "123456"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(jsonPath("$[0].step", is(1)))
		        .andExpect(jsonPath("$[0].from", is("Zona de Abastecimento")))
		        .andExpect(jsonPath("$[0].to", is("Zona do Caminhão")));
	 }
}
