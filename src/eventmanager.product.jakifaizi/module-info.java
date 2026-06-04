module eventmanager.product.jakifaizi {
    requires id.ac.ui.cs.prices.winvmj.auth;
    requires id.ac.ui.cs.prices.winvmj.auth.model;
    requires id.ac.ui.cs.prices.winvmj.core;
    requires id.ac.ui.cs.prices.winvmj.hibernate;
    requires org.slf4j;
    
    requires net.bytebuddy;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;
    requires jdk.unsupported;

    requires Event.checkin.core;
    requires Event.checkin.timestampcheckin;
    requires Event.eventcreation.core;
    requires Event.attendeemanagement.core;
    requires Event.report.core;
    requires Event.review.core;


}