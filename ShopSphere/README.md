# ShopSphere — RTU Advanced Java Project

Java 17+, Maven, **Tomcat 11**, Jakarta Servlets 6.1, JSP/JSTL 3, MySQL 8+, JDBC and BCrypt. This is a functional **core e-commerce student project**, accompanied by educational Swing, Socket and RMI modules.

## Prerequisites

1. Install JDK 17 or 21, Apache Maven, MySQL 8+, Apache Tomcat 11.
2. Import `database/shopsphere.sql` using `mysql -u root -p < database/shopsphere.sql`.
3. Configure environment variables `SHOPSPHERE_DB_URL` (defaults to `jdbc:mysql://localhost:3306/shopsphere?useSSL=false&allowPublicKeyRetrieval=true`), `SHOPSPHERE_DB_USER` (defaults to `root`), `SHOPSPHERE_DB_PASSWORD` (defaults to empty). Set them in the environment running Tomcat.
4. Run `mvn clean package` from project root, copy `target/shopsphere.war` to Tomcat 11 `webapps/` and start Tomcat.
5. Open `http://localhost:8080/shopsphere/` (or `/shopsphere/products`).

### First admin account

Register a customer via the UI, then *locally* promote that account using MySQL:

```sql
USE shopsphere;
UPDATE users SET role = 'ADMIN' WHERE email = 'your-email@example.com';
```

Sign out and back in, then go to `/shopsphere/admin`. **Do not expose this admin promotion SQL to public application users.** No admin password is seeded.

### Included functionality

- Customer registration, BCrypt password checks, session login/logout
- Product catalog, search, product detail pages, wishlist and reviews
- Database-backed cart and stock-aware **transactional** cash-on-delivery checkout
- User order history and admin product CRUD (deactivation), categories, order status, customer list
- JSP with EL, JSTL, fragment includes and a custom reusable tag file
- Auth filter, CSRF token filter, context/session listener
- Responsive CSS shop and desktop admin starter
- SQL schema covering users, addresses, categories, products, cart, wishlist, orders, order items, payments, reviews, coupons, coupon usage

### Supplementary modules

These modules are independent Java learning examples and **not wired into the web request flow**.

- `network/src/`: read-only inventory Socket server/client (`ServerSocket` / `Socket`). Requires JDBC driver classpath at runtime.
- `rmi/src/`: inventory remote interface/server/client and registry; requires MySQL Connector/J runtime. **Educational only:** no authentication on RMI updateStock; bind to trusted local networks only.
- `swing-admin/src/`: Swing product inventory dashboard; requires JDBC driver and BCrypt dependencies at compile/runtime. Uses valid ADMIN email + password.
- `extras/`: `Locale`, `ResourceBundle`, URL/URLConnection and optional JNDI lookup demo.

Compile auxiliary module code by including the Maven-downloaded dependency JARs in the classpath. Their entry points are `com.shopsphere.network.InventoryServer`, `...InventoryClient`, `com.shopsphere.rmi.InventoryServer`, `...InventoryClient`, and `com.shopsphere.swing.AdminDesktop`.

### Important limitations

- Checkout is **Cash on Delivery only**; no actual payment gateway or settlement. Customer-entered address is created during checkout.
- Coupons, wishlist removal, review moderation, advanced search filters, cart counter, sales summary, admin customer editing and applets are **not implemented** in the web app. Additional syllabus JSTL XML/SQL demonstrations are not included.
- The Swing admin module is a product inventory screen, not the full desktop admin suite. RMI, Socket and JNDI demos are separate from the core.
- No automated integration tests executed against a real Tomcat/MySQL environment. Do not represent this as production-ready; use TLS, database least-privilege credentials, input limits, security review, RMI access restrictions and proper deployment configuration first.
- Externally-hosted Unsplash sample images and Google Fonts require network connectivity. UI works without them but may lose images/fonts.
- **Potential behavior:** The stored cart quantity can overwrite an earlier quantity when a user adds the same product again rather than incrementing it.

### Manual functional test plan

1. Register with valid data; duplicate email should be rejected.
2. Login with correct/wrong password; verify access controls.
3. Browse/search/view products.
4. Add product to cart, update quantity, remove it.
5. Place COD order; verify stock decrement, order_items, payment row and cart clearing.
6. Confirm order listed for buyer only.
7. Admin creates product, changes stock, deactivates it, changes order status.
8. Verify customers receive 403 for `/admin`; guest redirected to login for `/cart`.
9. Check CSRF failure for POST without token.
10. Run networking and RMI demonstrations against a local DB.

## Architecture

Browser → JSP (`WEB-INF/views`) → ShopServlet/AdminServlet → DAO/CheckoutService → JDBC → MySQL

Additional: Swing → JDBC/MySQL; Socket server → JDBC/MySQL; RMI server → JDBC/MySQL.

See `PROJECT_STATUS.md` for implementation status and recommended next steps.
