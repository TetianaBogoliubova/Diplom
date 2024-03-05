insert into directions (direction_id, d_title, grading, teacher_id, service_id, book_id, customer_id)
values ('832e9984-e95a-7cd1-f301-972908561245', 'MATHEMATICS', 'JUNIOR_SCHOOL', '958e2063-e24a-6cd9-f860-826782168386', '056e0725-e24a-7cd3-f291-258372740391', '226e8867-e33a-2cd3-f362-211620192358', '614e5310-e75a-9cd6-f593-566726876254'),
       ('482e5086-e47a-4cd8-f285-009316715220', 'ENGLISH', 'KINDERGARTEN', '837e8317-e35a-4cd1-f710-387841923887', '656e1953-e78a-7cd3-f921-458312740893', '340e6220-e51a-3cd9-f027-973873847631', '510e4635-e54a-6cd4-f272-367474413754'),
       ('732e5029-e47a-3cd8-f985-624336719840', 'GERMAN', 'JUNIOR_SCHOOL', '928e9697-e68a-2cd7-f383-511957512214', '056e0725-e24a-7cd3-f291-258372740391', '625e6139-e33a-5cd5-f194-118398749234', '483e5800-e40a-2cd3-f678-617223078864'),
       ('376e0361-e47a-3cd8-f988-326626717821', 'BIOLOGY', 'MIDDLE_SCHOOL', '958e2063-e24a-6cd9-f860-826782168386', '239e2849-e94a-8cd7-f271-820332740591', '298e7601-e47a-5cd9-f387-125124058224', '510e4635-e54a-6cd4-f272-367474413754'),
       ('478e5024-e29a-3cd8-f285-039346717840', 'CHEMISTRY', 'MIDDLE_SCHOOL', '837e8317-e35a-4cd1-f710-387841923887', '855e9821-e50a-3cd0-f381-340142740693', '963e3760-e47a-3cd1-f387-126044050285', '614e5310-e75a-9cd6-f593-566726876254'),
       ('482e3281-e47a-3cd8-f285-029398114840', 'PHYSICS', 'OLD_SCHOOL', '928e9697-e68a-2cd7-f383-511957512214', '356e1953-e94a-7cd3-f261-858342741933', '954e3760-e47a-3cd1-f387-368114057285', '510e4635-e54a-6cd4-f272-367474413754'),
       ('482e5023-e47a-3cd4-f995-069733676210', 'INFORMATICS', 'OLD_SCHOOL', '837e8317-e35a-4cd1-f710-387841923887', '378e2506-e34a-9cd5-f851-481682743270', '928e3760-e47a-3cd0-f387-482154057285', '483e5800-e40a-2cd3-f678-617223078864'),
       ('769e8502-e93a-3cd4-f285-029387250840', 'ECONOMICS', 'STUDENTS', '958e2063-e24a-6cd9-f860-826782168386', '760e8935-e94a-7cd3-f801-765822748220', '769e8301-e47a-3cd1-f818-764854051936', '614e5310-e75a-9cd6-f593-566726876254'),
       ('829e4917-e63a-2cd8-f835-647194890141', 'MANAGEMENT', 'ADULTS', '837e8317-e35a-4cd1-f710-387841923887', '056e0725-e24a-7cd3-f291-258372740391', '693e6013-e87a-7cd1-f819-761493883468', '614e5310-e75a-9cd6-f593-566726876254'),
       ('739e0326-e27a-1cd5-f907-620348816211', 'BOOKKEEPING', 'ADULTS', '928e9697-e68a-2cd7-f383-511957512214', '381e9535-e93a-8cd5-f291-658332810993', '226e8867-e33a-2cd3-f362-211620192358', '510e4635-e54a-6cd4-f272-367474413754'),
       ('152e2449-e80a-4cd6-f080-533627622808', 'LOGISTICS', 'ADULTS', '837e8317-e35a-4cd1-f710-387841923887', '239e2849-e94a-8cd7-f271-820332740591', '298e7601-e47a-5cd9-f387-125124058224', '483e5800-e40a-2cd3-f678-617223078864'),
       ('901e0360-e29a-6cd5-f999-492827694093', 'MEMORY_DEVELOPMENT', 'PENSIONERS', '958e2063-e24a-6cd9-f860-826782168386', '356e1953-e94a-7cd3-f261-858342741933', '340e6220-e51a-3cd9-f027-973873847631', '614e5310-e75a-9cd6-f593-566726876254'),
       ('401e6825-e71a-5cd3-f357-657177682532', 'READING', 'KINDERGARTEN', '837e8317-e35a-4cd1-f710-387841923887', '378e2506-e34a-9cd5-f851-481682743270', '625e6139-e33a-5cd5-f194-118398749234', '483e5800-e40a-2cd3-f678-617223078864'),
       ('571e9645-e27a-3cd6-f712-872762244687', 'LOGIC', 'STUDENTS', '928e9697-e68a-2cd7-f383-511957512214', '381e9535-e93a-8cd5-f291-658332810993', '693e6013-e87a-7cd1-f819-761493883468', '510e4635-e54a-6cd4-f272-367474413754'),
       ('181e7378-e89a-4cd3-f130-127357405796', 'MANUAL_WORK', 'PENSIONERS', '958e2063-e24a-6cd9-f860-826782168386', '656e1953-e78a-7cd3-f921-458312740893', '769e8301-e47a-3cd1-f818-764854051936', '483e5800-e40a-2cd3-f678-617223078864'),
       ('829e4762-e25a-8cd5-f199-631195284218', 'MUSIC', 'MIDDLE_SCHOOL', '958e2063-e24a-6cd9-f860-826782168386', '760e8935-e94a-7cd3-f801-765822748220', '928e3760-e47a-3cd0-f387-482154057285', '614e5310-e75a-9cd6-f593-566726876254');

