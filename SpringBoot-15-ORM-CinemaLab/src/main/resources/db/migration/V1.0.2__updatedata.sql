INSERT INTO location (name, address, postal_code, country, state, city) VALUES
                                                                            ('AMC Empire 25', '234 W 42nd St', '10036', 'United States', 'New York', 'New York'),
                                                                            ('AMC 34th Street 14', '312 W 34th St', '10001', 'United States', 'New York', 'New York'),
                                                                            ('AMC Lincoln Square 13', '1998 Broadway', '10023', 'United States', 'New York', 'New York'),
                                                                            ('AMC Village 7', '66 3rd Ave', '10003', 'United States', 'New York', 'New York');


-- IF YOU WANT TO ADD NEW DATA ON TOP OF EXISTING TABLE USING flyway DEPENDENCY, THEN EACH TIME YOU HAVE TO CREATE NEW SQL FILE IN resources>db>migration.
-- -- TODO LIKE V1.0.2__update.sql
-- SO TABLE WILL UPDATED WITH NEW VERSION OF DATA. TO ADD DATA WITHOUT FLYWAY dependency, just go to resources and add your SQL file.

