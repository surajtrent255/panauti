INSERT INTO `form` (`id`, `form_id`, `form_name`) VALUES (1, 'MUNICIPALITY_RESIDENT_FORM', 'Municipality Residents Information Form');

INSERT INTO `options` (`id`, `option_id`, `option_text`, `question_id`) VALUES
	(1, 1, 'नेपाली', 8),
	(2, 2, 'मैथिली', 8),
	(3, 3, 'भोजपुरी', 8),
	(4, 4, 'थारु', 8),
	(5, 5, 'तामाङ', 8),
	(6, 6, 'नेवार', 8),
	(7, 7, 'मगर', 8),
	(8, 8, 'बज्जिका', 8),
	(9, 9, 'उर्दु', 8),
	(10, 10, 'अवाधी', 8),
	(11, 11, 'लिम्बु', 8),
	(12, 12, 'गुरुङ', 8),
	(13, 13, 'अन्य', 8),
	(14, 1, 'क्षत्री', 9),
	(15, 2, 'ब्राह्मण', 9),
	(16, 3, 'जनजाति', 9),
	(17, 4, 'दलित', 9),
	(18, 5, 'मधेशी', 9),
	(19, 6, 'मुस्लिम', 9),
	(20, 7, 'अन्य', 9),
	(21, 1, 'हिन्दु', 10),
	(22, 2, 'बौद्ध', 10),
	(23, 3, 'इस्लाम', 10),
	(24, 4, 'किँरात', 10),
	(25, 5, 'ईसाई', 10),
	(26, 6, 'निर्दिष्ट / अन्य', 10),
	(27, 1, 'धारा', 19),
	(28, 2, 'इनार', 19),
	(29, 3, 'अन्य', 19),
	(30, 1, 'ढल', 20),
	(31, 2, 'सेफ्टी ट्याङ्की', 20),
	(32, 3, 'छैन', 20),
	(33, 1, 'मोटर', 21),
	(34, 2, 'मोटर साइकल', 21),
	(35, 3, 'साइकल', 21),
	(36, 4, 'अन्य', 21),
	(37, 5, 'छैन', 21),
	(38, 1, 'आर. सि. सि ढलान', 22),
	(39, 2, 'माटोको छाना', 22),
	(40, 3, 'जस्ता वा च्याटर छाना', 22),
	(41, 4, 'टायल वा ढुंगाको छाना', 22),
	(42, 5, 'अन्य', 22),
	(43, 1, 'माटो वा ढुंगा', 23),
	(44, 2, 'सिमेन्ट र ढुंगा', 23),
	(45, 3, 'फ्रेम स्ट्रकचर', 23),
	(46, 4, 'लोड वायरिङ्ग', 23),
	(47, 5, 'काठको खम्बा', 23),
	(48, 6, 'अन्य', 23),
	(49, 1, 'छ', 24),
	(50, 2, 'छैन', 24),
	(51, 3, 'अन्य', 24),
	(52, 1, 'घर', 25),
	(53, 2, 'गाई वस्तु', 25),
	(54, 3, 'खेतीपाती', 25),
	(55, 4, 'मानव', 25),
	(56, 5, 'छैन', 25),
	(57, 1, 'छ', 26),
	(58, 2, 'छैन', 26),
	(59, 3, 'अन्य', 26),
	(60, 1, 'छ', 27),
	(61, 2, 'छैन', 27),
	(62, 1, 'स्थानिय सरकार', 28),
	(63, 2, 'गैर-सरकारी संस्था', 28),
	(64, 3, 'गुँठि', 28),
	(65, 4, 'अन्य', 28),
	(66, 1, 'बाढि वा डुवान', 30),
	(67, 2, 'पहिरो', 30),
	(68, 3, 'आगलागी', 30),
	(69, 4, 'दुरघटना (सडक)', 30),
	(70, 5, 'भुकम्प', 30),
	(71, 6, 'धुवा-धुलो', 30),
	(72, 7, 'महामारी', 30),
	(73, 1, 'छ', 31),
	(74, 2, 'छैन', 31),
	(75, 1, 'गल्फ', 32),
	(76, 2, 'युरोप', 32),
	(77, 3, 'एशिया', 32),
	(78, 4, 'अन्य', 32),
	(79, 5, 'छैन', 32),
	(80, 1, 'अष्ट्रेलिया', 33),
	(81, 2, 'यु . के .', 33),
	(82, 3, 'यु . एस . ए.', 33),
	(83, 4, 'अन्य', 33),
	(84, 5, 'छैन', 33),
	(85, 1, 'दैनिक ज्यालादारी', 34),
	(86, 2, 'कलकारखाना मजदुर', 34),
	(87, 3, 'यातायात मजदुर', 34),
	(88, 4, 'उत्पादक मजदुर', 34),
	(89, 5, 'घरेलु कामदार', 34),
	(90, 6, 'अन्य', 34),
	(91, 7, 'छैन', 34),
	(92, 1, 'मल बनाउने', 35),
	(93, 2, 'निजी संकलन', 35),
	(94, 3, 'नगरको गाडीमा पठाउने', 35),
	(95, 4, 'अन्य', 35),
	(96, 1, 'बिधुत', 36),
	(97, 2, 'जेनेरेटर', 36),
	(98, 3, 'गोबर ग्याँस', 36),
	(99, 4, 'सोलार', 36),
	(100, 5, 'मट्टितेल', 36),
	(101, 6, 'इन्भटर अथवा ब्याट्री', 36),
	(102, 7, 'अन्य', 36),
	(103, 8, 'छैन', 36),
	(104, 1, 'एल. पी. ग्याँस', 37),
	(105, 2, 'मट्टितेल', 37),
	(106, 3, 'दाउरा', 37),
	(107, 4, 'अन्य', 37),
	(108, 1, 'टेलिफोन', 38),
	(109, 2, 'इन्टरनेट', 38),
	(110, 3, 'मोबाईल', 38),
	(111, 4, 'टेलिभिजन', 38),
	(112, 5, 'रेडियो', 38),
	(113, 6, 'अन्य', 38),
	(114, 7, 'छैन', 38),
	(115, 1, '१ रोपनी भन्दा कम', 39),
	(116, 2, '१-५ रोपनी', 39),
	(117, 3, '५-१० रोपनी', 39),
	(118, 4, '१० रोपनी भन्दा माथी', 39),
	(119, 1, '१,००,०००/- भन्दा कम', 40),
	(120, 2, '१,००,००१/- देखि २,५०,०००/-', 40),
	(121, 3, '२,५०,००१/- देखि ४,००,०००/-', 40),
	(122, 4, '४,००,००१/- देखि ६,००,०००/-', 40),
	(123, 5, '६,००,००१/- देखि ८,००,०००/-', 40),
	(124, 6, '८,००,००१/- भन्दा माथि', 40),
	(125, 1, '१,००,०००/- भन्दा कम', 41),
	(126, 2, '१,००,००१/- देखि २,५०,०००/-', 41),
	(127, 3, '२,५०,००१/- देखि ४,००,०००/-', 41),
	(128, 4, '४,००,००१/- देखि ६,००,०००/-', 41),
	(129, 5, '६,००,००१/- देखि ८,००,०००/-', 41),
	(130, 6, '८,००,००१/- भन्दा माथि', 41),
	(131, 1, 'गाई', 42),
	(132, 2, 'भैँसी', 42),
	(133, 3, 'गोरु', 42),
	(134, 4, 'बाख्रा', 42),
	(135, 5, 'बोका', 42),
	(136, 6, 'भेडा', 42),
	(137, 7, 'च्याङग्रा', 42),
	(138, 8, 'कुकुर', 42),
	(139, 9, 'हाँस', 42),
	(140, 10, 'कुखुरा', 42),
	(141, 11, 'अन्य', 42),
	(142, 12, 'छैन', 42),
	(143, 1, '३ महिना', 43),
	(144, 2, '४-६ महिना', 43),
	(145, 3, '७-९ महिना', 43),
	(146, 4, '९ महिना भन्दा बढी', 43),
	(147, 1, 'छ', 44),
	(148, 2, 'छैन', 44),
	(149, 1, 'शारीरिक अपाङ्गता', 52),
	(150, 2, 'दृष्टिविहिन र न्युन दृष्टिविहिन', 52),
	(151, 3, 'बोल्न नसक्ने', 52),
	(152, 4, 'सुस्त श्रवण', 52),
	(153, 5, 'मानसिक अपाङ्गता', 52),
	(154, 6, 'श्रवण र दृष्टिविहिन (दुवै)', 52),
	(155, 7, 'अन्य', 52),
	(156, 8, 'छैन', 52),
	(157, 1, 'शारीरिक अपाङ्गता', 53),
	(158, 2, 'दृष्टिविहिन र न्युन दृष्टिविहिन', 53),
	(159, 3, 'बोल्न नसक्ने', 53),
	(160, 4, 'सुस्त श्रवण', 53),
	(161, 5, 'मानसिक अपाङ्गता', 53),
	(162, 6, 'श्रवण र दृष्टिविहिन (दुवै)', 53),
	(163, 7, 'अन्य', 53),
	(164, 8, 'छैन', 53),
	(165, 1, 'लामा, धामी, झाँऋी', 54),
	(166, 2, 'परंपरागत वैद्य', 54),
	(167, 3, 'स्वास्थ्य चौकी', 54),
	(168, 4, 'अस्पताल (निजी, सरकारी)', 54),
	(169, 5, 'अन्य', 54),
	(170, 1, 'बालक', 55),
	(171, 2, 'बालिका', 55),
	(172, 3, 'छैन', 55),
	(173, 1, 'बालक', 56),
	(174, 2, 'बालिका', 56),
	(175, 3, 'छैन', 56),
	(176, 1, 'गाली', 57),
	(177, 2, 'कुटपीट', 57),
	(178, 3, 'खानामा बन्देज', 57),
	(179, 4, 'कोठामा थुन्ने', 57),
	(180, 5, 'सम्झाउने', 57),
	(181, 6, 'अन्य', 57),
	(182, 1, 'छन्', 58),
	(183, 2, 'छैनन्', 58),
	(184, 1, 'छ', 59),
	(185, 2, 'छैन', 59),
	(186, 3, 'थाहा छैन', 59),
	(187, 1, 'दाईजोसम्बन्धी', 60),
	(188, 2, 'वहुविवाह', 60),
	(189, 3, 'घरायसी हिंसा', 60),
	(190, 4, 'बालविवाह', 60),
	(191, 5, 'बलात्कार', 60),
	(192, 6, 'महिला/बालबालिका बेचबिखन', 60),
	(193, 7, 'कुनै पनि घटना नघटेको', 60),
	(194, 1, 'छ भने जम्मा संख्या', 61),
	(195, 2, 'छैन', 61);
	