insert into books (book_id, b_title, author, b_price, direction_id, service_id)
values ('226e8867-e33a-2cd3-f362-211620192358', 'The House of Mirth', 'Rocco Hermann', 20.00,
        '832e9984-e95a-7cd1-f301-972908561245', '783e0829-e61a-8cd5-f369-491406283137'),
       ('340e6220-e51a-3cd9-f027-973873847631', 'An Instant In The Wind', 'Emily Gottlieb', 25.00,
        '482e5086-e47a-4cd8-f285-009316715220', '656e1953-e78a-7cd3-f921-458312740893'),
       ('625e6139-e33a-5cd5-f194-118398749234', 'A Time of Gifts', 'Trey Braun', 18.00,
        '732e5029-e47a-3cd8-f985-624336719840', '056e0725-e24a-7cd3-f291-258372740391'),
       ('298e7601-e47a-5cd9-f387-125124058224', 'The Line of Beauty', 'Sherri Miller', 13.50,
        '376e0361-e47a-3cd8-f988-326626717821', '239e2849-e94a-8cd7-f271-820332740591'),
       ('963e3760-e47a-3cd1-f387-126044050285', 'The Waste Land', 'Sulema Rowe', 10.00,
        '478e5024-e29a-3cd8-f285-039346717840', '855e9821-e50a-3cd0-f381-340142740693'),
       ('954e3760-e47a-3cd1-f387-368114057285', 'To Say Nothing of the Dog', 'Leandra Quigley', 15.00,
        '482e3281-e47a-3cd8-f285-029398114840', '381e9535-e93a-8cd5-f291-658332810993'),
       ('928e3760-e47a-3cd0-f387-482154057285', 'I Will Fear No Evil', 'Bertha Paucek', 18.00,
        '482e5023-e47a-3cd4-f995-069733676210', '356e1953-e94a-7cd3-f261-858342741933'),
       ('769e8301-e47a-3cd1-f818-764854051936', 'Cover Her Face', 'Sylvia Weimann', 23.00,
        '769e8502-e93a-3cd4-f285-029387250840', '760e8935-e94a-7cd3-f801-765822748220'),
       ('693e6013-e87a-7cd1-f819-761493883468', 'Blue Remembered Earth', 'Coleman Dicki', 12.00,
        '829e4917-e63a-2cd8-f835-647194890141', '378e2506-e34a-9cd5-f851-481682743270');

