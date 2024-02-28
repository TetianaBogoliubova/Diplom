package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherMapperImplTest {

    @InjectMocks
    private final TeacherMapperImpl teacherMapper = new TeacherMapperImpl();

    @Mock
    private TeacherDto teacherDto;
    private Teacher teacher;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        teacherDto = new TeacherDto();
        teacher = new Teacher();

        when(teacherDto.getFirstName()).thenReturn("Ulysses");
        when(teacherDto.getLastName()).thenReturn("Runte");
        when(teacherDto.getTeachEmail()).thenReturn("monroe.hilpert@yahoo.com");
    }

    @Test
    void toEntity() {
        when(teacherDto.getTeachEmail()).thenReturn("monroe.hilpert@yahoo.com");
        Teacher expectedTeacher = teacherMapper.toEntity(teacherDto);

        assertEquals("MONROE.HILPERT@YAHOO.COM", expectedTeacher.getTeachEmail());
    }

    @Test
    void toDtoListPositiveTest() {
        Teacher teacher1 = new Teacher();
        teacher1.setTeachEmail("email1.example.com");

        Teacher teacher2 = new Teacher();
        teacher2.setTeachEmail("email2.example.com");

        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);

        List<TeacherDto> expectedListDto = teacherMapper.toDtoList(teachers);

        Assertions.assertNotNull(expectedListDto);
        Assertions.assertFalse(expectedListDto.isEmpty());
        assertEquals(2, teachers.size());

        for (int i = 0; i < teachers.size(); i++) {
            teacher = teachers.get(i);
            teacherDto = expectedListDto.get(i);
        }
        assertEquals(teacher.getTeachEmail(), teacherDto.getTeachEmail());
    }
}



