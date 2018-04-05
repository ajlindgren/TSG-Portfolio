use superherotracker;

insert into `power`(description)
values
('Concussion Beams'),
('Energy Sourcing'),
('Inhuman Nature'),
('Superhuman Strength'),
('Physical Duplication'),
('Poison Generation'),
('Magnetism Manipulation'),
('Fire Manipulation');

insert into `super`(name, description, powerId)
values
('Cyclops', 'Cyclops can emit powerful beams of energy from his eyes.', 1),
('Darkseid', 'His main power, the Omega Beams, is a form of energy that he fires from his eyes or hands as either a concussive force or a disintegrating energy which is capable of erasing living objects and organisms from existence.', 1),
('Gambit', 'Gambit has the ability to mentally create, control, and manipulate pure kinetic energy to his desire.', 2),
('Goku', 'Goku is a member of an extraterrestrial warrior race called the Saiyans, which is the reason for his superhuman strength', 3),
('Vegeta', 'Vegeta is a member of an extraterrestrial warrior race called the Saiyans, which is the reason for his superhuman strength', 3),
('Hulk', 'Mild-mannered Bruce Banner was exposed to gamma radiation. You wouldnt like him when hes angry.', 4),
('Naruto Uzumaki', 'A teen ninja from the fictional village of Konohagakure.', 5),
('Poison Ivy', 'One of the worlds most notorious eco-terrorists, she uses plant toxins and mind-controlling pheromones for her criminal activities', 6),
('Magneto', 'The character is a powerful mutant, one of a fictional subspecies of humanity born with superhuman abilities, who has the ability to generate and control magnetic fields.', 7),
('Princess Azula', 'Azula is the crown princess of the Fire Nation and a Firebending prodigy.', 8);

insert into `headquarters`(address, planet)
values
('123 Super Street, New York, NY', 'Earth'),
('9964 Lunar Crater', 'The Moon');

insert into `organization`(name, description, email, alignment, headquartersId)
values
('Heroic Organization', 'Heroic: A club for cool heroes!', 'hero@heroclub.com', true, 1),
('Villains Only', 'Villainous: Lame Heroes stay out!', 'none@urbusiness.com', false, 2);

insert into `superOrganization`(superId, organizationId)
values
(1, 1),
(2, 2),
(3, 1),
(4, 2),
(5, 1),
(6, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 2);

insert into `location`(name, description, address, latitude, longitude)
values
('Central Park', 'New York Parks and Recreation', 'Central Avenue', 180.000000, 180.000000),
('Golden Gate Bridge', 'The Pride of the West', '155 Golden Gate Street', 115.000000, 110.000000);

insert into `sighting`(dateTime, locationId)
values
('2010-01-01 12:12:12', 1),
('2010-01-02 12:12:12', 1),
('2010-01-03 12:12:12', 1),
('2010-01-04 12:12:12', 1),
('2010-01-05 12:12:12', 1),
('2010-01-06 12:12:12', 1),
('2010-01-07 12:12:12', 1),
('2011-01-01 12:12:12', 2),
('2012-01-01 12:12:12', 2),
('2018-03-20 12:12:12', 2);

insert into `superSighting`(superId, sightingId)
values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 6),
(2, 1), (2, 5), (2, 9),
(3, 3), (3, 7),
(4, 3), (4, 10),
(9, 1), (9, 2), (9, 8);