insert into services (service_id, type, s_price, direction_id, book_id)
values ('783e0829-e61a-8cd5-f369-491406283137', 'PREPARATION_FOR_SCHOOL', 20.00, '832e9984-e95a-7cd1-f301-972908561245',
        '226e8867-e33a-2cd3-f362-211620192358'),
       ('656e1953-e78a-7cd3-f921-458312740893', 'SCHOOL_PROGRAM', 30.00, '482e5086-e47a-4cd8-f285-009316715220',
        '340e6220-e51a-3cd9-f027-973873847631'),
       ('056e0725-e24a-7cd3-f291-258372740391', 'PREPARATION_FOR_TECHNICAL_SCHOOL', 30.00,
        '732e5029-e47a-3cd8-f985-624336719840', '625e6139-e33a-5cd5-f194-118398749234'),
       ('239e2849-e94a-8cd7-f271-820332740591', ' PREPARATION_FOR_UNIVERSITY', 30.00,
        '376e0361-e47a-3cd8-f988-326626717821', '298e7601-e47a-5cd9-f387-125124058224'),
       ('855e9821-e50a-3cd0-f381-340142740693', 'PLAYING_MUSICAL_INSTRUMENTS', 30.00,
        '478e5024-e29a-3cd8-f285-039346717840', '963e3760-e47a-3cd1-f387-126044050285'),
       ('381e9535-e93a-8cd5-f291-658332810993', 'LEARNING_A_CRAFT', 30.00, '739e0326-e27a-1cd5-f907-620348816211',
        '954e3760-e47a-3cd1-f387-368114057285'),
       ('356e1953-e94a-7cd3-f261-858342741933', 'TRAINING_FOR_ADULTS', 30.00, '482e3281-e47a-3cd8-f285-029398114840',
        '928e3760-e47a-3cd0-f387-482154057285'),
       ('760e8935-e94a-7cd3-f801-765822748220', 'TRAINING_FOR_PENSIONEERS', 30.00,
        '769e8502-e93a-3cd4-f285-029387250840', '769e8301-e47a-3cd1-f818-764854051936'),
       ('378e2506-e34a-9cd5-f851-481682743270', ' BOOKSTORE', 30.00, '482e5023-e47a-3cd4-f995-069733676210',
        '693e6013-e87a-7cd1-f819-761493883468');

insert into types_of_learning (type_id, learning_types, special_price, teacher_id)
values ('580e0579-e99a-2cd2-f283-729335462796', 'ONLINE', 10.00, '958e2063-e24a-6cd9-f860-826782168386'),
       ('117e7748-e56a-3cd1-f540-426147884191', 'OFFLINE', 40.00, '837e8317-e35a-4cd1-f710-387841923887'),
       ('706e7226-e61a-1cd6-f099-314273313714', 'GROUP', 20.00, '928e9697-e68a-2cd7-f383-511957512214'),
       ('354e4209-e98a-5cd5-f904-143812497644', 'INDIVIDUAL', 15.00, '958e2063-e24a-6cd9-f860-826782168386'),
       ('720e7874-e56a-3cd7-f097-132263627595', 'REGULAR', 20.00, '837e8317-e35a-4cd1-f710-387841923887'),
       ('351e4568-e18a-9cd1-f136-633424481677', 'TIMELY', 30.00, '928e9697-e68a-2cd7-f383-511957512214');

insert into ratings (rating_id, rating_for_teacher, feedback, teacher_id)
values ('451e8893-e20a-6cd8-f470-307057153625', 6, 'Uses different methods', '958e2063-e24a-6cd9-f860-826782168386'),
       ('877e2246-e57a-9cd7-f555-573360728004', 7, 'Good with children', '837e8317-e35a-4cd1-f710-387841923887'),
       ('576e9192-e06a-5cd5-f194-405887628270', 9, 'High professionalism', '928e9697-e68a-2cd7-f383-511957512214');

insert into roles (role_id, role_name)
values ('226e8867-e33a-2cd3-f362-211620192111', 'CUSTOMER'),
       ('340e6220-e51a-3cd9-f027-973873847222', 'TEACHER'),
       ('340e6220-e51a-3cd9-f027-973873847333', 'ADMIN');

insert into authorities (authority_id, authority_name)
values ('116e8867-e33a-2cd3-f362-211620192111', 'READ'),
       ('220e6220-e51a-3cd9-f027-973873847222', 'WRITE'),
       ('330e6220-e51a-3cd9-f027-973873847333', 'ALLCHANGE');

