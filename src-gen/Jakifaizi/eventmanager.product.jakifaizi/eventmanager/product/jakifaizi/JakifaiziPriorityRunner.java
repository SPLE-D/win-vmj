package eventmanager.product.jakifaizi;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Event.report.ReportResourceFactory;
import Event.report.ReportServiceFactory;
import Event.report.core.resource.ReportResource;
import Event.report.core.resource.ReportResourceComponent;
import Event.report.core.service.ReportService;
import Event.report.core.service.ReportServiceComponent;
import id.ac.ui.cs.prices.winvmj.core.Router;
import id.ac.ui.cs.prices.winvmj.hibernate.HibernateUtil;

public class JakifaiziPriorityRunner {
    private static final Logger logger = LoggerFactory.getLogger(JakifaiziPriorityRunner.class);

    public static void main(String[] args) {
        String hostAddress = Jakifaizi.getEnvVariableHostAddress("AMANAH_HOST_BE");
        int portNum = Jakifaizi.getEnvVariablePortNumber("AMANAH_PORT_BE");
        Jakifaizi.activateServer(hostAddress, portNum);
        Jakifaizi.setCors();

        Configuration configuration = new Configuration();
        Jakifaizi.setDBProperties("AMANAH_DB_URL", "url", configuration);
        Jakifaizi.setDBProperties("AMANAH_DB_USERNAME", "username", configuration);
        Jakifaizi.setDBProperties("AMANAH_DB_PASSWORD", "password", configuration);

        addAnnotatedClasses(configuration);
        configuration.setProperty("feature.model.mappings", toJson(mappingFeatureModel()));
        configuration.buildMappings();

        try {
            HibernateUtil.buildSessionFactory(configuration);
            Jakifaizi.createObjectsAndBindEndPoints();
            bindPriorityReportEndpoints();
        } catch (Exception e) {
            logger.warn("Database connection failed - server running but database features disabled");
            logger.debug("Database error: {}", e.getMessage());
        }
    }

    private static void addAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserComponent.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserDecorator.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserImpl.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleComponent.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleDecorator.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleImpl.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleComponent.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleDecorator.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleImpl.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.core.model.User.class);
        configuration.addAnnotatedClass(id.ac.ui.cs.prices.winvmj.auth.model.passworded.model.UserImpl.class);

        configuration.addAnnotatedClass(Event.checkin.core.model.CheckIn.class);
        configuration.addAnnotatedClass(Event.checkin.core.model.CheckInComponent.class);
        configuration.addAnnotatedClass(Event.checkin.core.model.CheckInDecorator.class);
        configuration.addAnnotatedClass(Event.checkin.core.model.CheckInImpl.class);
        configuration.addAnnotatedClass(Event.checkin.timestampcheckin.model.CheckInImpl.class);
        configuration.addAnnotatedClass(Event.eventcreation.core.model.EventCreation.class);
        configuration.addAnnotatedClass(Event.eventcreation.core.model.EventCreationComponent.class);
        configuration.addAnnotatedClass(Event.eventcreation.core.model.EventCreationDecorator.class);
        configuration.addAnnotatedClass(Event.eventcreation.core.model.EventCreationImpl.class);
        configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagement.class);
        configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementComponent.class);
        configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementDecorator.class);
        configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementImpl.class);
        configuration.addAnnotatedClass(Event.report.core.model.Report.class);
        configuration.addAnnotatedClass(Event.report.core.model.ReportComponent.class);
        configuration.addAnnotatedClass(Event.report.core.model.ReportDecorator.class);
        configuration.addAnnotatedClass(Event.report.core.model.ReportImpl.class);
        configuration.addAnnotatedClass(Event.report.priorityreport.model.ReportImpl.class);
        configuration.addAnnotatedClass(Event.review.core.model.Review.class);
        configuration.addAnnotatedClass(Event.review.core.model.ReviewComponent.class);
        configuration.addAnnotatedClass(Event.review.core.model.ReviewDecorator.class);
        configuration.addAnnotatedClass(Event.review.core.model.ReviewImpl.class);
    }

    private static void bindPriorityReportEndpoints() {
        ReportService reportReport2Service = ReportServiceFactory
            .createReportService("Event.report.core.service.ReportServiceImpl");

        ReportResource reportReport2Resource = ReportResourceFactory
            .createReportResource("Event.report.core.resource.ReportResourceImpl");

        ReportService priorityreportReport2Service = ReportServiceFactory
            .createReportService(
                "Event.report.priorityreport.service.ReportServiceImpl",
                (ReportServiceComponent) reportReport2Service
            );

        ReportResource priorityreportReport2Resource = ReportResourceFactory
            .createReportResource(
                "Event.report.priorityreport.resource.ReportResourceImpl",
                (ReportResourceComponent) reportReport2Resource,
                (ReportServiceComponent) reportReport2Service
            );

        logger.info("Binding endpoints for priorityreportReport2Resource");
        Router.route(priorityreportReport2Resource);

        logger.info("Binding endpoints for priorityreportReport2Service");
        Router.route(priorityreportReport2Service);
    }

    private static String toJson(Map<String, Object> featureModelMappings) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Map<String, String[]>>>() {}.getType();
        return gson.toJson(featureModelMappings, type);
    }

    private static Map<String, Object> mappingFeatureModel() {
        Map<String, Object> featureModelMappings = new HashMap<>();

        featureModelMappings.put(
            Event.checkin.core.model.CheckInComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    Event.checkin.core.model.CheckInComponent.class.getName()
                });
                put("deltas", new String[] {
                    Event.checkin.timestampcheckin.model.CheckInImpl.class.getName()
                });
            }}
        );

        featureModelMappings.put(
            Event.eventcreation.core.model.EventCreationComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    Event.eventcreation.core.model.EventCreationComponent.class.getName()
                });
                put("deltas", new String[] {});
            }}
        );

        featureModelMappings.put(
            Event.attendeemanagement.core.model.AttendeeManagementComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    Event.attendeemanagement.core.model.AttendeeManagementComponent.class.getName()
                });
                put("deltas", new String[] {});
            }}
        );

        featureModelMappings.put(
            Event.report.core.model.ReportComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    Event.report.core.model.ReportComponent.class.getName()
                });
                put("deltas", new String[] {
                    Event.report.priorityreport.model.ReportImpl.class.getName()
                });
            }}
        );

        featureModelMappings.put(
            Event.review.core.model.ReviewComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    Event.review.core.model.ReviewComponent.class.getName()
                });
                put("deltas", new String[] {});
            }}
        );

        featureModelMappings.put(
            id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserComponent.class.getName()
                });
                put("deltas", new String[] {
                    id.ac.ui.cs.prices.winvmj.auth.model.passworded.model.UserImpl.class.getName()
                });
            }}
        );

        featureModelMappings.put(
            id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleComponent.class.getName()
                });
                put("deltas", new String[] {});
            }}
        );

        featureModelMappings.put(
            id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleComponent.class.getName(),
            new HashMap<String, String[]>() {{
                put("components", new String[] {
                    id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleComponent.class.getName()
                });
                put("deltas", new String[] {});
            }}
        );

        return featureModelMappings;
    }
}
