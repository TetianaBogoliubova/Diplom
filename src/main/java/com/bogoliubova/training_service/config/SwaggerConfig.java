package com.bogoliubova.training_service.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//
//    // Этот метод в классе SwaggerConfig представляет собой конфигурацию Swagger для группировки определенных путей в документации
//    @Bean
//    public GroupedOpenApi teacherOfTrainingServiceApi() {//Этот метод создает и возвращает объект типа GroupedOpenApi
//        return GroupedOpenApi.builder()//Метод использует билдер GroupedOpenApi.builder() для настройки группы.
//                .group("teacher_of_training-service")//Устанавливает имя группы. В данном случае, группа названа teacher_of_training-service
//                .pathsToMatch("/teacher/**", "/rating/**", "/typeOfLearning/**")//Указывает пути, которые должны быть включены в данную группу. В данном случае, группа включает пути, начинающиеся с "/teacher/", "/rating/" и "/typeOfLearning/".
//                .build();//Завершает конфигурацию и создает объект GroupedOpenApi с указанными параметрами.
//    }
//
//    @Bean
//    public GroupedOpenApi booksOfTrainingServiceApi() {
//        return GroupedOpenApi.builder()
//                .group("books_of_training-service")
//                .pathsToMatch("/book/**", "/direction/**")
//                .build();
//    }
//
//    //Этот метод создает и возвращает объект типа OpenAPI. Этот метод, таким образом, предоставляет общую конфигурацию
//    // для Swagger, включая информацию о вашем API и сервере. Данные параметры можно легко настраивать во внешних
//    // конфигурационных файлах вашего приложения, что обеспечивает гибкость в настройке Swagger для различных сред и
//    // этапов разработки.
//    @Bean
//    public OpenAPI teacherOpenApi(@Value("${app.description}") String appDescription,
//                                  @Value("${app.version}") String appVersion,
//                                  @Value("${app.server.url}") String serverUrl,
//                                  @Value("${app.server.stage}") String stage) {
//        return new OpenAPI()
//                .info(new Info().title("Info service API")
//                        .version(appVersion)
//                        .description(appDescription))
//                .servers(List.of(new Server()
//                        .url(serverUrl)
//                        .description(stage)));
//        //.servers(Collections.singletonList(new Server().url(serverUrl).description(stage)));
//        //.addServersItem(new Server().url(serverUrl).description(stage));
//
//
//    }
//}
