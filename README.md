# Vue Crud Contacts Manager Example Application
This application is an example build of creating CRUD pages using vue and accessing data via a spring boot api.

# Install frontend via npm.
    > cd frontend-crud
    > npm install

# Run
    > npm run dev
    
    
Navigation to `http://localhost:9000` to view the site.
    
# Start API
The api is developed in spring boot so you will need maven and java 8+ to run it.

    > cd marinerwebtest
    > mvn spring-boot:run
    
Spring boot should be running at `localhost:8080/api/contacts`

# Data Persistence

Spring boot is utilizing an in memory H2 database. You can verify that things are being save there by using the H2 console at
`localhost:8080/console`. Make sure that your JDBC url is set to `jdbc:h2:mem:testdb` username: sa pass: <blank>.
The app does prepopulate some test data on startup to ease with testing.

  