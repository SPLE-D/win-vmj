module Event.checkin.manualcheckin {
	requires Event.checkin.core;
	exports Event.checkin.manualcheckin.model;
	exports Event.checkin.manualcheckin.resource;
	exports Event.checkin.manualcheckin.service;

	requires id.ac.ui.cs.prices.winvmj.core;
	requires id.ac.ui.cs.prices.winvmj.hibernate;
	requires id.ac.ui.cs.prices.winvmj.auth;
	requires java.logging;
	// https://stackoverflow.com/questions/46488346/error32-13-error-cannot-access-referenceable-class-file-for-javax-naming-re/50568217
	requires java.naming;
	requires java.net.http;

	opens Event.checkin.manualcheckin.model to org.hibernate.orm.core, gson, id.ac.ui.cs.prices.winvmj.hibernate;
}
