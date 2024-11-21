-- reset identity seed for id column to ensure that the child tags are always linked correctly
ALTER TABLE event
    ALTER COLUMN id RESTART WITH 1;

INSERT INTO event (title,
                   summary,
                   description,
                   date,
                   location,
                   host,
                   original_Link,
                   image_Url)
VALUES ('Test title',
        'An exciting showcase where Harvard entrepreneurs and innovators present their startup ideas.',
        'This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard''s brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.',
        '2024-11-30 18:00:00',
        'Harvard Innovation Labs, Allston, MA',
        'Harvard Innovation Labs',
        'https://example.com/harvard-startup-showcase',
        'https://picsum.photos/800/600?random=0.1'),
       ('Veritas Forum: Ethics in AI',
        'A forum on the ethical implications of artificial intelligence featuring Harvard faculty and experts.',
        'This forum will explore the ethical dimensions of AI development, addressing questions about technology''s impact on society, privacy, and the role of ethics in AI. Attendees will gain insights from experts who discuss the responsibilities of developers and the moral complexities that come with advancing artificial intelligence. A thought-provoking event for anyone interested in technology''s future and its ethical considerations.',
        '2024-12-05 19:00:00',
        'Sanders Theatre, Cambridge, MA',
        'Veritas Forum',
        'https://example.com/veritas-forum-ai',
        'https://picsum.photos/800/600?random=0.2'),
       ('Harvard-Yale Debate',
        'The annual debate between Harvard and Yale on a current societal issue.',
        'Continuing a historic rivalry, Harvard and Yale debaters face off to argue key issues impacting society today. This event combines tradition with a vibrant intellectual exchange, attracting students, faculty, and alumni from both universities. It’s a chance to witness passionate debate, learn about important issues, and support Harvard''s team in a friendly, competitive atmosphere.',
        '2024-12-10 18:30:00',
        'Harvard Hall, Cambridge, MA',
        'Harvard Debate Council',
        'https://example.com/harvard-yale-debate',
        'https://picsum.photos/800/600?random=0.3'),
       ('Winter Concert by Harvard-Radcliffe Orchestra',
        'Enjoy classical pieces performed by the Harvard-Radcliffe Orchestra in this winter concert.',
        'A delightful evening awaits as the Harvard-Radcliffe Orchestra performs classic compositions,
showcasing the talent of Harvard''s dedicated musicians. This winter concert has become a seasonal tradition,
offering audiences a chance to experience live orchestral music in the beautiful setting of Memorial Hall. The concert will feature renowned pieces,
providing a perfect way to celebrate the holiday season.',
        '2024-12-15 20:00:00',
        'Memorial Hall, Cambridge, MA',
        'Harvard-Radcliffe Orchestra',
        '
https://example.com/winter-concert',
        '
https://picsum.photos/800/600?random=0.4'),
       ('Harvard Environmental Conference',
        'A conference on sustainable practices, climate change, and environmental policy.',
        'Join experts, students, and community leaders to discuss the latest research and solutions for environmental sustainability. Sessions will cover diverse topics, including climate action, renewable energy, and policy advocacy. The conference provides a platform for impactful discussions,
with workshops designed to inspire actionable solutions to pressing environmental issues.',
        '2024-12-20 09:00:00',
        'Harvard Science Center, Cambridge, MA',
        'Harvard Environmental Club',
        'https://example.com/harvard-environmental-conference',
        'https://picsum.photos/800/600?random=0.5');

INSERT INTO event_tags (event_id, tags)
VALUES (1, 'Entrepreneurship'),
       (1, 'Startups'),
       (1, 'Networking'),
       (2, 'Ethics'),
       (2, 'AI'),
       (2, 'Lecture'),
       (3, 'Debate'),
       (3, 'Yale'),
       (3, 'Tradition'),
       (4, 'Music'),
       (4, 'Classical'),
       (4, 'Concert');




