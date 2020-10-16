CREATE TABLE animal ( 
id INTEGER, 
name TEXT, 
kind TEXT, 
price INTEGER, 

PRIMARY KEY (id) 
); 
CREATE SEQUENCE hibernate_sequence START 1; 
SELECT setval('hibernate_sequence', (SELECT max(id) FROM public.animal));
