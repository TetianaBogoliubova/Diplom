insert into directions (direction_id, d_title, grading, teacher_id, service_id, book_id, customer_id)
values ('11111111-e11a-1cd1-f111-111111111111', 'MATHEMATICS', 'JUNIOR_SCHOOL', '22222222-e22a-2cd2-f22-222222222222',
        '44444444-e44a-4cd4-f444-444444444444', '55555555-e55a-5cd5-f555-555555555555',
        '33333333-e33a-3cd3-f333-333333333333'),
       ('11111112-e11a-1cd1-f111-111111111111', 'ENGLISH', 'KINDERGARTEN', '22222223-e22a-2cd2-f22-222222222222',
        '44444445-e44a-4cd4-f444-444444444444', '55555556-e55a-5cd5-f555-555555555555',
        '33333334-e33a-3cd3-f333-333333333333');

insert into books (book_id, b_title, author, b_price, direction_id, service_id)
values ('55555555-e55a-5cd5-f555-555555555555', 'The House of Mirth', 'Rocco Hermann', 20.00,
        '11111111-e11a-1cd1-f111-111111111111', '44444444-e44a-4cd4-f444-444444444444'),
       ('55555556-e55a-5cd5-f555-555555555555', 'An Instant In The Wind', 'Emily Gottlieb', 25.00,
        '11111112-e11a-1cd1-f111-111111111111', '44444445-e44a-4cd4-f444-444444444444');

insert into services (service_id, type, s_price, direction_id, book_id)
values ('44444444-e44a-4cd4-f444-444444444444', 'PREPARATION_FOR_SCHOOL', 20.00, '11111111-e11a-1cd1-f111-111111111111',
        '55555555-e55a-5cd5-f555-555555555555'),
       ('44444445-e44a-4cd4-f444-444444444444', 'SCHOOL_PROGRAM', 30.00, '11111112-e11a-1cd1-f111-111111111111',
        '55555556-e55a-5cd5-f555-555555555555');

insert into types_of_learning (type_id, learning_types, special_price, teacher_id)
values ('66666666-e66a-6cd6-f666-666666666666', 'ONLINE', 10.00, '22222222-e22a-2cd2-f22-222222222222'),
       ('66666667-e66a-6cd6-f666-666666666666', 'OFFLINE', 40.00, '22222223-e22a-2cd2-f22-222222222222');

insert into ratings (rating_id, rating_for_teacher, feedback, teacher_id)
values ('77777777-e77a-7cd7-f777-777777777777', 6, 'Uses different methods', '22222222-e22a-2cd2-f22-222222222222'),
       ('77777778-e77a-7cd7-f777-777777777777', 7, 'Good with children', '22222223-e22a-2cd2-f22-222222222222');

insert into customers (customer_id, first_name, last_name, c_email, location_id, direction_id)
values ('33333333-e33a-3cd3-f333-333333333333', 'Albert', 'Wisoky', 'galen.crooks@hotmail.com',
        '88888888-e88a-8cd8-f888-888888888888', '11111111-e11a-1cd1-f111-111111111111'),
       ('33333334-e33a-3cd3-f333-333333333333', 'Ismael', 'Spencer', 'kassandra.hammes@yahoo.com',
        '88888889-e88a-8cd8-f888-888888888888', '11111112-e11a-1cd1-f111-111111111111');

insert into teachers (teacher_id, first_name, last_name, t_email, direction_id, location_id, type_id, rating_id)
values ('22222222-e22a-2cd2-f22-222222222222', 'Chong', 'Schamberger', 'dustin.nader@hotmail.com',
        '11111111-e11a-1cd1-f111-111111111111', '88888888-e88a-8cd8-f888-888888888888', '66666666-e66a-6cd6-f666-666666666666', '77777777-e77a-7cd7-f777-777777777777'),
       ('22222223-e22a-2cd2-f22-222222222222', 'Ulysses', 'Runte', 'monroe.hilpert@yahoo.com', '11111112-e11a-1cd1-f111-111111111111',
        '88888889-e88a-8cd8-f888-888888888888', '66666667-e66a-6cd6-f666-666666666666', '77777778-e77a-7cd7-f777-777777777777');

insert into locations (location_id, country, city, postal_code, customer_id, teacher_id)
values ('88888888-e88a-8cd8-f888-888888888888', 'Germany', 'Berlin', 30133, '33333333-e33a-3cd3-f333-333333333333', null),
       ('88888889-e88a-8cd8-f888-888888888888', 'Austria', 'Graz', 51261, '33333334-e33a-3cd3-f333-333333333333', null);