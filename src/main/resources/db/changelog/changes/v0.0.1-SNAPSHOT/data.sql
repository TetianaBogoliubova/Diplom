insert into services (service_id, type, s_price, direction_id, book_id)
values ('7c830t29-ts61-o985-j3p9-491w06j83137', 'preparation_for_school', 20.00, '8329ey41-93g5-77a1-2301-c7290d5612sr',
        '2266t867-6d33-22s3-5362-21f620192358'),
       ('x561953m-76n8-d573-92e1-k583f2740d93', 'school_program', 30.00, '4u82v502-947y-f438-p285-0n93h671s840',
        '340f6220-5169-t509-5s27-97387b847631'),
       ('x560vn25-l242-d573-92e1-k583f2740e91', 'preparation_for_technical_school', 30.00,
        '7s32v502-947y-t538-o985-a243h671s840', '62g56139-9m33-51x5-5s14-1f839874g234'),
       ('yr93n284-l942-sq73-92e1-8203f2740e91', 'preparation_for_university', 30.00,
        '3c6a0361-947y-p038-o985-32x6h671s840', '29837601-m471-oiu9-j387-a251b405s22z'),
       ('c5j98210-l502-193f-23e1-a401f2740d93', 'playing_musical_instruments', 30.00,
        '4u82v502-h2o0-f438-p285-0n93h671s840', '9df37601-m471-o361-j387-h2o0b405s285'),
       ('3b81953m-193f-d573-92e1-k5833b810d93', 'learning_a_craft', 30.00, '4u823b81-947y-f438-p285-0n93b811s840',
        '9df37601-m471-o361-j387-3b81b405s285'),
       ('x561953m-l942-d573-92e1-k583f274193f', 'training_for_adults', 30.00, '4u82v502-947y-f438-p285-0n93h671s840',
        '9df37601-m471-h2o0-j387-4821b405s285'),
       ('76n893fm-l942-d573-3b81-76n8f274h2o0', 'training_for_persioneers', 30.00,
        '76n8v502-193f-f438-p285-0n93hh2o0840', '76n83f01-m471-o361-3b81-76n8b405193f'),
       ('3v782s06-2342-d5f5-7a81-n81sf274h2o0', 'bookstore', 30.00, 'u82s491q-763f-f438-pq35-hy71s48901ae',
        'k93d601s-l871-7q61-3b81-t61a93v83s68');

insert into books (book_id, b_title, author, b_price, direction_id)
values ('2266t867-6d33-22s3-5362-21f620192358', 'The House of Mirth', 'Rocco Hermann', 20.00,
        '829dw762-62y2-8s75-8199-6311t5284c18'),
       ('340f6220-5169-t509-5s27-97387b847631', 'An Instant In The Wind', 'Emily Gottlieb', 25.00,
        '745y7458-4d66-54a5-8g44-1244v9497s15'),
       ('62g56139-9m33-51x5-5s14-1f839874g234', 'A Time of Gifts', 'Trey Braun', 18.00,
        '494j80g9-2v75-1s82-4863-419480xn3972'),
       ('29837601-m471-oiu9-j387-a251b405s22z', 'The Line of Beauty', 'Sherri Miller', 13.50,
        '1y522449-7d80-42w6-30z0-5d3627622w08'),
       ('9df37601-m471-o361-j387-h2o0b405s285', 'The Waste Land', 'Sulema Rowe', 10.00,
        '9010u360-4s29-61w5-39c9-492s2769e093'),
       ('9df37601-m471-o361-j387-3b81b405s285', 'To Say Nothing of the Dog', 'Leandra Quigley', 15.00,
        '4017u682-71t0-51s3-s357-6571w768m532'),
       ('9df37601-m471-h2o0-j387-4821b405s285', 'I Will Fear No Evil', 'Bertha Paucek', 18.00,
        '571u9645-1d27-32a6-7712-87t762244n87'),
       ('76n83f01-m471-o361-3b81-76n8b405193f', 'Cover Her Face', 'Sylvia Weimann', 23.00,
        '1811t737-81e9-47n3-91a0-127f574057w6'),
       ('k93d601s-l871-7q61-3b81-t61a93v83s68', 'Blue Remembered Earth', 'Coleman Dicki', 12.00,
        '829dw762-62y2-8s75-8199-6311t5284c18');

insert into customers (customer_id, first_name, last_name, c_email, location_id, direction_id)
values ('614r5310-5j75-9s96-2w53-56672g876h54', 'Albert', 'Wisoky', 'galen.crooks@hotmail.com',
        '1289d537-53l9-76a7-24p6-962y98i87329', '4017u682-71t0-51s3-s357-6571w768m532'),
       ('48365800-9408-2733-1678-617223078864', 'Ismael', 'Spencer', 'kassandra.hammes@yahoo.com',
        '7061t853-42r9-w948-65l6-433d5598a469', '571u9645-1d27-32a6-7712-87t762244n87'),
       ('51084635-5549-6344-5272-367474413754', 'Sharla', 'Conn', 'brock.kilback@yahoo.com',
        '548h2018-2e54-f479-93a3-59h916517q06', '1811t737-81e9-47n3-91a0-127f574057w6');

