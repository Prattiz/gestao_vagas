package br.com.thiagopratti.gestao_vagas.modules.company.controllers;

import br.com.thiagopratti.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.thiagopratti.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.CompanyRepos;
import br.com.thiagopratti.gestao_vagas.utils.TestUtils;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepos companyRepos;


    @Before
    public void setup(){
        mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception{

        var company = CompanyEntity.builder()
        .description("COMPANY DESCRIPTION")
        .email("EMAIL@COMPANY.COM")
        .password("PASSWORD_COMPANY")
        .username("COMPANY_USERNAME")
        .name("NAME_COMPANY")
        .build();


        company = companyRepos.saveAndFlush(company);

        
        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createdJobDTO))
            .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }
}