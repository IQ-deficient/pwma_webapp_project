-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, password, dateofbirth) values (0, 'Vladimir Gazivoda', 'gazivodavladimir@gmail.com', 'Chad23xD', TO_DATE('19/10/1998', 'DD/MM/YYYY')) 
insert into Item (id, name, price, member_id) values (0, 'Rare artifact', 225.5, 0)
insert into Item (id, name, price) values (1, 'Flickering cinders', 442)
--insert into Item (id, name, price) values (3, 'Wings for Marie', 10000)
insert into Item (id, name, price, member_id) values (2, 'Anomaly', 5, 0)
--insert into Item (id, name, price) values (4, 'Crazy Diamond', 2300.23)
insert into Manufacturer (id, name) values (0, 'Propagand')
insert into Item_Manufacturer (id, temp, item_id, manufacturer_id) values (0, 'someData', 0, 0)
