package eventmanager.product.deltaenable;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.ac.ui.cs.prices.winvmj.core.VMJCors;
import id.ac.ui.cs.prices.winvmj.core.VMJServer;
import id.ac.ui.cs.prices.winvmj.core.Router;
import id.ac.ui.cs.prices.winvmj.hibernate.HibernateUtil;
import org.hibernate.cfg.Configuration;



import id.ac.ui.cs.prices.winvmj.auth.model.UserResourceFactory;
import id.ac.ui.cs.prices.winvmj.auth.model.RoleResourceFactory;
import id.ac.ui.cs.prices.winvmj.auth.model.core.resource.UserResource;
import id.ac.ui.cs.prices.winvmj.auth.model.core.resource.RoleResource;

import Event.checkin.CheckInResourceFactory;
import Event.checkin.core.resource.CheckInResource;
import Event.checkin.CheckInServiceFactory;
import Event.checkin.core.service.CheckInService;
import Event.eventcreation.EventCreationResourceFactory;
import Event.eventcreation.core.resource.EventCreationResource;
import Event.eventcreation.EventCreationServiceFactory;
import Event.eventcreation.core.service.EventCreationService;
import Event.attendeemanagement.AttendeeManagementResourceFactory;
import Event.attendeemanagement.core.resource.AttendeeManagementResource;
import Event.attendeemanagement.AttendeeManagementServiceFactory;
import Event.attendeemanagement.core.service.AttendeeManagementService;
import Event.notification.NotificationResourceFactory;
import Event.notification.core.resource.NotificationResource;
import Event.notification.NotificationServiceFactory;
import Event.notification.core.service.NotificationService;
import Event.report.ReportResourceFactory;
import Event.report.core.resource.ReportResource;
import Event.report.ReportServiceFactory;
import Event.report.core.service.ReportService;
import Event.review.ReviewResourceFactory;
import Event.review.core.resource.ReviewResource;
import Event.review.ReviewServiceFactory;
import Event.review.core.service.ReviewService;

public class Deltaenable {

	private static final Logger logger;
	
	static {
		logger = LoggerFactory.getLogger(Deltaenable.class);
	}
    
