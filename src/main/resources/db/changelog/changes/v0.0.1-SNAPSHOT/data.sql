insert into services (service_id, type, s_price, direction_id, book_id)
values ('7c830t29-ts61-o985-j3p9-491w06j83137', 'preparation_for_school', 20.00, '8329ey41-93g5-77a1-2301-c7290d5612sr', '8c375104-sx51-l926-518r-76104286ve47'),
       ('x561953m-76n8-d573-92e1-k583f2740d93', 'school_program', 30.00, '4u82v502-947y-f438-p285-0n93h671s840', '9df37601-m471-o361-j387-4821b4193f85'),
       ('x560vn25-l242-d573-92e1-k583f2740e91', 'preparation_for_technical_school', 30.00, '7s32v502-947y-t538-o985-a243h671s840', '11237601-m471-oiu9-j387-8641b405s285'),
       ('yr93n284-l942-sq73-92e1-8203f2740e91', 'preparation_for_university', 30.00, '3c6a0361-947y-p038-o985-32x6h671s840', '29837601-m471-oiu9-j387-a251b405s22z'),
       ('c5j98210-l502-193f-23e1-a401f2740d93', 'playing_musical_instruments', 30.00, '4u82v502-h2o0-f438-p285-0n93h671s840', '9df37601-m471-o361-j387-h2o0b405s285'),
       ('3b81953m-193f-d573-92e1-k5833b810d93', 'learning_a_craft', 30.00, '4u823b81-947y-f438-p285-0n93b811s840', '9df37601-m471-o361-j387-3b81b405s285'),
       ('x561953m-l942-d573-92e1-k583f274193f', 'training_for_adults', 30.00, '4u82v502-947y-f438-p285-0n93h671s840', '9df37601-m471-h2o0-j387-4821b405s285'),
       ('76n893fm-l942-d573-3b81-76n8f274h2o0', 'training_for_persioneers', 30.00, '76n8v502-193f-f438-p285-0n93hh2o0840', '76n83f01-m471-o361-3b81-76n8b405193f'),
       ('3v782s06-2342-d5f5-7a81-n81sf274h2o0', 'bookstore', 30.00, 'u82s491q-763f-f438-pq35-hy71s48901ae', 'k93d601s-l871-7q61-3b81-t61a93v83s68');

insert into books (book_id, b_title, author, b_price, direction_id)
values ('2266t867-6d33-22s3-5362-21f620192358', 'The House of Mirth','Rocco Hermann', 20.00, '829dw762-62y2-8s75-8199-6311t5284c18'),
       ('340f6220-5169-t509-5s27-97387b847631', 'An Instant In The Wind', 'Emily Gottlieb', 25.00, '745y7458-4d66-54a5-8g44-1244v9497s15'),
       ('62g56139-9m33-51x5-5s14-1f839874g234', 'A Time of Gifts', 'Trey Braun', 18.00, '494j80g9-2v75-1s82-4863-419480xn3972');

insert into customers (customer_id, first_name, last_name, c_email, location_id, direction_id)
values ('614r5310-5j75-9s96-2w53-56672g876h54', 'Albert', 'Wisoky', 'galen.crooks@hotmail.com', '1289d537-53l9-76a7-24p6-962y98i87329', '4017u682-71t0-51s3-s357-6571w768m532');

insert into teachers(teacher_id, first_name, last_name, t_email, direction_id, location_id, rating_id)
values ();

insert into locations(location_id, country, city, postal_code)
values ();

insert into types_of_learning (type_id, learning_types, special_price)
values ();

insert into ratings (rating_id, rating_for_teacher, feedback)
values ();

insert into directions (direction_id, d_title, grading)
values ();


