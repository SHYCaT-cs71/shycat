class Event {
    constructor({
        id,
        title,
        summary,
        description,
        startDate,
        endDate,
        locationType,
        locationName,
        locationAddress,
        locationUrl,
        locationGeo,
        host,
        originalLink,
        tags,
        imageUrl
    }) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.description = description || summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationType = locationType;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.locationUrl = locationUrl;
        this.locationGeo = locationGeo;
        this.host = host;
        this.originalLink = originalLink;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }
}

export const mockHarvardEvents = [
    new Event({
        id: 1,
        title: "Harvard Startup Showcase",
        summary: "An exciting showcase where Harvard entrepreneurs and innovators present their startup ideas.",
        description: "This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.",
        startDate: "2024-11-30T18:00:00",
        endDate: "2024-11-30T19:00:00",
        locationName: "Harvard Innovation Labs, Allston, MA",
        host: "Harvard Innovation Labs",
        originalLink: "https://example.com/harvard-startup-showcase",
        tags: ["Entrepreneurship", "Startups", "Networking"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Veritas Forum: Ethics in AI",
        summary: "A forum on the ethical implications of artificial intelligence featuring Harvard faculty and experts.",
        description: "This forum will explore the ethical dimensions of AI development, addressing questions about technology's impact on society, privacy, and the role of ethics in AI. Attendees will gain insights from experts who discuss the responsibilities of developers and the moral complexities that come with advancing artificial intelligence. A thought-provoking event for anyone interested in technology's future and its ethical considerations.",
        startDate: "2024-12-05T19:00:00",
        endDate: "2024-12-30T20:00:00",
        locationName: "Sanders Theatre, Cambridge, MA",
        host: "Veritas Forum",
        originalLink: "https://example.com/veritas-forum-ai",
        tags: ["Ethics", "AI", "Lecture"],
        imageUrl: ""
    }),
    new Event({
        title: "Harvard-Yale Debate",
        summary: "The annual debate between Harvard and Yale on a current societal issue.",
        description: "Continuing a historic rivalry, Harvard and Yale debaters face off to argue key issues impacting society today. This event combines tradition with a vibrant intellectual exchange, attracting students, faculty, and alumni from both universities. It’s a chance to witness passionate debate, learn about important issues, and support Harvard's team in a friendly, competitive atmosphere.",
        startDate: "2024-12-10T18:30:00",
        locationName: "Harvard Hall",
        locationAddress: "Harvard Hall, Cambridge, MA",
        host: "Harvard Debate Council",
        originalLink: "https://example.com/harvard-yale-debate",
        tags: ["Debate", "Yale", "Tradition"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Winter Concert by Harvard-Radcliffe Orchestra",
        summary: "Enjoy classical pieces performed by the Harvard-Radcliffe Orchestra in this winter concert.",
        description: "A delightful evening awaits as the Harvard-Radcliffe Orchestra performs classic compositions, showcasing the talent of Harvard’s dedicated musicians. This winter concert has become a seasonal tradition, offering audiences a chance to experience live orchestral music in the beautiful setting of Memorial Hall. The concert will feature renowned pieces, providing a perfect way to celebrate the holiday season.",
        startDate: "2024-12-15T20:00:00",
        locationName: "Memorial Hall, Cambridge, MA",
        host: "Harvard-Radcliffe Orchestra",
        originalLink: "https://example.com/winter-concert",
        tags: ["Music", "Classical", "Concert"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Harvard Environmental Conference",
        summary: "A conference on sustainable practices, climate change, and environmental policy.",
        description: "Join experts, students, and community leaders to discuss the latest research and solutions for environmental sustainability. Sessions will cover diverse topics, including climate action, renewable energy, and policy advocacy. The conference provides a platform for impactful discussions, with workshops designed to inspire actionable solutions to pressing environmental issues.",
        startDate: "2024-12-20T09:00:00",
        locationName: "Harvard Science Center, Cambridge, MA",
        host: "Harvard Environmental Club",
        originalLink: "https://example.com/harvard-environmental-conference",
        tags: ["Environment", "Sustainability", "Research"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Art Exhibit: Harvard Graduate Student Showcase",
        description: "The Harvard Graduate Student Showcase is a unique opportunity to experience the artistry and creative expression of Harvard's graduate students. The exhibit features diverse media, from painting and sculpture to digital art, providing insight into emerging art trends and individual perspectives. Open to the public, it’s an inspiring experience for art lovers and supporters of Harvard's creative talent.",
        startDate: "2025-01-05T10:00:00",
        locationName: "Harvard Art Museums, Cambridge, MA",
        host: "Harvard Art Museums",
        originalLink: "https://example.com/graduate-student-art",
        tags: ["Art", "Exhibit", "Harvard"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Career Fair: STEM Opportunities",
        summary: "A career fair for students and alumni interested in STEM, with top employers from the industry.",
        description: "Meet industry-leading companies and explore career paths in science, technology, engineering, and mathematics at Harvard's STEM Career Fair. This event allows students and alumni to connect with recruiters, learn about job and internship opportunities, and gain valuable career advice. With representatives from tech giants, research institutions, and startups, this is a key event for anyone interested in a STEM career.",
        startDate: "2025-01-10T13:00:00",
        locationName: "Harvard Science Center Plaza, Cambridge, MA",
        host: "Harvard Career Services",
        originalLink: "https://example.com/stem-career-fair",
        tags: ["Career Fair", "STEM", "Networking"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Panel: Literature and Social Change",
        summary: "A panel discussion on literature's role in driving social change, featuring Harvard professors and authors.",
        description: "Explore how literature has influenced and mirrored societal changes through the years. This panel brings together Harvard professors and celebrated authors to discuss the power of the written word in shaping social movements and cultural perspectives. Attendees will gain a deeper understanding of literature’s role in advocacy and change, from historical works to contemporary authors.",
        startDate: "2025-01-15T17:00:00",
        locationName: "Barker Center, Cambridge, MA",
        host: "Harvard Department of English",
        originalLink: "https://example.com/literature-social-change",
        tags: ["Literature", "Social Change", "Panel"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Harvard African Cultural Night",
        summary: "An evening celebrating African culture with music, performances, and cuisine.",
        description: "A vibrant evening that celebrates the richness and diversity of African culture. Experience traditional and modern African music, dance performances, and sample delicious cuisine from across the continent. Hosted by the Harvard African Students Association, this event is an opportunity to enjoy cultural expression and foster community through the arts.",
        startDate: "2025-01-20T18:00:00",
        locationName: "Student Organization Center at Hilles (SOCH), Cambridge, MA",
        host: "Harvard African Students Association",
        originalLink: "https://example.com/african-cultural-night",
        tags: ["Culture", "Africa", "Community"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    }),
    new Event({
        title: "Public Health Lecture: Innovations in Vaccine Development",
        summary: "A lecture on recent advancements in vaccine research and development.",
        description: "Join leading scientists from the Harvard T.H. Chan School of Public Health as they discuss recent innovations in vaccine development. This lecture will cover groundbreaking research, challenges in global vaccine distribution, and the future of immunization technology. Ideal for students, health professionals, and anyone interested in public health, this event highlights critical advancements in fighting infectious diseases.",
        startDate: "2025-01-25T16:00:00",
        locationName: "Harvard T.H. Chan School of Public Health, Boston, MA",
        host: "Harvard Public Health Association",
        originalLink: "https://example.com/vaccine-development",
        tags: ["Public Health", "Vaccine", "Lecture"],
        imageUrl: `https://picsum.photos/800/600?random=${Math.random()}`
    })
];

export default Event;