	public static void main(String[] args) {



		// get hostAddress and portnum from env var
        // ex:
        // AMANAH_HOST_BE --> "localhost"
        // AMANAH_PORT_BE --> 7776
		String hostAddress= getEnvVariableHostAddress("AMANAH_HOST_BE");
        int portNum = getEnvVariablePortNumber("AMANAH_PORT_BE");
        activateServer(hostAddress, portNum);
		setCors();

		Configuration configuration = new Configuration();
		// panggil setter setelah membuat object dari kelas Configuration
        // ex:
        // AMANAH_DB_URL --> jdbc:postgresql://localhost:5432/superorg
        // AMANAH_DB_USERNAME --> postgres
        // AMANAH_DB_PASSWORD --> postgres123
		setDBProperties("AMANAH_DB_URL", "url", configuration);
        setDBProperties("AMANAH_DB_USERNAME", "username", configuration);
        setDBProperties("AMANAH_DB_PASSWORD","password", configuration);

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
		configuration.addAnnotatedClass(Event.eventcreation.typeeventcreation.model.EventCreationImpl.class);
		configuration.addAnnotatedClass(Event.eventcreation.typeeventcreation.model.EventType.class);
		configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagement.class);
		configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementComponent.class);
		configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementDecorator.class);
		configuration.addAnnotatedClass(Event.attendeemanagement.core.model.AttendeeManagementImpl.class);
		configuration.addAnnotatedClass(Event.attendeemanagement.classattendeemanagement.model.AttendeeManagementImpl.class);
		configuration.addAnnotatedClass(Event.notification.core.model.Notification.class);
		configuration.addAnnotatedClass(Event.notification.core.model.NotificationComponent.class);
		configuration.addAnnotatedClass(Event.notification.core.model.NotificationDecorator.class);
		configuration.addAnnotatedClass(Event.notification.core.model.NotificationImpl.class);
		configuration.addAnnotatedClass(Event.notification.targetednotification.model.NotificationImpl.class);
		configuration.addAnnotatedClass(Event.report.core.model.Report.class);
		configuration.addAnnotatedClass(Event.report.core.model.ReportComponent.class);
		configuration.addAnnotatedClass(Event.report.core.model.ReportDecorator.class);
		configuration.addAnnotatedClass(Event.report.core.model.ReportImpl.class);
		configuration.addAnnotatedClass(Event.report.priorityreport.model.ReportImpl.class);
		configuration.addAnnotatedClass(Event.review.core.model.Review.class);
		configuration.addAnnotatedClass(Event.review.core.model.ReviewComponent.class);
		configuration.addAnnotatedClass(Event.review.core.model.ReviewDecorator.class);
		configuration.addAnnotatedClass(Event.review.core.model.ReviewImpl.class);
		configuration.addAnnotatedClass(Event.review.reviewanonymous.model.ReviewImpl.class);

		Map<String, Object> featureModelMappings = mappingFeatureModel();
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Map<String, String[]>>>(){}.getType();
        String convertedFeatureModelMappings = gson.toJson(featureModelMappings, type);
		
        configuration.setProperty("feature.model.mappings", convertedFeatureModelMappings);
		configuration.buildMappings();
		// Try to initialize Hibernate - graceful failure if DB not available
		try {
			HibernateUtil.buildSessionFactory(configuration);


			createObjectsAndBindEndPoints();
		} catch (Exception e) {
			logger.warn("Database connection failed - server running but database features disabled");
			logger.debug("Database error: {}", e.getMessage());
		}
	}

	public static void activateServer(String hostName, int portNumber) {
		VMJServer vmjServer = VMJServer.getInstance(hostName, portNumber);
		try {
			vmjServer.startServerGeneric();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void createObjectsAndBindEndPoints() {
		logger.info("Creating objects and binding endpoints");
		UserResource userResource = UserResourceFactory
            .createUserResource("id.ac.ui.cs.prices.winvmj.auth.model.core.resource.UserResourceImpl"
			);

		RoleResource roleResource = RoleResourceFactory
        	.createRoleResource("id.ac.ui.cs.prices.winvmj.auth.model.core.resource.RoleResourceImpl"
			);
        
        UserResource userPasswordedResource = UserResourceFactory
	        .createUserResource("id.ac.ui.cs.prices.winvmj.auth.model.passworded.resource.UserResourceImpl"
			,
		    UserResourceFactory.createUserResource("id.ac.ui.cs.prices.winvmj.auth.model.core.resource.UserResourceImpl"));

        CheckInService checkinCheckIn2Service = CheckInServiceFactory
            .createCheckInService("Event.checkin.core.service.CheckInServiceImpl"
            	);		

        CheckInResource checkinCheckIn2Resource = CheckInResourceFactory
            .createCheckInResource("Event.checkin.core.resource.CheckInResourceImpl"
                );
			
        CheckInService timestampcheckinCheckIn2Service = CheckInServiceFactory
            .createCheckInService("Event.checkin.timestampcheckin.service.CheckInServiceImpl"
            	, checkinCheckIn2Service);		

        CheckInResource timestampcheckinCheckIn2Resource = CheckInResourceFactory
            .createCheckInResource("Event.checkin.timestampcheckin.resource.CheckInResourceImpl"
                , checkinCheckIn2Resource, checkinCheckIn2Service);
			
        EventCreationService eventcreationEventCreation2Service = EventCreationServiceFactory
            .createEventCreationService("Event.eventcreation.core.service.EventCreationServiceImpl"
            	);		

        EventCreationResource eventcreationEventCreation2Resource = EventCreationResourceFactory
            .createEventCreationResource("Event.eventcreation.core.resource.EventCreationResourceImpl"
                );
			
        EventCreationService typeeventcreationEventCreation2Service = EventCreationServiceFactory
            .createEventCreationService("Event.eventcreation.typeeventcreation.service.EventCreationServiceImpl"
            	, eventcreationEventCreation2Service);		

        EventCreationResource typeeventcreationEventCreation2Resource = EventCreationResourceFactory
            .createEventCreationResource("Event.eventcreation.typeeventcreation.resource.EventCreationResourceImpl"
                , eventcreationEventCreation2Resource, eventcreationEventCreation2Service);
			
        AttendeeManagementService attendeemanagementAttendeeManagement2Service = AttendeeManagementServiceFactory
            .createAttendeeManagementService("Event.attendeemanagement.core.service.AttendeeManagementServiceImpl"
            	);		

        AttendeeManagementResource attendeemanagementAttendeeManagement2Resource = AttendeeManagementResourceFactory
            .createAttendeeManagementResource("Event.attendeemanagement.core.resource.AttendeeManagementResourceImpl"
                );
			
        AttendeeManagementService classattendeemanagementAttendeeManagement2Service = AttendeeManagementServiceFactory
            .createAttendeeManagementService("Event.attendeemanagement.classattendeemanagement.service.AttendeeManagementServiceImpl"
            	, attendeemanagementAttendeeManagement2Service);		

        AttendeeManagementResource classattendeemanagementAttendeeManagement2Resource = AttendeeManagementResourceFactory
            .createAttendeeManagementResource("Event.attendeemanagement.classattendeemanagement.resource.AttendeeManagementResourceImpl"
                , attendeemanagementAttendeeManagement2Resource, attendeemanagementAttendeeManagement2Service);
			
        NotificationService notificationNotification2Service = NotificationServiceFactory
            .createNotificationService("Event.notification.core.service.NotificationServiceImpl"
            	);		

        NotificationResource notificationNotification2Resource = NotificationResourceFactory
            .createNotificationResource("Event.notification.core.resource.NotificationResourceImpl"
                );
			
        NotificationService targetednotificationNotification2Service = NotificationServiceFactory
            .createNotificationService("Event.notification.targetednotification.service.NotificationServiceImpl"
            	, notificationNotification2Service);		

        NotificationResource targetednotificationNotification2Resource = NotificationResourceFactory
            .createNotificationResource("Event.notification.targetednotification.resource.NotificationResourceImpl"
                , notificationNotification2Resource, notificationNotification2Service);
			
        ReportService reportReport2Service = ReportServiceFactory
            .createReportService("Event.report.core.service.ReportServiceImpl"
            	);		

        ReportResource reportReport2Resource = ReportResourceFactory
            .createReportResource("Event.report.core.resource.ReportResourceImpl"
                );
			
        ReportService priorityreportReport2Service = ReportServiceFactory
            .createReportService("Event.report.priorityreport.service.ReportServiceImpl"
            	, reportReport2Service);		

        ReportResource priorityreportReport2Resource = ReportResourceFactory
            .createReportResource("Event.report.priorityreport.resource.ReportResourceImpl"
                , reportReport2Resource, reportReport2Service);
			
        ReviewService reviewReview2Service = ReviewServiceFactory
            .createReviewService("Event.review.core.service.ReviewServiceImpl"
            	);		

        ReviewResource reviewReview2Resource = ReviewResourceFactory
            .createReviewResource("Event.review.core.resource.ReviewResourceImpl"
                );
			
        ReviewService reviewanonymousReview2Service = ReviewServiceFactory
            .createReviewService("Event.review.reviewanonymous.service.ReviewServiceImpl"
            	, reviewReview2Service);		

        ReviewResource reviewanonymousReview2Resource = ReviewResourceFactory
            .createReviewResource("Event.review.reviewanonymous.resource.ReviewResourceImpl"
                , reviewReview2Resource, reviewReview2Service);
			

		logger.info("Binding endpoints for reviewanonymousReview2Resource");
		Router.route(reviewanonymousReview2Resource);
		
		logger.info("Binding endpoints for reviewanonymousReview2Service");
		Router.route(reviewanonymousReview2Service);
		
		logger.info("Binding endpoints for reviewReview2Resource");
		Router.route(reviewReview2Resource);
		
		logger.info("Binding endpoints for reviewReview2Service");
		Router.route(reviewReview2Service);
		
		logger.info("Binding endpoints for priorityreportReport2Resource");
		Router.route(priorityreportReport2Resource);
		
		logger.info("Binding endpoints for priorityreportReport2Service");
		Router.route(priorityreportReport2Service);
		
		logger.info("Binding endpoints for reportReport2Resource");
		Router.route(reportReport2Resource);
		
		logger.info("Binding endpoints for reportReport2Service");
		Router.route(reportReport2Service);
		
		logger.info("Binding endpoints for targetednotificationNotification2Resource");
		Router.route(targetednotificationNotification2Resource);
		
		logger.info("Binding endpoints for targetednotificationNotification2Service");
		Router.route(targetednotificationNotification2Service);
		
		logger.info("Binding endpoints for notificationNotification2Resource");
		Router.route(notificationNotification2Resource);
		
		logger.info("Binding endpoints for notificationNotification2Service");
		Router.route(notificationNotification2Service);
		
		logger.info("Binding endpoints for classattendeemanagementAttendeeManagement2Resource");
		Router.route(classattendeemanagementAttendeeManagement2Resource);
		
		logger.info("Binding endpoints for classattendeemanagementAttendeeManagement2Service");
		Router.route(classattendeemanagementAttendeeManagement2Service);
		
		logger.info("Binding endpoints for attendeemanagementAttendeeManagement2Resource");
		Router.route(attendeemanagementAttendeeManagement2Resource);
		
		logger.info("Binding endpoints for attendeemanagementAttendeeManagement2Service");
		Router.route(attendeemanagementAttendeeManagement2Service);
		
		logger.info("Binding endpoints for typeeventcreationEventCreation2Resource");
		Router.route(typeeventcreationEventCreation2Resource);
		
		logger.info("Binding endpoints for typeeventcreationEventCreation2Service");
		Router.route(typeeventcreationEventCreation2Service);
		
		logger.info("Binding endpoints for eventcreationEventCreation2Resource");
		Router.route(eventcreationEventCreation2Resource);
		
		logger.info("Binding endpoints for eventcreationEventCreation2Service");
		Router.route(eventcreationEventCreation2Service);
		
		logger.info("Binding endpoints for timestampcheckinCheckIn2Resource");
		Router.route(timestampcheckinCheckIn2Resource);
		
		logger.info("Binding endpoints for timestampcheckinCheckIn2Service");
		Router.route(timestampcheckinCheckIn2Service);
		
		logger.info("Binding endpoints for checkinCheckIn2Resource");
		Router.route(checkinCheckIn2Resource);
		
		logger.info("Binding endpoints for checkinCheckIn2Service");
		Router.route(checkinCheckIn2Service);
		
		logger.info("Binding auth endpoints");
		Router.route(userPasswordedResource);
		Router.route(roleResource);
		Router.route(userResource);
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
			}});
		featureModelMappings.put(
            Event.eventcreation.core.model.EventCreationComponent.class.getName(),
			new HashMap<String, String[]>() {{ 
				put("components", new String[] {
					Event.eventcreation.core.model.EventCreationComponent.class.getName()
				});
				put("deltas", new String[] {
					Event.eventcreation.typeeventcreation.model.EventCreationImpl.class.getName()
				});
			}});
		featureModelMappings.put(
            Event.attendeemanagement.core.model.AttendeeManagementComponent.class.getName(),
			new HashMap<String, String[]>() {{ 
				put("components", new String[] {
					Event.attendeemanagement.core.model.AttendeeManagementComponent.class.getName()
				});
				put("deltas", new String[] {
					Event.attendeemanagement.classattendeemanagement.model.AttendeeManagementImpl.class.getName()
				});
			}});
		featureModelMappings.put(
            Event.notification.core.model.NotificationComponent.class.getName(),
			new HashMap<String, String[]>() {{ 
				put("components", new String[] {
					Event.notification.core.model.NotificationComponent.class.getName()
				});
				put("deltas", new String[] {
					Event.notification.targetednotification.model.NotificationImpl.class.getName()
				});
			}});
		featureModelMappings.put(
            Event.report.core.model.ReportComponent.class.getName(),
			new HashMap<String, String[]>() {{ 
				put("components", new String[] {
					Event.report.core.model.ReportComponent.class.getName()
				});
				put("deltas", new String[] {
					Event.report.priorityreport.model.ReportImpl.class.getName()
				});
			}});
		featureModelMappings.put(
            Event.review.core.model.ReviewComponent.class.getName(),
			new HashMap<String, String[]>() {{ 
				put("components", new String[] {
					Event.review.core.model.ReviewComponent.class.getName()
				});
				put("deltas", new String[] {
					Event.review.reviewanonymous.model.ReviewImpl.class.getName()
				});
			}});
		featureModelMappings.put(
	            id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserComponent.class.getName(),
				new HashMap<String, String[]>() {{ 
					put("components", new String[] {
						id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserComponent.class.getName()
					});
					put("deltas", new String[] {
						id.ac.ui.cs.prices.winvmj.auth.model.passworded.model.UserImpl.class.getName()
					});
				}});
        
	    featureModelMappings.put(
				id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleComponent.class.getName(),
				new HashMap<String, String[]>() {{ 
					put("components", new String[] {
						id.ac.ui.cs.prices.winvmj.auth.model.core.model.RoleComponent.class.getName()
					});
					put("deltas", new String[] {
					});
				}});
        
	    featureModelMappings.put(
				id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleComponent.class.getName(),
				new HashMap<String, String[]>() {{ 
					put("components", new String[] {
						id.ac.ui.cs.prices.winvmj.auth.model.core.model.UserRoleComponent.class.getName()
					});
					put("deltas", new String[] {
					});
				}});
        
		return featureModelMappings;
	}

	public static void setDBProperties(String varname, String typeProp, Configuration configuration) {
		String varNameValue = System.getenv(varname);
		String propertyName = String.format("hibernate.connection.%s",typeProp);
		if (varNameValue != null) {
			configuration.setProperty(propertyName, varNameValue);
		} else {
			String hibernatePropertyVal = configuration.getProperty(propertyName);
			if (hibernatePropertyVal == null) {
				logger.warn("Please check '{}' in your local environment variable or 'hibernate.connection.{}' in your 'hibernate.properties' file!", varname, typeProp);
			}
		}
	}

	// if the env variable for server host is null, use localhost instead.
    public static String getEnvVariableHostAddress(String varname_host){
            String hostAddress = System.getenv(varname_host)  != null ? System.getenv(varname_host) : "localhost"; // Host
            return hostAddress;
    }

    // try if the environment variable for port number is null, use 7776 instead
    public static int getEnvVariablePortNumber(String varname_port){
            String portNum = System.getenv(varname_port)  != null? System.getenv(varname_port)  : "7776"; //PORT
            int portNumInt = Integer.parseInt(portNum);
            return portNumInt;
    }
	
	public static void setCors() {
    	Properties properties = new Properties();
        String propertyValue = "";
        
        try (FileInputStream fileInput = new FileInputStream("cors.properties")) {
            properties.load(fileInput);
            propertyValue = properties.getProperty("allowedMethod");
            VMJCors.setAllowedMethod(propertyValue);
            
            propertyValue = properties.getProperty("allowedOrigin");
            VMJCors.setAllowedOrigin(propertyValue);
            
        		} catch (IOException e) {
			VMJCors.setAllowedMethod("GET, POST, PUT, PATCH, DELETE");
			VMJCors.setAllowedOrigin("*");
			logger.info("cors.properties not found, using defaults (allowedMethod=GET,POST,PUT,PATCH,DELETE, allowedOrigin=*)");
        }
    }

}