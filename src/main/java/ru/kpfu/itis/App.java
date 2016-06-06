package ru.kpfu.itis;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
//import org.gmr.web.multipart.GMultipartResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("ru.kpfu.itis")

public class App extends WebMvcAutoConfiguration {

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    DatastoreService datastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    UserService userService() {
        return UserServiceFactory.getUserService();
    }

    //@Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
    //MultipartResolver multipartResolver(@Value("${multipart.maxFileSize:1048576}") int maxUploadSize) {
    //    GMultipartResolver multipartResolver = new GMultipartResolver();
    //    multipartResolver.setMaxUploadSize(maxUploadSize);
    //    return multipartResolver;
    //}

    //@Bean
    //@Primary
    //public DataSource dataSource() {
    //    return DataSourceBuilder
    //            .create()
    //            .username("root")
    //            .password("tatarstan")
    //            .url("jdbc:mysql://localhost/springbootgae?user=root&amp;PASSWORD=tatarstan&amp;useSSL=false")
                //.url("jdbc:google:rdbms:INSTANCE_NAME/DATABASE_NAME")
                //.driverClassName("com.google.appengine.api.rdbms.AppEngineDriver")
    //            .driverClassName("com.mysql.jdbc.Driver")
    //            .build();

        //jdbc.driverClassName=com.mysql.jdbc.GoogleDriver
        //jdbc.url=jdbc:google:mysql://my-app-id:my-instance/my_table?user=user
        //jdbc.username=user
        //jdbc.password=user
   // }
}