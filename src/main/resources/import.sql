insert into tab_state (name) values ("AC");
insert into tab_state (name) values ("AL");
insert into tab_state (name) values ("AP");
insert into tab_state (name) values ("AM");
insert into tab_state (name) values ("BA");
insert into tab_state (name) values ("CE");
insert into tab_state (name) values ("ES");
insert into tab_state (name) values ("GO");
insert into tab_state (name) values ("MA");
insert into tab_state (name) values ("MT");
insert into tab_state (name) values ("MS");
insert into tab_state (name) values ("MG");
insert into tab_state (name) values ("PA");
insert into tab_state (name) values ("PB");
insert into tab_state (name) values ("PR");
insert into tab_state (name) values ("PE");
insert into tab_state (name) values ("PI");
insert into tab_state (name) values ("RJ");
insert into tab_state (name) values ("RN");
insert into tab_state (name) values ("RS");
insert into tab_state (name) values ("RO");
insert into tab_state (name) values ("RR");
insert into tab_state (name) values ("SC");
insert into tab_state (name) values ("SP");
insert into tab_state (name) values ("SE");
insert into tab_state (name) values ("TO");
insert into tab_state (name) values ("DF");

insert into tab_kitchen (id, name) values (1, "Tailandesa");
insert into tab_kitchen (id, name) values (2, "Italiana");


insert into tab_city (name, state_id) values ('Uberlândia', 12);
insert into tab_city (name, state_id) values ('Belo Horizonte', 12);
insert into tab_city (name, state_id) values ('São Paulo', 24);
insert into tab_city (name, state_id) values ('Campinas', 24);
insert into tab_city (name, state_id) values ('Fortaleza', 6);
insert into tab_city (name, state_id) values ("Goiania", 8);

insert into tab_restaurant (name, shipping_price, kitchen_id, registration_date, update_date, address_city_id, address_cep, address_street, address_number, address_neighborhood) values ('Thai Gourmet', 10.50, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tab_restaurant (name, shipping_price, kitchen_id, registration_date, update_date) values ("Thai Delivery", 9.50, 1, utc_timestamp, utc_timestamp);
insert into tab_restaurant (name, shipping_price, kitchen_id, registration_date, update_date) values ("Tuk Delivery", 15, 2, utc_timestamp, utc_timestamp);


insert into tab_payment_methods (id, description) values (1, "Cartão de Crédito");
insert into tab_payment_methods (id, description) values (2, "Cartão de Débito");
insert into tab_payment_methods (id, description) values (3, "Dinheiro");

insert into restaurants_payment_methods (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
