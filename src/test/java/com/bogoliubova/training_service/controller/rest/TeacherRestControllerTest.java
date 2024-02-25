package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.ErrorResponse;
import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.entity.Rating;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/createTestDB.sql")
@Sql("/addTestDB.sql")
class TeacherRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    TeacherDto teacherDto = new TeacherDto();

    @BeforeEach
    public void setup() {
        List<Direction> directions = new ArrayList<>();
        Location location = null;
        List<Rating> ratings = new ArrayList<>();

        teacherDto.setFirstName("Ulysses");
        teacherDto.setLastName("Runte");
        teacherDto.setTeachEmail("monroe.hilpert@yahoo.com");
        teacherDto.setDirections(directions);
        teacherDto.setLocation(location);
        teacherDto.setRatings(ratings);
    }

    @Test
    void createTeacherRestIntegrationTest() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setFirstName("NewFirstname");
        teacher.setLastName("NewLastname");
        teacher.setTeachEmail("NEWTEACHEMAIL@YAHOO.COM");

        String newStringTeacher = objectMapper.writeValueAsString(teacher);
        MvcResult createTeacherPositiveResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/teacher/createTeacherRest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringTeacher))
                .andReturn();

        assertEquals(200, createTeacherPositiveResult.getResponse().getStatus());

        String teacherResultJSON = createTeacherPositiveResult.getResponse().getContentAsString();
        Teacher teacherResult = objectMapper.readValue(teacherResultJSON, Teacher.class);

        assertNotNull(teacherResult.getTeacherId());
        assertEquals(teacher.getFirstName(), teacherResult.getFirstName());
        assertEquals(teacher.getLastName(), teacherResult.getLastName());
        assertEquals(teacher.getTeachEmail(), teacherResult.getTeachEmail());
    }

    @Test
    void getFirstNameAndLastNameAndRatingsIntegrationTest() throws Exception {
        List<Rating> ratings = new ArrayList<>();
        TeacherFullNameAndRatingDto teacherFNRDto = new TeacherFullNameAndRatingDto();
        teacherFNRDto.setFirstName("Ulysses");
        teacherFNRDto.setLastName("Runte");
        teacherFNRDto.setRatings(ratings);

        String teacherNewString = objectMapper.writeValueAsString(teacherFNRDto);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/id_teacherRest/{teacher_id}", "837e8317-e35a-4cd1-f710-387841923887")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/id_teacherRest/{teacher_id}", "837e8317-e35a-4cd1-f710-387841923000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(404, mockNegativeResult.getResponse().getStatus());

        String errorMessage = objectMapper.readValue(mockNegativeResult.getResponse().getContentAsString(), ErrorResponse.class).getMessage();
        assertEquals("!!!!! " + ErrorMassage.M_TEACHER_NOT_FOUND, errorMessage);

        TeacherFullNameAndRatingDto teacherResult = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        //assertEquals(teacherResult, teacherFNRDto);
        assertEquals(teacherResult.getFirstName(), teacherFNRDto.getFirstName());
        assertEquals(teacherResult.getLastName(), teacherFNRDto.getLastName());
        //assertEquals(teacherResult.getRatings(), teacherFNRDto.getRatings());
    }

    @Test
    void getTeacherByCityIntegrationTest() throws Exception {
        String teacherNewString = objectMapper.writeValueAsString(teacherDto);

        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherCity/{city}", "Dortmund")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherCity/{city}", "Dnepr")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(404, mockNegativeResult.getResponse().getStatus());

        String errorMessage = mockNegativeResult.getResponse().getContentAsString();
        assertTrue(errorMessage.contains("!!!!! " + ErrorMassage.M_TEACHER_IN_THIS_CITY_NOT_FOUND));

        List<TeacherDto> teacherResultList = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(1, teacherResultList.size());
        assertEquals(teacherResultList.get(0).getFirstName(), teacherDto.getFirstName());
        assertEquals(teacherResultList.get(0).getLastName(), teacherDto.getLastName());
    }

    @Test
    void getTeacherByRatingIntegrationTest() throws Exception {
        String teacherNewString = objectMapper.writeValueAsString(teacherDto);

        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherRating/{rating}", 7)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherRating/{rating}", 12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(404, mockNegativeResult.getResponse().getStatus());

        List<TeacherDto> teacherResultList = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(1, teacherResultList.size());
        assertEquals(teacherResultList.get(0).getFirstName(), teacherDto.getFirstName());
        assertEquals(teacherResultList.get(0).getLastName(), teacherDto.getLastName());
        //assertEquals(teacherResultList.get(0).getRatings(), teacherDto.getRatings());
    }

    @Test
    void getTeacherByDirectionAndRatingIntegrationTest() throws Exception {
        String teacherNewString = objectMapper.writeValueAsString(teacherDto);

        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherDirAndRating/{direction}/{rating}", "ENGLISH", 7)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/getTeacherDirAndRating/{direction}/{rating}", "LOGIC", 12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherNewString))
                .andReturn();
        // assertEquals(404, mockNegativeResult.getResponse().getStatus());

        List<TeacherDto> teacherResultList = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(1, teacherResultList.size());
        assertEquals(teacherResultList.get(0).getFirstName(), teacherDto.getFirstName());
        assertEquals(teacherResultList.get(0).getLastName(), teacherDto.getLastName());
        //assertEquals(teacherResultList.get(0).getRatings(), teacherDto.getRatings());
    }
}