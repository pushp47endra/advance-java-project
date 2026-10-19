# Build status and handoff

## Implemented
Java Maven WAR structure, complete 12-table MySQL schema and seed catalog, web catalog/search/details, login/registration/logout, wishlist add/list, product review submission, cart quantities/remove, transactional COD checkout, order history, admin product insert/update/deactivation, category add, order-status update and user listing. CSRF/auth filters, session tracking and reusable JSP tag / fragments. Responsive styling. Separate examples for Swing inventory dashboard, Socket client/server, RMI service/registry, internationalization, JNDI and URLConnection.

## Not implemented or incomplete
Coupon application and usage; payment gateway; order tracking timeline/details; review listing/moderation; comprehensive advanced filters/sorting; profile/address management; extensive Swing screens; integration of networking and RMI into main services; JSTL XML and SQL demo pages; applet historical demo; full functional and integration automated tests; UML diagrams and formal final student report. Discount can be seeded in SQL but is not edited from admin panel.

## Build verification
Maven executable not present in construction environment; WAR build not run. Supplemental Java code can be syntax-checked with javac. MySQL/Tomcat connection not tested. No claim of full end-to-end testing.

## Next steps
1. Import SQL into local MySQL; configure Tomcat DB env vars.
2. Run `mvn clean package`, address any dependency/version/compilation/JSP compatibility issues, deploy WAR to Tomcat 11.
3. Execute README manual test plan; validate checkout concurrency and session security.
4. Finish remaining syllabus/demo modules and complete report and screenshots.