INSERT INTO `question` (`id`, `question_id`, `description`, `group`, `required`, `type_id`, `form_id`, `reportable`) VALUES
	(1, 'Owner_name', 'घरमूलीको नाम', 'व्यक्तिगत', 2, 3, 1, b'0'),
	(2, 'Tole', 'टोल', 'व्यक्तिगत', 2, 3, 1, b'0'),
	(3, 'resident_ward_no', 'वडा नं', 'व्यक्तिगत', 2, 15, 1, b'0'),
	(4, 'House_no', 'घर नं', 'व्यक्तिगत', 2, 4, 1, b'0'),
	(5, 'Phone_no', 'फोन नं', 'व्यक्तिगत', 1, 4, 1, b'0'),
	(6, 'Father_name', 'बुबाको नाम', 'व्यक्तिगत', 2, 3, 1, b'0'),
	(7, 'Mother_name', 'आमाको नाम', 'व्यक्तिगत', 2, 3, 1, b'0'),
	(8, 'Mother_togue', 'मातृभाषा', 'व्यक्तिगत', 2, 13, 1, b'0'),
	(9, 'Caste', 'जाति', 'व्यक्तिगत', 2, 13, 1, b'0'),
	(10, 'Religion', 'धर्म', 'व्यक्तिगत', 2, 13, 1, b'0'),
	(11, 'Citizenship_no', 'नागरिकता नं', 'व्यक्तिगत', 2, 3, 1, b'0'),
	(12, 'citizenship_district', 'नागरिकता लिएको जिल्ला', 'व्यक्तिगत', 2, 14, 1, b'0'),
	(13, 'kitta_number', 'कित्ता नं', 'घर विवरण', 2, 4, 1, b'0'),
	(14, 'house_count', 'घर संख्या', 'घर विवरण', 2, 4, 1, b'0'),
	(15, 'business', 'व्यवसाय', 'घर विवरण', 1, 3, 1, b'0'),
	(16, 'businessman', 'व्यवसायी', 'घर विवरण', 1, 3, 1, b'0'),
	(17, 'bphone_no', 'फोन नं', 'घर विवरण', 1, 4, 1, b'0'),
	(18, 'house_storey', 'घरको तल्ला', 'घर विवरण', 2, 4, 1, b'0'),
	(19, 'water', 'पानी', 'घर विवरण', 2, 1, 1, b'0'),
	(20, 'outlet', 'निकास', 'घर विवरण', 2, 1, 1, b'0'),
	(21, 'vehicle', 'सवारी साधन', 'घर विवरण', 2, 12, 1, b'1'),
	(22, 'Roof_type', 'घरको छानाको प्रकार', 'घर विवरण', 2, 2, 1, b'0'),
	(23, 'foundation_type', 'घरको जगको प्रकार', 'घर विवरण', 2, 2, 1, b'0'),
	(24, 'EQ_proof', 'घर भुकम्प प्रतिरोधात्मक', 'घर विवरण', 2, 2, 1, b'0'),
	(25, 'EQ_Effect', 'तपाईको घर वा परिवारलाई २०७२ सालको भुकम्पले के-कस्तो क्षेती पुर्यायो?', 'घर विवरण', 2, 1, 1, b'0'),
	(26, 'Re_construction', 'घर क्षती भएको भए पुनः निर्माण गर्नु भयो?', 'घर विवरण', 1, 2, 1, b'0'),
	(27, 'Risk_type', 'तपाईको घर परिवारलाई अहिले कुनै प्रकारको जोखिम छ?', 'जोखिम', 1, 9, 1, b'0'),
	(28, 'Rescuer_first', 'जोखिमको समयमा सबैभन्दा पहिलो उद्धार कसले गरेको थियो?', 'जोखिम', 2, 2, 1, b'0'),
	(29, 'Risk_type_5yrs', 'पछिल्लो ५ वर्षमा तपाईको घर परिवारले कस्तो किसिमका जोखिम सामना गर्नु पर्यो?', 'जोखिम', 1, 3, 1, b'0'),
	(30, 'Risks_rating', 'तपाईको विचारमा यस क्षेत्रमा तलका मध्ये कुन-कुन जोखिमको खतरा उच्च छ? (१ देखि ७ सम्म स्तरीकरण गर्नुहोस्। १ लाई उच्च र ७ लाई न्युन जोखिम मान्नुहोस्) ', 'जोखिम', 2, 10, 1, b'0'),
	(31, 'Risk_precautions', 'भविष्यमा हुन सक्ने जोखिम नियन्त्रणको लागि के-कस्तो पुर्व तयारी गर्नु भएको छ?', 'जोखिम', 1, 9, 1, b'0'),
	(32, 'Foreign_employment', 'वैदेशिक रोजगार', 'उपयोगिता', 2, 12, 1, b'1'),
	(33, 'Foreign_education', 'वैदेशिक अध्ययन', 'उपयोगिता', 2, 12, 1, b'1'),
	(34, 'child_labour', '१६ वर्ष सम्मका बालबालिका अन्य ठाँउमा काम गर्न गएका छन्?', 'बालबालिका', 2, 1, 1, b'0'),
	(35, 'waste_management', 'घरबाट उत्पादन भएको फोहोरको ब्यबस्था', 'उपयोगिता', 2, 1, 1, b'1'),
	(36, 'electricity', 'बत्ती', 'उपयोगिता', 2, 1, 1, b'0'),
	(37, 'cooking_fuel', 'खाना पकाउन पर्योग हुने इन्धन', 'उपयोगिता', 2, 1, 1, b'1'),
	(38, 'communication_devices', 'सञ्चार सुविधा', 'उपयोगिता', 2, 1, 1, b'0'),
	(39, 'Houseland_info', 'घर जग्गाको विवरण', 'उपयोगिता', 2, 2, 1, b'0'),
	(40, 'annual_income', 'वार्षिक आम्दानी', 'आर्थिक', 2, 2, 1, b'1'),
	(41, 'annual_expense', 'वार्षिक खर्च', 'आर्थिक', 2, 2, 1, b'1'),
	(42, 'cattles', 'चौपाया तथा पशुपंक्षी', 'आर्थिक', 2, 12, 1, b'0'),
	(43, 'production_lasting_time', 'आफ्नो उत्पादन तथा आय स्रोतले कति महिना खान पुग्छ?', 'आर्थिक', 2, 2, 1, b'0'),
	(44, 'skill_training', 'सिप तालिम पहिला लिनुभएको छ?', 'आर्थिक', 2, 2, 1, b'0'),
	(45, 'skill_training_in_future', 'भविष्यमा कस्तो सिप तालिम गर्नु पर्ला?', 'आर्थिक', 2, 3, 1, b'0'),
	(46, 'citizenship_photo', 'नागरिकताको फोटो', 'कागजात', 1, 7, 1, b'0'),
	(47, 'relationship_proof_photo', 'नाता प्रमाण-पत्रको फोटो', 'कागजात', 1, 7, 1, b'0'),
	(48, 'building_map_photo', 'पास भएको घरको नक्साको फोटो', 'कागजात', 1, 7, 1, b'0'),
	(49, 'other_document_photo', 'अन्य कागजातको फोटो', 'कागजात', 1, 7, 1, b'0'),
	(50, 'gps_info', 'GPS co-ordinate', 'सर्वेक्षण विवरण', 2, 6, 1, b'0'),
	(51, 'house_owner_photo', 'Photo of house owner', 'सर्वेक्षण विवरण', 2, 7, 1, b'0'),
	(52, 'physical_mental_male', 'तपाईको परिवारमा १८ वर्षभन्दा कम उमेरका भिन्न शारीरिक तथा मानसिक क्षमता भएका(अपाङ्ग) सदस्यहरु (बालक)', 'बालबालिका', 2, 12, 1, b'0'),
	(53, 'physical_mental_female', 'तपाईको परिवारमा १८ वर्षभन्दा कम उमेरका भिन्न शारीरिक तथा मानसिक क्षमता भएका(अपाङ्ग) सदस्यहरु (बालिका)', 'बालबालिका', 2, 12, 1, b'0'),
	(54, 'primary_health_service', 'परिवारको कुनै सदस्य विरामी भएमा उपचारका लागि सबैभन्दा पहिला कहाँ जानुहुन्छ?', 'बालबालिका', 2, 2, 1, b'0'),
	(55, 'school_leaving_children', 'बिचैमा विद्यालय जान छोडेका ६ - १५ वर्ष उमेर समुहका बालबालिकाहरुका संख्या', 'बालबालिका', 2, 12, 1, b'0'),
	(56, 'playgroup_children', 'बाल विकास केन्द्र वा पुर्व प्रा.वि. तहमा जाने ३ देखि ५ वर्षसम्मका बालबालिका संख्या', 'बालबालिका', 2, 12, 1, b'0'),
	(57, 'children_mistake', 'तपाईको परिवारमा बालबालिकाले गल्ति गरेमा के गर्नु हुन्छ?', 'बालबालिका', 2, 1, 1, b'0'),
	(58, 'children_street', 'तपाईको परिवारका कुनै बालबालिका सडक बालबालिकाको रुपमा रहेका छन्?', 'बालबालिका', 2, 2, 1, b'0'),
	(59, 'children_school_disabilities', 'तपाईको परिवारका बालबालिका पढ्ने विद्यालायमा भिन्न शारीरिक क्षमता भएका विद्यार्थीका लागि विशेष शिक्षाको सुविधा छ?', 'बालबालिका', 2, 2, 1, b'0'),
	(60, 'violence_women_children', 'एक वर्षभित्र तपाईको परिवारमा महिला तथा बालबालिका उपर हिंसा सम्बन्धी निम्न कुनै घटना घटेका छन/छैनन्?', 'बालबालिका', 2, 1, 1, b'0'),
	(61, 'child_mortality_les_1_year', 'बितेको १ वर्षभित्र तपाईको परिवारमा ५ वर्षमुनिका कुनै बालबालिकाको मृत्यु भएको छ?', 'बालबालिका', 2, 9, 1, b'0');
	

	