insert into teachers(teacher_id, first_name, last_name, t_email, direction_id, location_id, rating_id, type_id)
values ('95832y06-32f3-6059-s860-826s82168c86', 'Chong', 'Schamberger', 'dustin.nader@hotmail.com',
        '1y522449-7d80-42w6-30z0-5d3627622w08', '74066d74-6r74-9j14-8r11-353d792433h1',
        '4510s889-f208-6r08-5h40-307w5715t625', '5800g579-93y9-21w2-2283-7d933546f796'),
       ('83738c17-3l55-a151-b710-387s4192s887', 'Ulysses', 'Runte', 'monroe.hilpert@yahoo.com',
        '73903x26-26s7-1r75-29j7-620s4881g211', '801s8077-9d12-4v56-40d4-12v223873l32',
        '87722r46-7e57-9q37-9m55-5u33607v8004', '11d77748-3u56-31d1-54x0-4261ds884191'),
       ('9z819697-8t89-w397-63c3-d1195751i214', 'Leonardo', 'Wiza', 'santana.dibbert@gmail.com',
        '9010u360-4s29-61w5-39c9-492s2769e093', '4x4648b4-1n20-2a39-r939-1r1613393u01',
        '57691c92-406e-510y-19r4-u05887r28270', '7067c226-561s-182x-f099-3142a331v714');

insert into locations(location_id, country, city, postal_code)
values ('1289d537-53l9-76a7-24p6-962y98i87329', 'Germany', 'Berlin', 30133),
       ('7061t853-42r9-w948-65l6-433d5598a469', 'Austria', 'Graz', 51261),
       ('548h2018-2e54-f479-93a3-59h916517q06', 'Germany', 'Dresden', 46699),
       ('74066d74-6r74-9j14-8r11-353d792433h1', 'Netherlands', 'Amsterdam', 40491),
       ('801s8077-9d12-4v56-40d4-12v223873l32', 'Germany', 'Dortmund', 84371),
       ('4x4648b4-1n20-2a39-r939-1r1613393u01', 'Austria', 'Vien', 80502);

insert into types_of_learning (type_id, learning_types, special_price)
values ('5800g579-93y9-21w2-2283-7d933546f796', 'online', 10.00),
       ('11d77748-3u56-31d1-54x0-4261ds884191', 'offline', 10.00),
       ('7067c226-561s-182x-f099-3142a331v714', 'group', 10.00),
       ('35442t93-78s9-51w5-3x94-14w81249j644', 'individual', 10.00),
       ('72078a74-86s0-3re7-5j07-1v226362d595', 'regular', 10.00),
       ('3511rf68-2x18-w914-7v16-6f3424wf1677', 'timely', 10.00);

insert into ratings (rating_id, rating_for_teacher, feedback)
values ('4510s889-f208-6r08-5h40-307w5715t625', 6, 'Uses different methods'),
       ('87722r46-7e57-9q37-9m55-5u33607v8004', 7, 'Good with children'),
       ('57691c92-406e-510y-19r4-u05887r28270', 9, 'High professionalism');

insert into directions (direction_id, d_title, grading)
values ('8329ey41-93g5-77a1-2301-c7290d5612sr', 'mathematics', 'junior_school'),
       ('4u82v502-947y-f438-p285-0n93h671s840', 'english', 'kindergarten'),
       ('7s32v502-947y-t538-o985-a243h671s840', 'german', 'junior_school'),
       ('3c6a0361-947y-p038-o985-32x6h671s840', 'biology', 'middle_school'),
       ('4u82v502-h2o0-f438-p285-0n93h671s840', 'chemistry', 'middle_school'),
       ('4u823b81-947y-f438-p285-0n93b811s840', 'physics', '"old_school"'),
       ('4u82v502-947y-f438-p285-0n93h671s840', 'informatics', '"old_school"'),
       ('76n8v502-193f-f438-p285-0n93hh2o0840', 'economics', 'students'),
       ('u82s491q-763f-f438-pq35-hy71s48901ae', 'management', 'adults'),
       ('73903x26-26s7-1r75-29j7-620s4881g211', 'bookkeeping', 'adults'),
       ('1y522449-7d80-42w6-30z0-5d3627622w08', 'logistics', 'adults'),
       ('9010u360-4s29-61w5-39c9-492s2769e093', 'memory_development', 'pensioners'),
       ('4017u682-71t0-51s3-s357-6571w768m532', 'reading', 'kindergarten'),
       ('571u9645-1d27-32a6-7712-87t762244n87', 'logic', 'students'),
       ('1811t737-81e9-47n3-91a0-127f574057w6', 'manual_work', 'pensioners'),
       ('829dw762-62y2-8s75-8199-6311t5284c18', 'music', 'middle_school');







