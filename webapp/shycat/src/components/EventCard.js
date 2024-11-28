import React from 'react';

export const getEventSummary = (description) => {
    return description.split(". ")[0] + "."
}

const EventCard = ({ event }) => {
    let endDateP;
    if (event.endDate) {
        endDateP = <p className="event-date">End: {event.endDate}</p>
    }

    let locationNameP;
    if (event.locationName) {
        locationNameP = <p className="event-location">Location: {event.locationName}</p>
    }

    let locationAddressP;
    if (event.locationAddress) {
        locationAddressP = <p className="event-location">Address: {event.locationAddress}</p>
    }

    let summaryP;
    if (event.summary) {
        summaryP = <p className="event-summary">{event.summary}</p>
    } else {
        summaryP = <p className="event-summary">{getEventSummary(event.description)}</p>
    }

    return (
        <div data-testid="event-card" className="card card-compact bg-base-100 w-96 shadow-xl">
            <figure>
                <img src={event.imageUrl} alt={event.title} />
            </figure>
            <div className="card-body">
                <h2 className="card-title">{event.title}</h2>
                <div className="event-info">
                    {locationNameP}
                    {locationAddressP}
                    <p className="event-date">Start: {event.startDate}</p>
                    {endDateP}
                </div>
                {summaryP}
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