INSERT INTO `family_relation` (`relation_id`, `relation_nepali`, `relation_english`) VALUES
	(1, 'आफु', 'Self'),
	(2, 'हजुर बुबा', 'Grandfather'),
	(3, 'हजुर आमा', 'Grandmother'),
	(4, 'बुबा', 'Father'),
	(5, 'आमा', 'Mother'),
	(6, 'श्रीमान', 'Husband'),
	(7, 'श्रीमती', 'Wife'),
	(8, 'छोरा', 'Son'),
	(9, 'छोरी', 'Daughter'),
	(10, 'नाति', 'Grandson'),
	(11, 'नातिनी', 'Granddaughter'),
	(12, 'दाइ', 'Brother(elder)'),
	(13, 'भाइ', 'Brother(younger)'),
	(14, 'दिदी', 'Sister(elder)'),
	(15, 'बहिनी', 'Sister(younger)'),
	(16, 'बुहारी', 'Daughter-in-law'),
	(17, 'ज्वाँइ', 'Son-in-law'),
	(18, 'भाउजु', 'Sister-in-law'),
	(19, 'भान्जा ', 'Nephew (Maternal)'),
	(20, 'भान्जी', 'Niece (Maternal)'),
	(21, 'भतिजा', 'Nephew (Paternal)'),
	(22, 'भतिजी', 'Niece (Paternal)'),
	(23, 'सासु', 'Mother-in-law'),
	(24, 'ससुरा', 'Father-in-law'),
	(25, 'अन्य', 'other');


	INSERT INTO `academic_qualification` (`qualification_id`, `qualification_nep`, `qualification_eng`) VALUES
	(1, 'पिएचडि', 'phd'),
	(2, 'एमफिल', 'mphil'),
	(3, 'मास्टर डिग्री(स्नातकोत्तर)', 'masters'),
	(4, 'स्नातक', 'bachelors'),
	(5, 'उच्च विद्यालय', 'high school'),
	(6, 'माध्यामिक', 'secondary'),
	(7, 'तल्लो माध्यामिक', 'lower secondary'),
	(8, 'प्राथमिक', 'primary'),
	(9, 'सामान्य शिक्षा', 'literate'),
	(10, 'असाक्षर', 'Iliterate');


	INSERT INTO `gender` (`gender_id`, `gender_english`, `gender_nepali`) VALUES
	(1, 'Male', 'पुरुष'),
	(2, 'Female', 'महिला'),
	(3, 'Other', 'अन्य');
	
	
	INSERT INTO `districts` (`district_id`, `district_name_eng`, `district_name_nep`) VALUES 
	(1,'Achham','अछाम'),
	(2,'Arghakhanchi','अर्घाखाँची'),
	(3,'Baglung','बागलुङ'),
	(4,'Baitadi','बैतडी'),
	(5,'Bajhang','बझाङ'),
	(6,'Bajura','बाजुरा '),
	(7,'Banke','बाँके'),
	(8,'Bara','बारा'),
	(9,'Bardiya','बर्दिया'),
	(10,'Bhaktapur','भक्तपुर'),
	(11,'Bhojpur','भोजपुर'),
	(12,'Chitwan','चितवन'),
	(13,'Dadeldhura','डडेलधुरा'),
	(14,'Dailekh','दैलेख'),
	(15,'Dang Deukhuri','दाङ देउखुरी'),
	(16,'Darchula','दार्चुला'),
	(17,'Dhading','धादिङ '),
	(18,'Dhankuta','धनकुटा'),
	(19,'Dhanusa','धनुषा'),
	(20,'Dolakha','दोलखा'),
	(21,'Dolpa','डोल्पा'),
	(22,'Doti','डोटी'),
	(23,'Gorkha','गोरखा'),
	(24,'Gulmi','गुल्मी'),
	(25,'Humla','हुम्ला'),
	(26,'Ilam','इलाम'),
	(27,'Jajarkot','जाजरकोट'),
	(28,'Jhapa','झापा'),
	(29,'Jumla','जुम्ला'),
	(30,'Kailali','कैलाली'),
	(31,'Kalikot','कालिकोट'),
	(32,'Kanchanpur','कंचनपुर '),
	(33,'Kapilvastu','कपिलवस्तु '),
	(34,'Kaski','कास्की'),
	(35,'Kathmandu','काठमाडौँ'),
	(36,'Kavrepalanchok','काभ्रेकाभ्रेपलान्चोक'),
	(37,'Khotang','खोटाँग'),
	(38,'Lalitpur','ललितपुर'),
	(39,'Lamjung','लमजुङ'),
	(40,'Mahottari','महोत्तरी'),
	(41,'Makwanpur','मकवानपुर'),
	(42,'Manang','मनाङ'),
	(43,'Morang','मोरंग'),
	(44,'Mugu','मुगु'),
	(45,'Mustang','मुस्ताङ'),
	(46,'Myagdi','म्याग्दी'),
	(47,'Nawalparasi East','नवलपरासी पूर्व'),
	(48,'Nawalparasi West','नवलपरासी पश्चिम'),
	(49,'Nuwakot','नुवाकोट'),
	(50,'Okhaldhunga','ओखलढुंगा'),
	(51,'Palpa','पाल्पा'),
	(52,'Panchthar','पांचथर'),
	(53,'Parbat','पर्वत'),
	(54,'Parsa','पर्सा'),
	(55,'Pyuthan','प्युठान'),
	(56,'Ramechhap','रामेछाप'),
	(57,'Rasuwa','रसुवा'),
	(58,'Rautahat','रौतहट'),
	(59,'Rolpa','रोल्पा'),
	(60,'Rukum East','रूकुम पूर्वी'),
	(61,'Rukum West','रूकुम पश्चिम'),
	(62,'Rupandehi','रुपन्देही'),
	(63,'Salyan','सल्यान '),
	(64,'Sankhuwasabha','संखुवासभा'),
	(65,'Saptari','सप्तरी'),
	(66,'Sarlahi','सर्लाही'),
	(67,'Sindhuli','सिन्धुली'),
	(68,'Sindhupalchok','सिन्धुपाल्चोक'),
	(69,'Siraha','सिराहा'),
	(70,'Solukhumbu','सोलुखुम्बू'),
	(71,'Sunsari','सुनसरी'),
	(72,'Surkhet','सुर्खेत'),
	(73,'Syangja','स्याङग्जा'),
	(74,'Tanahu','तनहुँ'),
	(75,'Taplejung','ताप्लेजुंग'),
	(76,'Tehrathum','तेह्रथुम'),
	(77,'Udayapur','उदयपुर');
	
	
	INSERT INTO `ward` (`id`, `ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES ('1', 1, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`id`, `ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES ('2', 2, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (3, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (4, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (5, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (6, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (7, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (8, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (9, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (10, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (11, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (12, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (13, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');
	INSERT INTO `ward` (`ward_number`, `location`, `name`, `ward_description`, `main_person`, `contact_no`) VALUES (14, 'xxxx', 'xxxx', 'xxxx', 'xxxxx', 'xxxxx');


	INSERT INTO `favourite_place_type` (`type_id`, `place_type_nep`, `place_type_eng`) VALUES
	(1, 'मन्दिर', 'Temple'),
	(2, 'पार्क', 'Park'),
	(3, 'पोखरी', 'Pond'),
	(4, 'इनार/ पँधेरी', 'well/watches'),
	(5, 'स्तुपा/मूति', 'Statue'),
	(6, 'विद्यालय', 'School'),
	(7, 'संघ/संस्था', 'Organization'),
	(8, 'गुँठी', 'Guthi'),
	(9, 'अन्य', 'Other');
	
		
	INSERT INTO `marital_status` (`marital_status_id`, `marital_status_nep`, `marital_status_eng`) VALUES
	(1, 'विवाहित', 'Married'),
	(2, 'अविवाहित', 'Single');
	
