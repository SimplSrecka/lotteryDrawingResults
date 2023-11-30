package si.fri.rso.simplsrecka.lotterydrawingresults.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

//@OpenAPIDefinition(info = @Info(title = "Lottery drawings and results API", version = "v1",
//        contact = @Contact(email = "lb4684@student.uni-lj.si"),
//        license = @License(name = "dev"), description = "API for managing image metadata."),
//        servers = @Server(url = "http://localhost:8080/"))
@OpenAPIDefinition(info = @Info(title = "Lottery drawings and results API", version = "v1",
        contact = @Contact(email = "lb4684@student.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing image metadata."),
        servers = @Server(url = "https://20.253.63.208:8080/"))
@ApplicationPath("/v1")
public class LotteryDrawingResultsApplication extends Application {

}