insert into role_authorities(rol_id, authority_id)
values ('226e8867-e33a-2cd3-f362-211620192111', '116e8867-e33a-2cd3-f362-211620192111'),
       ('340e6220-e51a-3cd9-f027-973873847222', '220e6220-e51a-3cd9-f027-973873847222'),
       ('340e6220-e51a-3cd9-f027-973873847333', '330e6220-e51a-3cd9-f027-973873847333');

insert into customers (customer_id, first_name, last_name, c_email, location_id, direction_id)
values ('614e5310-e75a-9cd6-f593-566726876254', 'Albert', 'Wisoky', 'galen.crooks@hotmail.com',
        '128e5373-e59a-7cd7-f246-962698987329', '829e4762-e25a-8cd5-f199-631195284218'),
       ('483e5800-e40a-2cd3-f678-617223078864', 'Ismael', 'Spencer', 'kassandra.hammes@yahoo.com',
        '706e8538-e49a-4cd8-f656-433255983469', '181e7378-e89a-4cd3-f130-127357405796'),
       ('510e4635-e54a-6cd4-f272-367474413754', 'Sharla', 'Conn', 'brock.kilback@yahoo.com',
        '548e2018-e54a-7cd9-f333-598916517306', '571e9645-e27a-3cd6-f712-872762244687');

insert into customer_role(cus_id, rol_id)
values ('614e5310-e75a-9cd6-f593-566726876254', '226e8867-e33a-2cd3-f362-211620192111'),
       ('483e5800-e40a-2cd3-f678-617223078864', '340e6220-e51a-3cd9-f027-973873847222'),
       ('510e4635-e54a-6cd4-f272-367474413754', '340e6220-e51a-3cd9-f027-973873847333');

insert into teachers (teacher_id, first_name, last_name, t_email, direction_id, location_id, type_id, rating_id)
values ('958e2063-e24a-6cd9-f860-826782168386', 'Chong', 'Schamberger', 'dustin.nader@hotmail.com',
        '832e9984-e95a-7cd1-f301-972908561245', '740e6674-e74a-9cd4-f118-353479243341', '580e0579-e99a-2cd2-f283-729335462796', '451e8893-e20a-6cd8-f470-307057153625'),
       ('837e8317-e35a-4cd1-f710-387841923887', 'Ulysses', 'Runte', 'monroe.hilpert@yahoo.com', '482e5086-e47a-4cd8-f285-009316715220',
        '801e8077-e12a-4cd6-f404-122223873732', '117e7748-e56a-3cd1-f540-426147884191', '877e2246-e57a-9cd7-f555-573360728004'),
       ('928e9697-e68a-2cd7-f383-511957512214', 'Leonardo', 'Wiza', 'santana.dibbert@gmail.com', '732e5029-e47a-3cd8-f985-624336719840',
        '494e6484-e20a-2cd9-f939-161613393701', '706e7226-e61a-1cd6-f099-314273313714', '576e9192-e06a-5cd5-f194-405887628270');

insert into teacher_role(teach_id, rol_id)
values ('958e2063-e24a-6cd9-f860-826782168386', '226e8867-e33a-2cd3-f362-211620192111'),
       ('837e8317-e35a-4cd1-f710-387841923887', '340e6220-e51a-3cd9-f027-973873847222'),
       ('928e9697-e68a-2cd7-f383-511957512214', '340e6220-e51a-3cd9-f027-973873847333');

insert into locations (location_id, country, city, postal_code, customer_id, teacher_id)
values ('128e5373-e59a-7cd7-f246-962698987329', 'Germany', 'Berlin', 30133, '614e5310-e75a-9cd6-f593-566726876254', null),
       ('706e8538-e49a-4cd8-f656-433255983469', 'Austria', 'Graz', 51261, '483e5800-e40a-2cd3-f678-617223078864', null),
       ('548e2018-e54a-7cd9-f333-598916517306', 'Germany', 'Dresden', 46699, '510e4635-e54a-6cd4-f272-367474413754', null),
       ('740e6674-e74a-9cd4-f118-353479243341', 'Netherlands', 'Amsterdam', 40491, null, '958e2063-e24a-6cd9-f860-826782168386'),
       ('801e8077-e12a-4cd6-f404-122223873732', 'Germany', 'Dortmund', 84371, null , '837e8317-e35a-4cd1-f710-387841923887'),
       ('494e6484-e20a-2cd9-f939-161613393701', 'Austria', 'Vien', 80502, null, '928e9697-e68a-2cd7-f383-511957512214');