import React from 'react';

const EventCard = ({ event }) => {
    return (
        <div data-testid="event-card" className="card card-compact bg-base-100 w-96 shadow-xl">
            <figure>
                <img src={event.imageUrl} alt={event.title} />
            </figure>
            <div className="card-body">
                <h2 className="card-title">{event.title}</h2>
                <div className="event-info">
                    <p className="event-location">{event.location}</p>
                    <p className="event-date">{event.date.toLocaleString()}</p>
                </div>
                <p className="event-summary">{event.summary}</p>
                <div className="card-tags">
                    {event.tags.map((tag, index) => (
                        <div className="badge badge-outline" key={index}>{tag}</div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default EventCard;
